package hotelreserv.admin;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class HotelInfo extends Page{
	JTable table;
	JScrollPane scroll;
	JButton bt_regist;
	JPanel p_center;
	
	HotelModel hotelModel;
	HotelRegist hotelRegist;

	public HotelInfo(AdminMain adminMain) {
		super(adminMain);
		p_center = new JPanel();
		table = new JTable(new HotelModel());
		scroll = new JScrollPane(table);
		bt_regist = new JButton("상품등록");
		
		
		//등록폼 생성
		hotelRegist = new HotelRegist(this);
		

		setLayout(new BorderLayout());
		
		scroll.setPreferredSize(new Dimension(1150,500));
		
		p_center.add(scroll); 
		p_center.add(bt_regist);
		
		add(p_center);
		
		getProductList();

		
		bt_regist.addActionListener((e)->{
		addRemoveContent(p_center, hotelRegist);
		});		
	
	}
	
	//상품가져오기
		public void getProductList() {
			PreparedStatement pstmt = null;
			ResultSet rs = null;	
			
			String sql = "select *from hotelinfo";
			
			
			try {
				pstmt = adminMain.getCon().prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				//메타정보를 이용하여 ProductModel의 column ArrayList를 채우자
				ResultSetMetaData meta=rs.getMetaData();
				ArrayList<String> columnNames = new ArrayList<String>();
				
				for(int i =1; i<=meta.getColumnCount();i++) {
					String colName =meta.getColumnName(i); //컬럼명 추출
					columnNames.add(colName);
				}
				
				//rs의 레코드를 ProductModel의 record ArrayList에 채우자
				ArrayList<HotelVO> hotelList = new ArrayList<HotelVO>();
				while(rs.next()) {
					HotelVO vo = new HotelVO(); //비어있는 vo를 생성해서 rs의 값들을 채워넣기 위해!
					vo.setHotel_id(rs.getInt("hotel_id"));
					vo.setArea(rs.getString("area"));
					vo.setHname(rs.getString("hname"));
					vo.setAdress(rs.getString("adress"));
					vo.setPhone(rs.getString("phone"));
					vo.setPrice(rs.getInt("price"));
					vo.setFilename(rs.getString("filename"));
					
					hotelList.add(vo); //방금 생성하고 하나의 레코드가 채워진 vo를 ArrayList에 추가하자
				}
				hotelModel = new HotelModel();
				hotelModel.record = hotelList; //레코드 정보 대입
				hotelModel.column=columnNames; //컬럼 정보 대입
				table.setModel(hotelModel); //테이블에 방금 생성한 모델 적용!!
				table.updateUI();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				adminMain.getDbManager().close(pstmt,rs);
			}
			
		}
		
	
	
	//보여질 컨텐트와 가려질 컨텐트를 제어하는 메서드
	public void addRemoveContent(Component removeObj, Component addObj) {
		this.remove(removeObj); //제거될 자
		this.add(addObj);//부착될 자
		((JPanel)addObj).updateUI();
	}

}
