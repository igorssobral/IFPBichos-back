package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;

public abstract class UserDTO {
	
	private String name;
	private String CPF;
	private String phoneNumber;
	private String email;
	private String login;
	private String password;
	private UserRoles userRole;
	
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public UserDTO(String name, String CPF, String phoneNumber, String email, String login,String password, UserRoles userRole) {
		this.name = name;
		this.CPF = CPF;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
		this.userRole = userRole;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}

	public void setCPF(String CPF) {
		this.CPF = CPF;
	}

	public String getCPF() {
		return CPF;
	}
}
