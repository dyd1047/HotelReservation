package hotelreserv.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import hotelReservation.design.ButtonDesign;

public class RegionSelect extends Page{
	JPanel p_content;
	JButton bt_seoul;
	JButton bt_gyeonggi;

	public RegionSelect(ClientMain clientMain) {
		super(clientMain);
		p_content = new JPanel();
		bt_seoul = new ButtonDesign("서울", Color.WHITE, Color.RED, 400, 250, true, true, false);
		bt_gyeonggi = new ButtonDesign("서울", Color.WHITE, Color.RED, 400, 250, true, true, false);
		
		p_content.setPreferredSize(new Dimension(1200, 700));
		p_content.setLayout(new FlowLayout());
		p_content.setBackground(Color.WHITE);
		this.setBackground(Color.WHITE);
		
		p_content.add(bt_seoul);
		p_content.add(bt_gyeonggi);
		
		add(p_content);
		
	}

}
