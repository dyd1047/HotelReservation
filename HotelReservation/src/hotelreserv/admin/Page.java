package hotelreserv.admin;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	AdminMain adminMain;
	
	public Page(AdminMain adminMain) {
		this.adminMain = adminMain;
		this.setPreferredSize(new Dimension(1200,900));
	}

}
