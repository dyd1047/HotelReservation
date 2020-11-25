package hotelreserv.client;

public class HotelMember {

	private int member_id;
	private String mid;
	private String pass;
	private String name;
	private String phone;
	private String email;
	
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getEncryptionPass() {
		return pass;
	}
	public String getDecryptionPass() {
		String de_pass = null;
		for (int i = 0; i < pass.length(); i++) {
			de_pass += (char)(pass.charAt(i) / 3);
		}
		return de_pass;
	}
	public void setEncryptionPass(String pass) {
		String en_pass = null;
		for (int i = 0; i < pass.length(); i++) {
			en_pass += (char)(pass.charAt(i) * 3);
		}
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	

}
