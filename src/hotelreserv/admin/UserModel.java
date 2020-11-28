package hotelreserv.admin;

import javax.swing.table.AbstractTableModel;

public class UserModel extends AbstractTableModel{
	
	String[] column= {"Member_id","MID","PASS","NAME","PHONE","EMAIL"};
	String[][]data = {}; //비어있는 2차원 배열 선언
	

	@Override
	public int getRowCount() { //레코드 수
		return data.length;
	}

	@Override
	public int getColumnCount() { //컬럼 수 
		return column.length;
	}
	
	//컬럼제목을 출력하기 위해선, 이미지원하는 메서드 오버라이드
	@Override
	public String getColumnName(int col) {
		return column[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		return data[row][col];
	}

}
