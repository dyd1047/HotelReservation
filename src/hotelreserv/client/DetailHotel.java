package hotelreserv.client;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hotelreserv.admin.HotelVO;
import hotelreserv.admin.ReservVO;

public class DetailHotel extends Page {

   JPanel p_option;
   public JPanel p_content; // 상세내용을 담게 될 패널
   public JPanel p_can; // 큰 상품이미지 그려질 패널

   JLabel la_hname; // 상품명 라벨
   JLabel la_adress;
   JLabel la_phone;
   JLabel la_price;

   JLabel la_s;
   JLabel la_e;

   Choice startyy; // 색상옵션
   Choice startmm; // 색상옵션
   Choice startdd; // 색상옵션

   Choice lastyy;
   Choice lastmm;
   Choice lastdd;

   String startdate;
   String lastdate;

   JButton bt_reserv; // 구매하기 버튼

   HotelMember hotelMember;
   HotelVO vo;
   private Image img;

   public DetailHotel(ClientMain clientMain) {
      super(clientMain);

      p_content = new JPanel();
      p_can = new JPanel() {
         @Override
         public void paint(Graphics g) {
            g.drawImage(img, 0, 0, p_can);
         }
      };

      p_option = new JPanel();

      la_hname = new JLabel();
      la_adress = new JLabel();
      la_phone = new JLabel();
      la_price = new JLabel();

      la_s = new JLabel("입실날짜");
      la_e = new JLabel("퇴실날짜");

      startyy = new Choice();
      startmm = new Choice();
      startdd = new Choice();
      lastyy = new Choice();
      lastmm = new Choice();
      lastdd = new Choice();

      for (int i = 2020; i < 2030; i++) {
         startyy.add(Integer.toString(i));
      }
      for (int i = 1; i < 13; i++) {
         startmm.add(Integer.toString(i));
      }
      for (int i = 1; i < 32; i++) {
         startdd.add(Integer.toString(i));
      }

      for (int i = 2020; i < 2030; i++) {
         lastyy.add(Integer.toString(i));
      }
      for (int i = 1; i < 13; i++) {
         lastmm.add(Integer.toString(i));
      }
      for (int i = 1; i < 32; i++) {
         lastdd.add(Integer.toString(i));
      }

      bt_reserv = new JButton("예약하기");

      // 스타일 적용
      p_content.setPreferredSize(new Dimension(1100, 700));

      Dimension d = new Dimension((clientMain.WIDTH - 100) / 3, 30);

      la_hname.setPreferredSize(d);
      la_adress.setPreferredSize(d);
      la_phone.setPreferredSize(d);
      la_price.setPreferredSize(d);

      bt_reserv.setPreferredSize(new Dimension(200, 30));

      startyy.setPreferredSize(new Dimension((clientMain.WIDTH - 100) / 3, 30));
      startmm.setPreferredSize(new Dimension((clientMain.WIDTH - 100) / 3, 30));
      startdd.setPreferredSize(new Dimension((clientMain.WIDTH - 100) / 3, 30));
      lastyy.setPreferredSize(new Dimension((clientMain.WIDTH - 100) / 3, 30));
      lastmm.setPreferredSize(new Dimension((clientMain.WIDTH - 100) / 3, 30));
      lastdd.setPreferredSize(new Dimension((clientMain.WIDTH - 100) / 3, 30));

      la_s.setPreferredSize(new Dimension(d));
      la_e.setPreferredSize(new Dimension(d));

      // 조립
      p_content.setLayout(new GridLayout(1, 2));

      p_option.add(la_hname);
      p_option.add(la_adress);
      p_option.add(la_price);
      p_option.add(la_phone);
      p_option.add(la_price);

      p_option.add(la_s);
      p_option.add(startyy);
      p_option.add(startmm);
      p_option.add(startdd);
      p_option.add(la_e);
      p_option.add(lastyy);
      p_option.add(lastmm);
      p_option.add(lastdd);

      p_option.add(bt_reserv);

      p_content.add(p_can);
      p_content.add(p_option);

      add(p_content);

      bt_reserv.addActionListener((e) -> {
         if(clientMain.isHasSession()) {
//            System.out.println(hotelMember.getMember_id());
            regist(clientMain.vo, vo);
            JOptionPane.showMessageDialog(clientMain, "예약성공");
            clientMain.showPage(clientMain.MYPAGE);
            
            MyPage mypage = (MyPage) clientMain.page[ClientMain.MYPAGE];
            mypage.getList();
            updateUI();
            
            updateUI();
         }else {
            JOptionPane.showMessageDialog(clientMain, "로그인이 필요합니다.");
         }
      });

   }

   public void init(HotelVO vo, Image img) {
      this.vo = vo; // 멤버변수에 현재 보고있는 상품 vo를 주입
      la_hname.setText(vo.getHname());
      la_adress.setText(vo.getAdress());
      la_phone.setText(vo.getPhone());
      la_price.setText(Integer.toString(vo.getPrice()));

      this.img = img;
      this.img = this.img.getScaledInstance(500, 550, Image.SCALE_SMOOTH);
   }

   public int regist(HotelMember hotelMember, HotelVO hotelvo) {
      PreparedStatement pstmt = null;

      int result = 0;
      String sql = "insert into reservinfo(member_id,u_id,u_name,u_phone,hotel_id,h_name,h_phone,startdate,enddate)";
      sql += " values(?,?,?,?,?,?,?,?,?)";

      startdate = startyy.getSelectedItem() + "년 " + startmm.getSelectedItem() + "월 " + startdd.getSelectedItem() + "일";
      lastdate = lastyy.getSelectedItem() + "년 " + lastmm.getSelectedItem() + "월 " + lastdd.getSelectedItem() + "일";

      try {
         pstmt = clientMain.getCon().prepareStatement(sql);

         // 바인드변수 대입
         pstmt.setInt(1, hotelMember.getMember_id());
         pstmt.setString(2, hotelMember.getMid());
         pstmt.setString(3, hotelMember.getName());
         pstmt.setString(4, hotelMember.getPhone());
         pstmt.setInt(5, vo.getHotel_id());
         pstmt.setString(6, vo.getHname());
         pstmt.setString(7, vo.getPhone());
         pstmt.setString(8, startdate);
         pstmt.setString(9, lastdate);

         result = pstmt.executeUpdate(); // 쿼리 수행

      } catch (SQLException e) {
         e.printStackTrace();

      } finally {
         clientMain.getDbManager().close(pstmt);
      }
      return result;

   }

   public HotelVO getVo() {
      return vo;
   }

   public void setVo(HotelVO vo) {
      this.vo = vo;
   }

   public Image getImg() {
      return img;
   }

   public void setImg(Image img) {
      this.img = img;
   }

}