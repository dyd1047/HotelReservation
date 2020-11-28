package hotelreserv.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class HotelRegist extends JPanel{
	JPanel p_container;//그리드 적용 예정(7, 2)

	String[] title= {"지역","호텔이름","주소","번호","가격","이미지파일"};
	JLabel[] la_title = new JLabel[title.length];

	JTextField t_area;//브랜드
	JTextField t_hname;//가격
	JTextField t_adress;//이미지
	JTextField t_phone;//상세설명
	JTextField t_price;//상세설명
	JTextField t_filename;//상세설명
	JButton bt_regist;
	JButton bt_list;
	
	
	HotelInfo hotelInfo;
	
	
	public HotelRegist(HotelInfo hotelInfo) {
		this.hotelInfo = hotelInfo;
		
		p_container = new JPanel();
		for(int i=0;i<title.length;i++) {
			la_title[i] = new JLabel(title[i], SwingConstants.RIGHT);
		}

		t_area = new JTextField();
		t_hname = new JTextField();
		t_adress = new JTextField();
		t_phone = new JTextField();
		t_price = new JTextField();
		t_filename = new JTextField();
		bt_regist = new JButton("등록");
		bt_list = new JButton("목록");
		
		
	Dimension d = new Dimension(400,45);
		
		p_container.setBackground(Color.WHITE);
		p_container.setPreferredSize(new Dimension(AdminMain.WIDTH-300, AdminMain.HEIGHT-300));
		for(int i=0;i<title.length;i++) {
			la_title[i].setPreferredSize(d);
		}
	
		t_area.setPreferredSize(d);
		t_hname.setPreferredSize(d);
		t_adress.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_price.setPreferredSize(d);
		t_filename.setPreferredSize(d);
		
		bt_regist.setPreferredSize(new Dimension(300, 40));
		bt_list.setPreferredSize(new Dimension(300, 40));
		
		//조립
		p_container.add(la_title[0]);
		p_container.add(t_area);
		p_container.add(la_title[1]);
		p_container.add(t_hname);
		p_container.add(la_title[2]);
		p_container.add(t_adress);
		p_container.add(la_title[3]);
		p_container.add(t_phone);
		p_container.add(la_title[4]);
		p_container.add(t_price);
		p_container.add(la_title[5]);
		p_container.add(t_filename);
		
		this.add(p_container);//현재 패널에 폼컨테이너 부착
		this.add(bt_regist);//현재 패널에 버튼 부착
		this.add(bt_list);//현재 패널에 버튼 부착
		
		bt_regist.addActionListener((e)->{			
			if(regist()==0) {
				JOptionPane.showMessageDialog(HotelRegist.this,"등록실패");
			}else {
				if(JOptionPane.showConfirmDialog(this, "등록하시겠어요?")==JOptionPane.OK_OPTION) {
					
				JOptionPane.showMessageDialog(HotelRegist.this,"등록성공");
				hotelInfo.addRemoveContent(this,hotelInfo.p_center );
				t_area.setText("");
				t_hname.setText("");
				t_adress.setText("");
				t_phone.setText("");
				t_price.setText("");
				t_filename.setText("");
				
				}
				hotelInfo. getProductList();
			}
		});
		
		bt_list.addActionListener((e)->{
			hotelInfo.addRemoveContent(this,hotelInfo.p_center );
			
			
		});
	
	}
	
	public int regist() {
		PreparedStatement pstmt = null;
		int result = 0; //DML수행이 성공했는지 여부를 알수있는 변수
		
		String sql = "insert into hotelinfo(hotel_id,area,hname";
		sql+=",adress,phone,price,filename) values(seq_product.nextval,?,?,?,?,?,?)";
		
		
		try {
			pstmt=hotelInfo.adminMain.getCon().prepareStatement(sql);
			pstmt.setString(1,t_area.getText());
			pstmt.setString(2, t_hname.getText());
			pstmt.setString(3, t_adress.getText());
			pstmt.setString(4, t_phone.getText());
			pstmt.setString(5, t_price.getText());
			pstmt.setString(6, t_filename.getText());
			
			result = pstmt.executeUpdate(); //DML
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			hotelInfo.adminMain.getDbManager().close(pstmt);
		}
		
		return result;
	}
	

}
