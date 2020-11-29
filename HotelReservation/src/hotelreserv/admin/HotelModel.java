package hotelreserv.admin;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class HotelModel extends AbstractTableModel{

	//레코드를 담게될 리스트
	ArrayList<HotelVO> record = new ArrayList<HotelVO>(); //생성하지 않으면 getRowCount()메서드에서 
															//널포인터익셉션 발생 데이터가 없으면 0이니까
	
	//컬럼정보를 위한 ArrayList선언
	ArrayList<String> column = new ArrayList<String>();
	
	@Override
	public int getRowCount() {
		return record.size();
	}

	@Override
	public int getColumnCount() {
		return column.size();
	}
	
	@Override
	public String getColumnName(int col) {

		return column.get(col);
	}
	
	private int hotel_id;
	private String area;
	private String hname;
	private String adress;
	private String phone;
	private int price;
	private String filename;

	@Override
	public Object getValueAt(int row, int col) {
		HotelVO vo = record.get(row);
		String obj = null;
		if(col==0) {
			obj = Integer.toString(vo.getHotel_id());
		}else if(col==1) {
			obj = vo.getArea();
		}else if(col==2) {
			obj=vo.getHname();
		}else if(col==3) {
			obj=vo.getAdress();
		}else if(col==4) {
			obj=vo.getPhone();
		}else if(col==5) {
			obj=Integer.toString(vo.getPrice());
		}else if(col==6) {
			obj=vo.getFilename();
		}



		return obj;
	}

}
