package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;


public class UserRegistrationDTO {
	//Atributos comuns de todos os tipos de usuário
	private String name;
	private String phoneNumber;
	private String email;
	private String login;
	private String password;
	private UserRoles userRole;
	
	//Atributos exclusivos de donator
	private String registration;
	private DonatorType donatorType;
	
	//Atributos exclusivos de comission member
	private String CPF;
	private ComissionMemberRole role;
	
	public UserRegistrationDTO() {
		
	}
	//Para registrar comission member
	public UserRegistrationDTO(String name, String phoneNumber, String email, String login, String password, 
			UserRoles userRole,  String CPF, ComissionMemberRole role) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
		this.userRole = userRole;
		this.CPF = CPF;
		this.role = role;
	}
	//Para Registrar donator
	public UserRegistrationDTO(String name, String phoneNumber, String email, String login, String password, 
			UserRoles userRole,String registration, DonatorType donatorType) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
		this.userRole = userRole;
		this.donatorType = donatorType;
		this.registration = registration;
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

	public DonatorType getDonatorType() {
		return donatorType;
	}

	public void setDonatorType(DonatorType donatorType) {
		this.donatorType = donatorType;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public ComissionMemberRole getRole() {
		return role;
	}

	public void setRole(ComissionMemberRole role) {
		this.role = role;
	}

	public UserRoles getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRoles userRole) {
		this.userRole = userRole;
	}
	
}
