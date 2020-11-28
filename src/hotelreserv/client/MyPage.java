package hotelreserv.client;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import hotelreserv.admin.ReservModel;
import hotelreserv.admin.UserModel;

public class MyPage extends Page{
	JTable table;
	JScrollPane scroll;
//	MyPageModel myPageModel;

	public MyPage(ClientMain clientMain) {
		super(clientMain);
		
//		table = new JTable(myPageModel=new MyPageModel());
		scroll = new JScrollPane(table);
		
		scroll.setPreferredSize(new Dimension(1200,800));
		add(scroll);
		
		
//		getList() ;
//		System.out.println(clientMain.vo.getMember_id());
		
	}
	
	public void getList() {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select *from reservinfo where member_id=?";
		
		try {
			pstmt = clientMain.getCon().prepareStatement(sql
					,ResultSet.TYPE_SCROLL_INSENSITIVE
					,ResultSet.CONCUR_READ_ONLY);
			
			pstmt.setInt(1, clientMain.vo.getMember_id());
			System.out.println(clientMain.vo.getMember_id());
			rs= pstmt.executeQuery();
			 
			
			rs.last(); //커서를 제일 마지막으로 보내기
			int total = rs.getRow();
			
//			String[][]data = new String[total][myPageModel.column.length];
			rs.beforeFirst(); //이건 맨꼭대기 rs.First()는 꼭대기에서 밑에
			
			int index=0;
			while(rs.next()) {			
//			String[] record = new String[myPageModel.column.length];
			
//			record[0]= rs.getString("Member_id"); //그냥 String취급할수도 있어
//			record[1]= rs.getString("u_id");
//			record[2]= rs.getString("u_name");
//			record[3]= rs.getString("u_phone");
//			record[4]= rs.getString("hotel_id");
//			record[5]= rs.getString("h_name");
//			record[6]= rs.getString("h_phone");
//			record[7]= rs.getString("startdate");
//			record[8]= rs.getString("enddate");
			
			//채워진 일차원 배열을 data이차원배열에 순서대로 담자
//			data[index++]=record;
		}
		//완성된 이차월 배열을 boardModel이 보유한 data배열에 주소로 대입시켜버리자
//			myPageModel.data = data;
		
		//JTable 다시 ui갱신
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}
