package hotelreserv.admin;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import common.image.ImageUtil;
import hotelreserv.client.ClientMain;

public class AdminMain extends JFrame{
	Image img;
	JPanel can;
	Toolkit kit;
	ClientMain clientMain;
	
	public AdminMain() {
		kit = Toolkit.getDefaultToolkit();
		img=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/HotelReservation/res/main.png"),1200, 900);
		
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, this);
			}
		};
		
		can.setPreferredSize(new Dimension(1200,900));
	
		this.add(can);
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		
		this.setSize(1200, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	}

	public static void main(String[] args) {
		new AdminMain();
	}

}
