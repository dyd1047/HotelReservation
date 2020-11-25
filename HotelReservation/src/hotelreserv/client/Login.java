package hotelreserv.client;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import common.image.ImageUtil;

public class Login extends Page {

	JPanel p_content;
	JButton bt_logo;
	JButton bt_kakao;
	JButton bt_facebook;
	JButton bt_naver;
	JPanel p_line;
	JTextField t_id;
	JPasswordField t_pass;
	JButton bt_login;
	JButton bt_regist;
	JLabel la_id;
	JLabel la_pass;
	TopMenu topMenu;
	ImageIcon icon;

	public Login(ClientMain clientMain) {
		super(clientMain);

		topMenu = new TopMenu();
		URL url = this.getClass().getClassLoader().getResource("res/line.png");
		icon = new ImageIcon(url);
		Image img = icon.getImage();
		img = img.getScaledInstance(370, 80, Image.SCALE_SMOOTH);
		icon.setImage(img);

		p_content = new JPanel();
		bt_logo = new JButton(ImageUtil.getIcon(this.getClass(), "res/logo_white.png", 120, 50));
		bt_kakao = new JButton(ImageUtil.getIcon(this.getClass(), "res/kakao.png", 370, 80));
		bt_facebook = new JButton(ImageUtil.getIcon(this.getClass(), "res/facebook.png", 370, 80));
		bt_naver = new JButton(ImageUtil.getIcon(this.getClass(), "res/naver.png", 370, 80));
		p_line = new JPanel() {
			@Override
			public void paint(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, this);
			}
		};
		t_id = new JTextField();
		t_pass = new JPasswordField();
		bt_login = new JButton("로그인");
		bt_regist = new JButton("회원가입");
		la_id = new JLabel("ID");
		la_pass = new JLabel("PASS");

		// 스타일
		this.setBackground(Color.WHITE);
		//Panel
		p_content.setPreferredSize(new Dimension(400, 650));
		p_content.setBackground(Color.WHITE);
		p_content.setBorder(new TitledBorder(new LineBorder(Color.RED, 2), "LOGIN", 0, 0,
				new Font("Verdana", Font.BOLD, 20), Color.BLACK));
		p_line.setPreferredSize(new Dimension(370, 70));
		
		//ID, Pass 입력 관련
		t_id.setPreferredSize(new Dimension(300, 50));
		t_id.setBorder(new LineBorder(Color.BLACK, 2));
		t_id.setFont(new Font("Verdana", Font.BOLD, 16));
		
		t_pass.setPreferredSize(new Dimension(300, 50));
		t_pass.setBorder(new LineBorder(Color.BLACK, 2));
		t_pass.setFont(new Font("Verdana", Font.BOLD, 16));
		
		la_id.setPreferredSize(new Dimension(50, 50));
		la_id.setFont(new Font("Verdana", Font.BOLD, 16));
		
		la_pass.setPreferredSize(new Dimension(50, 50));
		la_pass.setFont(new Font("Verdana", Font.BOLD, 16));
		
		//Button
		bt_logo.setPreferredSize(new Dimension(370, 50));
		bt_logo.setBorderPainted(false);
		bt_logo.setContentAreaFilled(false);
		bt_logo.setFocusPainted(false);
		bt_logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bt_kakao.setBorderPainted(false);
		bt_kakao.setContentAreaFilled(false);
		bt_kakao.setFocusPainted(false);
		bt_kakao.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bt_facebook.setBorderPainted(false);
		bt_facebook.setContentAreaFilled(false);
		bt_facebook.setFocusPainted(false);
		bt_facebook.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bt_naver.setBorderPainted(false);
		bt_naver.setContentAreaFilled(false);
		bt_naver.setFocusPainted(false);
		bt_naver.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bt_login.setPreferredSize(new Dimension(175, 50));
		bt_login.setBackground(Color.BLACK);
		bt_login.setForeground(Color.WHITE);
		bt_login.setFocusPainted(false);
		bt_login.setFont(new Font("Verdaba", Font.BOLD, 14));
		bt_login.setCursor(new Cursor(Cursor.HAND_CURSOR));
		
		bt_regist.setPreferredSize(new Dimension(175, 50));
		bt_regist.setBackground(Color.BLACK);
		bt_regist.setForeground(Color.WHITE);
		bt_regist.setFocusPainted(false);
		bt_regist.setFont(new Font("Verdaba", Font.BOLD, 14));
		bt_regist.setCursor(new Cursor(Cursor.HAND_CURSOR));

		p_content.add(bt_logo);
		p_content.add(bt_kakao);
		p_content.add(bt_facebook);
		p_content.add(bt_naver);
		p_content.add(p_line);
		p_content.add(la_id);
		p_content.add(t_id);
		p_content.add(la_pass);
		p_content.add(t_pass);
		p_content.add(bt_login);
		p_content.add(bt_regist);
		add(p_content);

		bt_logo.addActionListener((e) -> {
			clientMain.showPage(clientMain.START);
		});
		bt_kakao.addActionListener((e) -> {
			JOptionPane.showMessageDialog(this, "그딴거 없음");
		});
		bt_facebook.addActionListener((e) -> {
			JOptionPane.showMessageDialog(this, "그딴거 없음");
		});
		bt_naver.addActionListener((e) -> {
			JOptionPane.showMessageDialog(this, "그딴거 없음");
		});
		
		// 회원가입 버튼과 리스너 연결
		bt_regist.addActionListener((e) -> {
			clientMain.showPage(clientMain.REGISTMEMBER);
		});

		// 로그인 버튼과 리스너 연결
		bt_login.addActionListener((e) -> {
			HotelMember vo = new HotelMember();
			vo.setMid(t_id.getText());
			vo.setEncryptionPass(new String(t_pass.getPassword()));
			validCheck(vo);

		});

		// 키보드 리스너 연결 엔터처리
		t_id.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					HotelMember vo = new HotelMember();
					vo.setMid(t_id.getText());
					vo.setEncryptionPass(new String(t_pass.getPassword()));
					validCheck(vo);
				}
			}
		});
		t_pass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					HotelMember vo = new HotelMember();
					vo.setMid(t_id.getText());
					vo.setEncryptionPass(new String(t_pass.getPassword()));
					validCheck(vo);
				}
			}
		});

	}

	public void validCheck(HotelMember hotelMember) {
		// 유효성 체크 (입력하지 않은 필드가 있는지 여부에 따른 피드백...)
		if (hotelMember.getMid().length() < 1) { // 문자열의 길이가 0이라면
			JOptionPane.showMessageDialog(this, "아이디를 입력하세요");
		} else if (hotelMember.getEncryptionPass().length() < 1) {
			JOptionPane.showMessageDialog(this, "패스워드를 입력하세요");
		} else {
			if (login(hotelMember) == null) {
				JOptionPane.showMessageDialog(this, "로그인 정보가 올바르지 않습니다");

			} else {
				JOptionPane.showMessageDialog(this, "로그인 성공");
				// Home페이지 보여주기
				clientMain.showPage(clientMain.START);
				// 버튼라벨을 로그아웃으로 전환
				clientMain.navi[2].setText("logout");
				clientMain.navi[2].setBackground(Color.GRAY);
				clientMain.navi[2].setForeground(Color.WHITE);
				clientMain.setHasSession(true); // 로그인 상태임을 알수있는 값 true로 바꿔

			}
		}

	}

	// 회원 로그인 처리 메서드 정의: 로그인 성공 후 Home을 보여줄 예정
	// 아래의 메서드가 ShopMember를 반환하므로 만일 로그인 실패한 경우에는
	// null을 반환받아서 간다
	public HotelMember login(HotelMember hotelMember) {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		HotelMember vo = null; // 로그인 성공 후 회원의 모든 정보를 담게될 VO

		String sql = "select *from hotel_member ";
		sql += " where mid=? and pass=?";

		try {
			pstmt = clientMain.getCon().prepareStatement(sql);
			pstmt.setString(1, hotelMember.getMid());
			pstmt.setString(2, hotelMember.getEncryptionPass());

			rs = pstmt.executeQuery();

			// rs.next()가 참이면, 회원이 존재하는 것이므로 로그인으로 인정
			// 회원정보를 vo에 옮겨담자!
			if (rs.next()) {
				vo = new HotelMember();
				vo.setMember_id(rs.getInt("member_id"));
				vo.setMid(rs.getString("mid"));
				vo.setEncryptionPass(rs.getString("Pass"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			clientMain.getDbManager().close(pstmt, rs);
		}

		return vo;

	}

	// 로그아웃처리
	// 1.hasSession의 값을 false 2.버튼 배경색 빼기 3. 버튼의 텍스트 login으로 바꾸기
	public void logout() {
		clientMain.setHasSession(false);
		clientMain.navi[2].setBackground(null);
		clientMain.navi[2].setForeground(null);
		clientMain.navi[2].setText("login");
		clientMain.showPage(clientMain.START);

	}
}
