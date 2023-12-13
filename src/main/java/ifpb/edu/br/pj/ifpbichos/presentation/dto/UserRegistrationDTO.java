package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;

public class UserRegistrationDTO {
	
	private String name;
	private String phoneNumber;
	private String email;
	private String login;
	private String password;
	private String registration;
	private DonatorType donatorType;
	
	public UserRegistrationDTO() {
		
	}
	
	public UserRegistrationDTO(String name, String phoneNumber, String email, String login, String password,
			String registration,	DonatorType donatorType) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.login = login;
		this.password = password;
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
	
}
