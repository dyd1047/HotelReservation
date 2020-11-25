package hotelreserv.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import common.image.ImageUtil;

public class Start extends Page{
	JPanel can;
	Toolkit kit;
	ImageIcon icon;

	public Start(AdminMain adminMain) {
		super(adminMain);
		URL url = this.getClass().getClassLoader().getResource("res/mainlogo.png");
		icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
		icon.setImage(img);
		
		
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, this);
			}
		};
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				adminMain.showPage(adminMain.USERINFO);
			}
		});
		
		can.setPreferredSize(new Dimension(1200,900));
		add(can);
	}

}
