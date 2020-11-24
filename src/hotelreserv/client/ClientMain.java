package hotelreserv.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import hotelreserv.util.db.DBManager;

public class ClientMain extends JFrame{
	public static final int WIDTH=1200;
	public static final int HEIGHT=900;
	//최상위 페이지들
	public static final int START=0;
	public static final int REGIONSELECTION=1;
	public static final int SELECTHOTEL=2;
	public static final int DETAILHOTEL=3;
	public static final int LOGIN=4;
	public static final int REGISTMEMBER=5;
	public static final int MYPAGE=6;

	JPanel p_navi; //웹사이트의 주 메뉴를 포함할 컨테이너 패널
	String[] navi_title = {"HOME","MyPage","Login"};
	public JButton[]navi = new JButton[navi_title.length]; //[][][][][] 배열생성
	
	JPanel p_content;
	JPanel user_container; 
	Page[] page = new Page[7];
	
	private DBManager dbManager;
	private Connection con;
	
	private boolean hasSession= false;
	
	public ClientMain() {
		dbManager = new DBManager();
		p_content = new JPanel();
		user_container = new JPanel();
		

		user_container = new JPanel();
		p_content = new JPanel();
		p_navi = new JPanel();

		con = dbManager.connect(); 
		
		if(con==null) {
			JOptionPane.showMessageDialog(this, "데이터베이스 접속 실패");
		}else {
			this.setTitle("HotelReservation client로 접속 성공");
		}
		
		for(int i =0;i<navi.length;i++) {
			navi[i]=new JButton(navi_title[i]);
			p_navi.add(navi[i]);
		}
		
		//페이지구성
		page[0]= new Start(this);
		page[1]=new RegionSelection(this);
		page[2]=new SelectHotel(this);
		page[3]=new DetailHotel(this);
		page[4]=new Login(this);
		page[5]=new RegistMember(this);
		page[6]=new MyPage(this);
		
		user_container.setPreferredSize(new Dimension(1200,900));
		
		user_container.setLayout(new BorderLayout());
		user_container.add(p_navi,BorderLayout.NORTH);

		for(int i=0;i<page.length;i++) {
			p_content.add(page[i]);
		}

		showPage(0); //처음나올 페이지 설정
		
		user_container.add(p_content); 		//센터에 페이지 부착
		
		add(user_container);
		
		this.setSize(1200, 900);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dbManager.disConnect(con);
			}
		});
		
		//네비게이션 버튼과 리스너연결 
		for(int i=0;i<navi.length;i++) {
			navi[i].addActionListener((e)->{
				Object obj = e.getSource();
				if(obj==navi[0]) { //home
					showPage(0);
				}else if(obj==navi[1]) {
					this.setBackground(Color.BLACK);
				}else if(obj==navi[2]) {
					//로그인을 요청할지, 로그아웃을 요청할지를 구분하자!!
					//hasSession의 값이 true 일때는 로그인한 상태이므로, 로그아웃을 요청해야 한다..
					if(hasSession) {
						int ans = JOptionPane.showConfirmDialog(ClientMain.this, "로그아웃 하시겠습니까?");
						
						if(ans==JOptionPane.OK_OPTION) {//예를 누른것임
							Login loginPage = (Login)page[ClientMain.LOGIN];
							loginPage.logout();		
						}
					}else {
						showPage(LOGIN);//로그인						
					}									
				}
					
			});
		}
		
		
		
	}
	
	public void showPage(int showIndex) { //매개변수로는 보고싶은 페이지 넘버
		for(int i=0;i<page.length;i++) {
			if(i==showIndex) {
				page[i].setVisible(true);				
			}else {
				page[i].setVisible(false);				
			}
		}
	}
	
	
	

	public DBManager getDbManager() {
		return dbManager;
	}


	public Connection getCon() {
		return con;
	}
	
	



	public boolean isHasSession() {
		return hasSession;
	}

	public void setHasSession(boolean hasSession) {
		this.hasSession = hasSession;
	}

	public static void main(String[] args) {
		new ClientMain();
	}

}
