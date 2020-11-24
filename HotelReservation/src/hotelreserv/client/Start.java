package hotelreserv.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import common.image.ImageUtil;

public class Start extends Page{
	Image img;
	JPanel can;
	Toolkit kit;

	public Start(ClientMain clientMain) {
		super(clientMain);
		kit = Toolkit.getDefaultToolkit();
		img=ImageUtil.getCustomSize(kit.getImage("C:/Users/user/eclipse-workspace/HotelReservation/res/main.png"),1200, 900);
		
		
		can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, this);
			}
		};
		
		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				clientMain.showPage(clientMain.REGIONSELECTION);
			}
		});
		
		can.setPreferredSize(new Dimension(1200,900));
		add(can);
	}

}
