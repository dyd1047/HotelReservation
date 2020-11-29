package hotelreserv.admin;

public class ReservVO {
   private int member_id;
   private String u_id;
   private String u_name;
   private String u_phone;
   private int hotel_id;
   private String h_name;
   private String h_phone;
   private String h_adress;
   private String h_filename;
   private String startdate;
   private String enddate;

   public int getMember_id() {
      return member_id;
   }

   public void setMember_id(int member_id) {
      this.member_id = member_id;
   }

   public String getU_id() {
      return u_id;
   }

   public void setU_id(String u_id) {
      this.u_id = u_id;
   }

   public String getU_name() {
      return u_name;
   }

   public void setU_name(String u_name) {
      this.u_name = u_name;
   }

   public String getU_phone() {
      return u_phone;
   }

   public void setU_phone(String u_phone) {
      this.u_phone = u_phone;
   }

   public int getHotel_id() {
      return hotel_id;
   }

   public void setHotel_id(int hotel_id) {
      this.hotel_id = hotel_id;
   }

   public String getH_name() {
      return h_name;
   }

   public void setH_name(String h_name) {
      this.h_name = h_name;
   }

   public String getH_phone() {
      return h_phone;
   }

   public void setH_phone(String h_phone) {
      this.h_phone = h_phone;
   }

   public String getH_adress() {
      return h_adress;
   }

   public void setH_adress(String h_adress) {
      this.h_adress = h_adress;
   }

   public String getH_filename() {
      return h_filename;
   }

   public void setH_filename(String h_filename) {
      this.h_filename = h_filename;
   }

   public String getStartdate() {
      return startdate;
   }

   public void setStartdate(String startdate) {
      this.startdate = startdate;
   }

   public String getEnddate() {
      return enddate;
   }

   public void setEnddate(String enddate) {
      this.enddate = enddate;
   }

}