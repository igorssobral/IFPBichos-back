package ifpb.edu.br.pj.ifpbichos.presentation.dto;

public abstract class UserDTO {
	
	private String name;
	private String phoneNumber;
	private String email;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(String name,String phoneNumber, String email) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
