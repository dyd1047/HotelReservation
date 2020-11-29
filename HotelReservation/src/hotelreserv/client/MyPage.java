package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hotelreserv.admin.HotelVO;
import hotelreserv.admin.ReservVO;

public class MyPage extends Page{
   JPanel p_wrapper;
   JPanel p_topImage;
   JScrollPane scroll;
   JPanel p_content;

   ImageIcon icon;

   ArrayList<MyPageItem> itemList;

   public MyPage(ClientMain clientMain) {
      super(clientMain);
      
      //이미지
      URL url = this.getClass().getClassLoader().getResource("res/reservList.png");
        icon = new ImageIcon(url);
        Image img = icon.getImage();
        img = img.getScaledInstance(1000, 100, Image.SCALE_SMOOTH);
        icon.setImage(img);
      
      // 생성
      p_topImage = new JPanel() {
            @Override
            public void paint(Graphics g) {
               g.drawImage(icon.getImage(), 0, 0, this);
            }
         };
      p_content = new JPanel();
      p_wrapper = new JPanel();
      scroll = new JScrollPane(p_content);

      // 스타일
      this.setBackground(Color.WHITE);

      p_wrapper.setLayout(new BorderLayout());
      p_wrapper.setPreferredSize(new Dimension(1200, 750));
      
      p_topImage.setPreferredSize(new Dimension(1000, 100));

      p_content.setBackground(Color.WHITE);
      p_content.setPreferredSize(new Dimension(950, 2000));

      p_content.add(p_topImage);
      p_wrapper.add(scroll);
      add(p_wrapper);

      getList();
      
      // 생성된 아이템들에 대해서 마우스 리스너 연결하기
      for (MyPageItem item : itemList) {
         item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
//               DetailHotel productDetail = (DetailHotel) clientMain.page[clientMain.DETAILHOTEL];
//               productDetail.init(item.vo, item.img); // 아이템이 보유한 ProductVO 전달
//               productDetail.updateUI();
//               productDetail.p_can.repaint();
               // 보여주고 싶은 페이지
               clientMain.showPage(clientMain.DETAILHOTEL);
            }
         });
      }

   }

   public void getList() {
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      String sql = "select * from reservinfo where member_id=?";

      try {
         pstmt = clientMain.getCon().prepareStatement(sql);
         pstmt.setInt(1, clientMain.vo.getMember_id());
         rs = pstmt.executeQuery();
         itemList = new ArrayList<MyPageItem>();

         while (rs.next()) {
            ReservVO vo = new ReservVO();
            vo.setMember_id(rs.getInt("member_id"));
               vo.setU_id(rs.getString("u_id"));
               vo.setU_name(rs.getString("u_name"));
               vo.setU_phone(rs.getString("u_phone"));
               vo.setHotel_id(rs.getInt("hotel_id"));
               vo.setH_name(rs.getString("h_name"));
               vo.setH_phone(rs.getString("h_phone"));
               vo.setH_adress(rs.getString("h_adress"));
               vo.setH_filename(rs.getString("h_filename"));
               vo.setStartdate(rs.getString("startdate"));
               vo.setEnddate(rs.getString("enddate"));

            itemList.add(getCreateItem(vo));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         clientMain.getDbManager().close(pstmt, rs);
      }

   }

   // 상품 아이템 카드 생성하기
   public MyPageItem getCreateItem(ReservVO vo) {
      MyPageItem item = new MyPageItem(this, vo, 900, 150);
      p_content.add(item);

      return item; // 생성 후 반환까지 하기
   }

}