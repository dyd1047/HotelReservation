package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import common.image.ImageUtil;

public class Test extends JFrame{
	static boolean loginCheck = false;
	Image img;
	JPanel p_test;
	JButton bt_board;
	TopMenu topMenu;
	
	public Test() {
		topMenu = new TopMenu();
		p_test = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, p_test);
			}
		};
		bt_board = new JButton(ImageUtil.getIcon(this.getClass(), "res/logo.png", 90, 35));
		
		try {
			URL url = new URL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUHouNAtA1H98cZK9ZdkNoxzGmNhDCF4pzMQ&usqp=CAU");
			img = ImageIO.read(url); //원본크기의 이미지가 오므로, 크기 조절이 필요
//			img = img.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		p_test.setPreferredSize(new Dimension(300, 300));
		
		add(p_test);
		add(bt_board);
		add(topMenu, BorderLayout.NORTH);
		
		setSize(1200, 900);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		bt_board.addActionListener((e)->{
			topMenu.setLogoutButton(topMenu);
	    });
	}
	
	public static void main(String[] args) {
		new Test();
	}
}
