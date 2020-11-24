package hotelreserv.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import hotelReservation.design.ButtonDesign;

public class TopMenu extends JPanel{
   JButton bt_login;
   JButton bt_myPage;
   JButton bt_logout;

   
   public TopMenu() {

      bt_login = new ButtonDesign("로그인", Color.WHITE, Color.RED, 200, 40, true, true, true);
      bt_myPage = new ButtonDesign("마이페이지", Color.WHITE, Color.RED, 200, 40, true, true, false);
      bt_logout = new ButtonDesign("로그아웃", Color.WHITE, Color.RED, 200, 40, false, false, false);
      
      setPreferredSize(new Dimension(1200, 100));
      setBackground(Color.RED);
      
      add(bt_myPage);
      add(bt_login);
      
   }


public JButton getBt_login() {
	return bt_login;
}


public void setBt_login(JButton bt_login) {
	this.bt_login = bt_login;
}


public JButton getBt_logout() {
	return bt_logout;
}


public void setBt_logout(JButton bt_logout) {
	this.bt_logout = bt_logout;
}


public void setLoginButton(TopMenu topMenu) {
    topMenu.remove(bt_logout);
    topMenu.remove(bt_myPage);
    topMenu.add(bt_login); 
    topMenu.updateUI();
 }
 
 public void setLogoutButton(TopMenu topMenu) {
    topMenu.remove(bt_login);
    topMenu.add(bt_myPage);
    topMenu.add(bt_logout);
    topMenu.updateUI();
 }


   
   
}