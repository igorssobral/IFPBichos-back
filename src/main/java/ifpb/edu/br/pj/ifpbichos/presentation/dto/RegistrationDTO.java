package ifpb.edu.br.pj.ifpbichos.presentation.dto;

public class RegistrationDTO {
	
	private String email;
	private String password;
	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return email;
	}

	public String getPassword() {
	    return password;
	}
	
}
