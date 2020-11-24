package hotelreserv.client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;



public class RegistMember extends Page{
	JPanel p_content;
	JTextField t_mid;
	JPasswordField t_pass;
	JTextField t_name;
	JTextField t_phone;
	JTextField t_email;
	JButton bt_regist;
	JButton bt_cancel;
	
	JLabel la_mid;
	JLabel la_pass;
	JLabel la_name;
	JLabel la_phone;
	JLabel la_email;
	
	public RegistMember(ClientMain clientMain) {
		super(clientMain);
		
		p_content = new JPanel();
		
		t_mid = new JTextField();
		t_pass = new JPasswordField();
		t_name = new JTextField();
		t_phone = new JTextField();
		t_email = new JTextField();
		bt_regist = new JButton("회원가입");
		bt_cancel = new JButton("취소");
		
		la_mid = new JLabel("아이디");
		la_pass = new JLabel("비밀번호");
		la_name = new JLabel("이름");
		la_phone = new JLabel("연락처");
		la_email = new JLabel("이메일");
		
		p_content.setPreferredSize(new Dimension(400,350));
		p_content.setBackground(Color.WHITE);
		Dimension d = new Dimension(380,25);
		
		t_mid.setPreferredSize(d);
		t_pass.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_phone.setPreferredSize(d);
		t_email.setPreferredSize(d);
		
		p_content.add(la_mid);
		p_content.add(t_mid);
		p_content.add(la_pass);
		p_content.add(t_pass);
		p_content.add(la_name);
		p_content.add(t_name);
		p_content.add(la_phone);
		p_content.add(t_phone);
		p_content.add(la_email);
		p_content.add(t_email);
		p_content.add(bt_regist);
		p_content.add(bt_cancel);
		
		add(p_content);
		
		bt_regist.addActionListener((e)->{

			if(checkId(t_mid.getText())) {
				JOptionPane.showMessageDialog(RegistMember.this, "중복된 아이디입니다.\n다른 아이디 사용바람");
			}else {
				//메서드 호출하기 전에 ,VO값을 채워야한다
				HotelMember vo = new HotelMember();
				vo.setMid(t_mid.getText());
				vo.setPass(new String(t_pass.getPassword()));
				vo.setName(t_name.getText());
				vo.setPhone(t_phone.getText());
				vo.setEmail(t_email.getText());
				int result = regist(vo);
				if(result == 0) {
					JOptionPane.showMessageDialog(RegistMember.this, "등록실패");
				}else {
					JOptionPane.showMessageDialog(RegistMember.this, "회원가입 성공");
					clientMain.showPage(clientMain.LOGIN);
					
				}
				
			}
			
			
		});
		
		bt_cancel.addActionListener((e)->{
			clientMain.showPage(clientMain.START);
			
		});
	
	}
	
	
	//회원존재 여부 체크
		public boolean checkId(String mid) {
			PreparedStatement pstmt=null;
			ResultSet rs = null;
			boolean flag = false;
			
			//회원테이블에 중복된 아이디가 있는지 여부에 대한 쿼리
			//반환값이 true가 나오면, 회원가입 진행시키면 안됨
			String sql = "select *from hotel_member where mid=?";
			
			try {
				pstmt = clientMain.getCon().prepareStatement(sql);
				pstmt.setString(1,mid);
				rs=pstmt.executeQuery();
				
				flag=rs.next(); //레코드가 존재하면 true, 아니면 false가 대입됨
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				clientMain.getDbManager().close(pstmt,rs);
			}
			return flag;
			
		}
		
		public int regist(HotelMember hotelMember) {
			PreparedStatement pstmt=null;
			int result =0;
			
			String sql = "insert into hotel_member(member_id,mid,pass,name,phone,email)";
			sql+=" values(seq_hotel_member.nextval,?,?,?,?,?)";

			try {
				pstmt = clientMain.getCon().prepareStatement(sql);
				
				//바인드변수 대입
				pstmt.setString(1, hotelMember.getMid());
				pstmt.setString(2, hotelMember.getPass());
				pstmt.setString(3, hotelMember.getName());
				pstmt.setString(4, hotelMember.getPhone());
				pstmt.setString(5, hotelMember.getEmail());
				
				result = pstmt.executeUpdate(); //쿼리 수행
				
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}finally {
				clientMain.getDbManager().close(pstmt);
			}
			
				return result;	
					
		}

}
