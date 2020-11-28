package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import hotelreserv.admin.HotelVO;


public class MyPageItem extends JPanel{
	JPanel p_can;
	JPanel p_la;
	
	JLabel la_area;
	JLabel la_hname;
	JLabel la_adress;
	JLabel la_phone;
	JLabel la_price;
	
	
	HotelVO vo;
	Image img;
	Object hotelPage;
	
	public MyPageItem(Object hotelPage, HotelVO vo,int width,int height) {
		this.hotelPage = hotelPage;
		this.vo = vo;
		try {
			URL url = new URL(vo.getFilename());
			img = ImageIO.read(url); //원본크기의 이미지가 오므로, 크기 조절이 필요하다
			img = img.getScaledInstance(width/4, height,Image.SCALE_SMOOTH);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		p_can = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(img, 0, 0, p_can);
			}
		};
		p_la = new JPanel();
	
		la_area = new JLabel("지역 : "+vo.getArea());
		la_hname = new JLabel("이름 : "+vo.getHname());
		la_adress = new JLabel("주소 : "+vo.getAdress());
		la_phone = new JLabel("연락쳐 : "+vo.getPhone());
		la_price = new JLabel("가격 : "+Integer.toString(vo.getPrice()));
		
		//스타일
		this.setLayout(new BorderLayout(10,10));
		this.setPreferredSize(new Dimension(width,height));
		this.setBorder(new LineBorder(Color.BLACK, 2));
		this.setBackground(Color.WHITE);
		
		p_can.setPreferredSize(new Dimension(width/4,height));
		p_can.setBackground(Color.WHITE);
		p_la.setLayout(new GridLayout(5,1));
		p_la.setBackground(Color.WHITE);
		
		la_area.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		la_hname.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		la_adress.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		la_phone.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		la_price.setFont(new Font("맑은 고딕", Font.BOLD, 14));
		
		p_la.add(la_area);
		p_la.add(la_hname);
		p_la.add(la_adress);
		p_la.add(la_phone);
		p_la.add(la_price);
		
		add(p_can, BorderLayout.WEST);
		add(p_la);
		
	}

}
