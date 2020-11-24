package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import common.image.ImageUtil;

public class RegionSelection extends Page{
		TopMenu topMenu;
		JButton bt_seoul;
	   JButton bt_busan;
	   JPanel p_content;
	   JPanel p_top;
	   JPanel p_top2;
	   JPanel p_top3;
	   JLabel la_top;
	   JLabel la_seoul;
	   JLabel la_busan;
	   
	   
	
	public RegionSelection(ClientMain clientMain) {
		super(clientMain);
		
		p_content = new JPanel();
		p_top = new JPanel();
		p_top2 = new JPanel();
		p_top3 = new JPanel();
		
		
		la_top = new JLabel("원하시는 지역 선택해주세요");
		la_seoul = new JLabel("서울");
		la_busan = new JLabel("경기");
		la_top.setHorizontalAlignment(JLabel.CENTER);
		la_seoul.setHorizontalAlignment(JLabel.CENTER);
		la_busan.setHorizontalAlignment(JLabel.CENTER);
		p_top.setLayout(new BorderLayout());
		
		p_top2.add(la_top);
		p_top2.add(p_top3);
		
		p_top3.add(la_seoul);
		p_top3.add(la_busan);
		p_top.add(p_top2,BorderLayout.NORTH);
		p_top.add(p_content);
		
		p_top3.setLayout(new GridLayout(1,2));
		bt_seoul= new JButton(ImageUtil.getIcon(this.getClass(), "res/btseoul.png", 600, 700));
		bt_busan= new JButton(ImageUtil.getIcon(this.getClass(), "res/busanbt.png", 600, 700));
		p_top2.setPreferredSize(new Dimension(1200, 100));
		la_top.setPreferredSize(new Dimension(1200, 50));
		p_top3.setPreferredSize(new Dimension(1200, 50));
		  p_content.setPreferredSize(new Dimension(1200, 700));
	      p_content.setLayout(new GridLayout(1, 2));
	      p_content.setBackground(Color.WHITE);

		
		p_content.add(bt_seoul);
		p_content.add(bt_busan);
		
		
		
		setLayout(new FlowLayout());
		this.add(p_top);
		
		
	clientMain.navi[2].addActionListener((e)->{
		clientMain.showPage(clientMain.LOGIN);
	});
	

	}

}
