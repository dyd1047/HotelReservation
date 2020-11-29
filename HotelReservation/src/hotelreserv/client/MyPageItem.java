package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import hotelreserv.admin.HotelVO;
import hotelreserv.admin.ReservVO;


public class MyPageItem extends JPanel{
   JPanel p_can;
   JPanel p_la;
   
   JLabel la_uname;
   JLabel la_uphone;
   JLabel la_hname;
   JLabel la_hphone;
   JLabel la_hadress;
   JLabel la_startDate;
   JLabel la_endDate;
   
   
   ReservVO vo;
   Image img;
   MyPage myPage;
   
   public MyPageItem(MyPage myPage, ReservVO vo,int width,int height) {
      this.myPage = myPage;
      this.vo = vo;
      try {
         URL url = new URL(vo.getH_filename());
         img = ImageIO.read(url); //원본크기의 이미지가 오므로, 크기 조절이 필요하다
         img = img.getScaledInstance(width/4, height,Image.SCALE_SMOOTH);
      } catch (MalformedURLException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
      
      
      p_can = new JPanel() {
         @Override
         public void paint(Graphics g) {
            g.drawImage(img, 0, 0, p_can);
         }
      };
      p_la = new JPanel();
   
      la_uname = new JLabel("예약자 성함 : "+vo.getU_name());
      la_uphone = new JLabel("예약자 연락처 : "+vo.getU_phone());
      la_hname = new JLabel("호텔 이름 : "+vo.getH_name());
      la_hphone = new JLabel("호텔 연락처 : "+vo.getH_phone());
      la_hadress = new JLabel("호텔 주소 : "+vo.getH_adress());
      la_startDate = new JLabel("Check-In : "+vo.getStartdate());
      la_endDate = new JLabel("Check-Out : "+vo.getEnddate());
      
      //스타일
      this.setLayout(new BorderLayout(10,10));
      this.setPreferredSize(new Dimension(width,height));
      this.setBorder(new LineBorder(Color.BLACK, 2));
      this.setBackground(Color.WHITE);
      
      p_can.setPreferredSize(new Dimension(width/4,height));
      p_can.setBackground(Color.WHITE);
      p_la.setLayout(new GridLayout(4,2));
      p_la.setBackground(Color.WHITE);
      
      la_uname.setFont(new Font("맑은 고딕", Font.BOLD, 14));
      la_hname.setFont(new Font("맑은 고딕", Font.BOLD, 14));
      la_uphone.setFont(new Font("맑은 고딕", Font.BOLD, 14));
      la_hphone.setFont(new Font("맑은 고딕", Font.BOLD, 14));
      la_startDate.setFont(new Font("맑은 고딕", Font.BOLD, 14));
      la_hadress.setFont(new Font("맑은 고딕", Font.BOLD, 14));
      la_endDate.setFont(new Font("맑은 고딕", Font.BOLD, 14));
      
      p_la.add(la_uname);
      p_la.add(la_hname);
      p_la.add(la_uphone);
      p_la.add(la_hphone);
      p_la.add(la_startDate);
      p_la.add(la_hadress);
      p_la.add(la_endDate);
      
      add(p_can, BorderLayout.WEST);
      add(p_la);
      
   }

}