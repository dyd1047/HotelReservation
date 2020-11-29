package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hotelreserv.admin.HotelVO;

public class AreaPageDesign extends JPanel{
   JPanel p_west;
   JPanel p_wrapper;
   JPanel p_topImage;
   JScrollPane scroll;
   JPanel p_content;
   String[] areaTitle = { "서울", "부산" };
   JButton[] bt_area = new JButton[areaTitle.length];

   String area;
   ClientMain clientMain;
   ImageIcon icon;

   ArrayList<HotelItem> itemList;

   public AreaPageDesign(ClientMain clientMain, String area) {
      this.clientMain = clientMain;
      this.area = area;
      
      //이미지
      URL url = this.getClass().getClassLoader().getResource("res/topImage.png");
        icon = new ImageIcon(url);
        Image img = icon.getImage();
        img = img.getScaledInstance(1000, 100, Image.SCALE_SMOOTH);
        icon.setImage(img);
      
      // 생성
      p_west = new JPanel();
      p_topImage = new JPanel() {
            @Override
            public void paint(Graphics g) {
               g.drawImage(icon.getImage(), 0, 0, this);
            }
         };
      p_content = new JPanel();
      p_wrapper = new JPanel();
      scroll = new JScrollPane(p_content);

      for (int i = 0; i < bt_area.length; i++) {
         bt_area[i] = new JButton(areaTitle[i]);
         bt_area[i].setPreferredSize(new Dimension(200, 80));
         bt_area[i].setBackground(Color.WHITE);
         bt_area[i].setForeground(Color.BLACK);
         bt_area[i].setBorderPainted(false);
         bt_area[i].setContentAreaFilled(false);
         bt_area[i].setFocusPainted(false);
         bt_area[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));
         bt_area[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
         bt_area[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               Object obj = e.getSource();
               for (int j = 0; j < bt_area.length; j++) {
                  if (obj == bt_area[j]) {
                     bt_area[j].setBackground(Color.BLACK);
                     bt_area[j].setForeground(Color.WHITE);
                     bt_area[j].setContentAreaFilled(true);
                  }
               }
            }

            @Override
            public void mouseExited(MouseEvent e) {
               Object obj = e.getSource();
               for (int j = 0; j < bt_area.length; j++) {
                  if (obj == bt_area[j]) {
                     bt_area[j].setBackground(Color.WHITE);
                     bt_area[j].setForeground(Color.BLACK);
                     bt_area[j].setContentAreaFilled(true);
                  }
               }
            }
         });
         p_west.add(bt_area[i]);
      }

      // 스타일
      this.setBackground(Color.WHITE);

      p_wrapper.setLayout(new BorderLayout());
      p_wrapper.setPreferredSize(new Dimension(1200, 750));
      
      p_topImage.setPreferredSize(new Dimension(1000, 100));

      p_content.setBackground(Color.WHITE);
      p_content.setPreferredSize(new Dimension(1000, 2000));

      p_west.setBackground(Color.WHITE);
      p_west.setPreferredSize(new Dimension(200, 750));

      p_content.add(p_topImage);
      p_wrapper.add(scroll);
      p_wrapper.add(p_west, BorderLayout.WEST);
      add(p_wrapper);

      getHotelList(area);
      
      bt_area[0].addActionListener((e)->{
         clientMain.showPage(clientMain.SEOUL);
         updateUI();
      });
      bt_area[1].addActionListener((e)->{
         clientMain.showPage(clientMain.BUSAN);
         updateUI();
      });

      // 생성된 아이템들에 대해서 마우스 리스너 연결하기
      for (HotelItem item : itemList) {
         item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
               DetailHotel productDetail = (DetailHotel) clientMain.page[clientMain.DETAILHOTEL];
               productDetail.init(item.vo, item.img); // 아이템이 보유한 ProductVO 전달
               productDetail.updateUI();
               productDetail.p_can.repaint();
               // 보여주고 싶은 페이지
               clientMain.showPage(clientMain.DETAILHOTEL);
            }
         });
      }

   }

   public void getHotelList(String area) {
      PreparedStatement pstmt = null;
      ResultSet rs = null;

      String sql = "select *from hotelinfo where area=?";

      try {
         pstmt = clientMain.getCon().prepareStatement(sql);
         pstmt.setString(1, area);
         rs = pstmt.executeQuery();
         itemList = new ArrayList<HotelItem>();

         while (rs.next()) {
            HotelVO vo = new HotelVO();
            vo.setHotel_id(rs.getInt("hotel_id"));
            vo.setArea(rs.getString("area"));
            vo.setHname(rs.getString("hname"));
            vo.setAdress(rs.getString("adress"));
            vo.setPhone(rs.getString("phone"));
            vo.setPrice(rs.getInt("price"));
            vo.setFilename(rs.getString("filename"));

            itemList.add(getCreateItem(vo));
         }

      } catch (SQLException e) {
         e.printStackTrace();
      } finally {
         clientMain.getDbManager().close(pstmt, rs);
      }

   }

   // 상품 아이템 카드 생성하기
   public HotelItem getCreateItem(HotelVO vo) {
      HotelItem item = new HotelItem(this, vo, 900, 150);
      p_content.add(item);

      return item; // 생성 후 반환까지 하기
   }

}