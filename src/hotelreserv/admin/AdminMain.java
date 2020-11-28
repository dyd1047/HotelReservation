package hotelreserv.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import common.image.ImageUtil;
import hotelreserv.util.db.DBManager;

public class AdminMain extends JFrame {
   public static final int WIDTH = 1200;
   public static final int HEIGHT = 900;
   // 최상위 페이지들
   public static final int START = 0;
   public static final int USERINFO = 1;
   public static final int HOTELINFO = 2;
   public static final int RESERVINFO = 3;

   JPanel p_navi; // 웹사이트의 주 메뉴를 포함할 컨테이너 패널
   JPanel p_naviLogo; // 웹사이트의 주 메뉴를 포함할 컨테이너 패널
   String[] navi_title = { "메인", "회원관리", "호텔관리", "예약관리" };

   public JButton[] navi = new JButton[navi_title.length]; // [][][][][] 배열생성

   JPanel p_content;
   JPanel user_container;
   Page[] page = new Page[4];

   private DBManager dbManager;
   private Connection con;

   public AdminMain() {
      dbManager = new DBManager();
      p_content = new JPanel();
      user_container = new JPanel();

      user_container = new JPanel();
      p_content = new JPanel();
      p_navi = new JPanel();
      p_naviLogo = new JPanel();

      con = dbManager.connect();

      if (con == null) {
         JOptionPane.showMessageDialog(this, "데이터베이스 접속 실패");
      } else {
         this.setTitle("HotelReservation 관리자로 접속 성공");
      }

      navi[0] = new JButton(ImageUtil.getIcon(this.getClass(), "res/logo_black.png", 200, 100));
      navi[0].setPreferredSize(new Dimension(200, 100));
      navi[0].setBorderPainted(false);
      navi[0].setContentAreaFilled(false);
      navi[0].setFocusPainted(false);
      navi[0].setCursor(new Cursor(Cursor.HAND_CURSOR));
      p_naviLogo.setLayout(new BorderLayout());
      p_naviLogo.add(navi[0], BorderLayout.WEST);
      p_navi.add(p_naviLogo);

      for (int i = 1; i < navi.length; i++) {
         navi[i] = new JButton(navi_title[i]);
         navi[i].setPreferredSize(new Dimension(200, 80));
         navi[i].setForeground(Color.WHITE);
         navi[i].setBorderPainted(false);
         navi[i].setContentAreaFilled(false);
         navi[i].setFocusPainted(false);
         navi[i].setFont(new Font("맑은 고딕", Font.BOLD, 24));
         navi[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
         navi[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
               Object obj = e.getSource();
               for (int j = 0; j < navi.length; j++) {
                  if (obj == navi[j]) {
                     navi[j].setBackground(Color.WHITE);
                     navi[j].setForeground(Color.BLACK);
                     navi[j].setContentAreaFilled(true);
                  }
               }
            }

            @Override
            public void mouseExited(MouseEvent e) {
               Object obj = e.getSource();
               for (int j = 0; j < navi.length; j++) {
                  if (obj == navi[j]) {
                     navi[j].setBackground(Color.BLACK);
                     navi[j].setForeground(Color.WHITE);
                     navi[j].setContentAreaFilled(true);
                  }
               }
            }
         });
         p_navi.add(navi[i]);
      }

      // 페이지구성
      page[0] = new Start(this);
      page[1] = new UserInfo(this);
      page[2] = new HotelInfo(this);
      page[3] = new ReservInfo(this);

      //스타일
      user_container.setPreferredSize(new Dimension(1200,900));
      user_container.setLayout(new BorderLayout());
      user_container.add(p_navi,BorderLayout.NORTH);
      
      p_navi.setPreferredSize(new Dimension(1200, 100));
      p_navi.setBackground(Color.BLACK);
      p_naviLogo.setPreferredSize(new Dimension(550, 100));
      p_naviLogo.setBackground(Color.BLACK);

      for(int i=0;i<page.length;i++) {
         p_content.add(page[i]);
      }

      showPage(0); // 처음나올 페이지 설정

      user_container.add(p_content); // 센터에 페이지 부착

      add(user_container);

      this.setSize(1200, 900);
      this.setVisible(true);
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      setLocationRelativeTo(null);

      this.addWindowListener(new WindowAdapter() {
         @Override
         public void windowClosing(WindowEvent e) {
            dbManager.disConnect(con);
         }
      });

      // 네비게이션 버튼과 리스너연결
      for (int i = 0; i < navi.length; i++) {
         navi[i].addActionListener((e) -> {
            Object obj = e.getSource();
            if (obj == navi[0]) { // home
               showPage(START);
            } else if (obj == navi[1]) {
               showPage(USERINFO);
            } else if (obj == navi[2]) {
               showPage(HOTELINFO);
            } else if (obj == navi[3]) {
               showPage(RESERVINFO);
            }

         });
      }

   }

   public void showPage(int showIndex) { // 매개변수로는 보고싶은 페이지 넘버
      for (int i = 0; i < page.length; i++) {
         if (i == showIndex) {
            page[i].setVisible(true);
         } else {
            page[i].setVisible(false);
         }
      }
   }

   public Connection getCon() {
      return con;
   }

   public void setCon(Connection con) {
      this.con = con;
   }
   
   

   public DBManager getDbManager() {
	return dbManager;
}

public void setDbManager(DBManager dbManager) {
	this.dbManager = dbManager;
}

public static void main(String[] args) {
      new AdminMain();
   }

}