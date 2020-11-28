package hotelreserv.client;

import javax.swing.table.AbstractTableModel;

public class MyPageModel extends AbstractTableModel{
	
	String[] column = {"MemberID","ID","이름","번호","HotelID","호텔이름","호텔번호","입실날짜","퇴실날짜"};
	String[][]data = {};

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return column.length;
	}
	
	@Override
	public String getColumnName(int col) {
		return column[col];
	}


	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
