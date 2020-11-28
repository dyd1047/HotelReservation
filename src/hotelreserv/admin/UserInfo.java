package hotelreserv.admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JScrollPane;
import javax.swing.JTable;


public class UserInfo extends Page{
	JTable table;
	UserModel userModel;
	JScrollPane scroll;
	Connection con;
	

	public UserInfo(AdminMain adminMain) {
		super(adminMain);
//		this.setBackground(Color.RED);
		table = new JTable(userModel = new UserModel());
		scroll = new JScrollPane(table);
		
		con = adminMain.getCon();
		
		setLayout(new BorderLayout());
		add(scroll);
		
		getList();
	}
	
	
	public void getList() {
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		String sql = "select *from hotel_member";
		
		try {
			pstmt = con.prepareStatement(sql
					,ResultSet.TYPE_SCROLL_INSENSITIVE
					,ResultSet.CONCUR_READ_ONLY);
			rs= pstmt.executeQuery();
			
			rs.last(); //커서를 제일 마지막으로 보내기
			int total = rs.getRow();
			
			String[][]data = new String[total][userModel.column.length];
			rs.beforeFirst(); //이건 맨꼭대기 rs.First()는 꼭대기에서 밑에
			
			int index=0;
			while(rs.next()) {			
			String[] record = new String[userModel.column.length];
			
			record[0]= rs.getString("Member_id"); //그냥 String취급할수도 있어
			record[1]= rs.getString("mid");
			record[2]= rs.getString("pass");
			record[3]= rs.getString("name");
			record[4]= rs.getString("phone");
			record[5]= rs.getString("email");
			
			//채워진 일차원 배열을 data이차원배열에 순서대로 담자
			data[index++]=record;
		}
		//완성된 이차월 배열을 boardModel이 보유한 data배열에 주소로 대입시켜버리자
			userModel.data = data;
		
		//JTable 다시 ui갱신
			table.updateUI();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}
	
	

}
