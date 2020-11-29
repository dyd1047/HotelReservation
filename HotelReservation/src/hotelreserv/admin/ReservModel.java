package hotelreserv.admin;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class ReservModel extends AbstractTableModel{

   AdminMain adminMain;
   //레코드를 담게될 리스트
   ArrayList<ReservVO> recordReserv = new ArrayList<ReservVO>(); //생성하지 않으면 getRowCount()메서드에서 
                                             //널포인터익셉션 발생 데이터가 없으면 0이니까
//   ArrayList<HotelMember> recordMember = new ArrayList<HotelMember>(); 
   
   //컬럼정보를 위한 ArrayList선언
   ArrayList<String> column = new ArrayList<String>();
   
   @Override
   public int getRowCount() {
      return recordReserv.size();
   }

   @Override
   public int getColumnCount() {
      return column.size();
   }
   
   @Override
   public String getColumnName(int col) {

      return column.get(col);
   }
   
   private int member_id;
   private int hotel_id;
   private String startDate;
   private String lastDate;

   @Override
   public Object getValueAt(int row, int col) {
      ReservVO vo = recordReserv.get(row);
      String obj = null;
      if(col==0) {
         obj = Integer.toString(vo.getMember_id());
      }else if(col==1) {
         obj = vo.getU_id();
      }else if(col==2) {
         obj = vo.getU_name();
      }else if(col==3) {
         obj = vo.getU_phone();
      }else if(col==4) {
         obj = Integer.toString(vo.getHotel_id());
      }else if(col==5) {
         obj = vo.getH_name();
      }else if(col==6) {
         obj = vo.getH_phone();
      }else if(col==7) {
         obj=vo.getStartdate();
      }else if(col==8) {
         obj=vo.getEnddate();
      }



      return obj;
   }

}