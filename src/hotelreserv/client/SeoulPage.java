package hotelreserv.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import hotelreserv.admin.HotelVO;

public class SeoulPage extends Page {
	AreaPageDesign areaPageDesign;
	
	public SeoulPage(ClientMain clientMain) {
		super(clientMain);
		areaPageDesign = new AreaPageDesign(clientMain, "서울");
		add(areaPageDesign);
	}

}
