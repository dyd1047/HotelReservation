package hotelreserv.client;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	ClientMain clientMain;
	
	public Page(ClientMain clientMain) {
		this.clientMain = clientMain;
		this.setPreferredSize(new Dimension(1200,900));
	}

}
