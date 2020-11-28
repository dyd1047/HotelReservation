package hotelreserv.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import hotelreserv.client.HotelMember;

public class ReservInfo extends Page{
   JTable table;
   JScrollPane scroll;
   JPanel p_content;
   JPanel p_center;
   JPanel p_south;
   JButton bt_update;
   JButton bt_delete;
   
   ReservModel reservModel;

   public ReservInfo(AdminMain adminMain) {
      super(adminMain);
      
      table = new JTable();
      scroll = new JScrollPane(table);
      p_content = new JPanel();
      p_center = new JPanel();
      p_south = new JPanel();
      bt_update = new JButton("수정");
      bt_delete = new JButton("삭제");
      
      setLayout(new BorderLayout());
      p_content.setPreferredSize(new Dimension(1150,800));
      scroll.setPreferredSize(new Dimension(1150,500));
      p_south.setPreferredSize(new Dimension(1150,100));
      
      p_center.add(scroll);
      p_south.add(bt_update);
      p_south.add(bt_delete);
      
      p_content.add(p_center);
      p_content.add(p_south, BorderLayout.SOUTH);
      
      add(p_content);
      
      getReservList();
   }
   
   //예약정보 가져오기
   public void getReservList() {
      PreparedStatement pstmt = null;
      ResultSet rs = null;   
      
      String sql = "select * from reservinfo";
      
      
      try {
         pstmt = adminMain.getCon().prepareStatement(sql);
         rs = pstmt.executeQuery();
         
         //메타정보를 이용하여 ProductModel의 column ArrayList를 채우자
         ResultSetMetaData meta=rs.getMetaData();
         ArrayList<String> columnNames = new ArrayList<String>();
         
         for(int i =1; i<=meta.getColumnCount();i++) {
            String colName =meta.getColumnName(i); //컬럼명 추출
            columnNames.add(colName);
         }
         
         //rs의 레코드를 ProductModel의 record ArrayList에 채우자
         ArrayList<ReservVO> reservList = new ArrayList<ReservVO>();
         while(rs.next()) {
            ReservVO vo = new ReservVO(); //비어있는 vo를 생성해서 rs의 값들을 채워넣기 위해!
            vo.setMember_id(rs.getInt("member_id"));
            vo.setU_id(rs.getString("u_id"));
            vo.setU_name(rs.getString("u_name"));
            vo.setU_phone(rs.getString("u_phone"));
            vo.setHotel_id(rs.getInt("hotel_id"));
            vo.setH_name(rs.getString("h_name"));
            vo.setH_phone(rs.getString("h_phone"));
            vo.setStartdate(rs.getString("startdate"));
            vo.setEnddate(rs.getString("enddate"));
            
            reservList.add(vo); //방금 생성하고 하나의 레코드가 채워진 vo를 ArrayList에 추가하자
         }
         reservModel = new ReservModel();
         reservModel.recordReserv = reservList; //레코드 정보 대입
         reservModel.column=columnNames; //컬럼 정보 대입
         table.setModel(reservModel); //테이블에 방금 생성한 모델 적용!!
         table.updateUI();
      } catch (SQLException e) {
         e.printStackTrace();
      }finally {
         adminMain.getDbManager().close(pstmt,rs);
      }
      
   }
}