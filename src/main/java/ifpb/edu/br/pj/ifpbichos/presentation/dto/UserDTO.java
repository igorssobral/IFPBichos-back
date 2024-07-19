package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import lombok.Data;

@Data
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


}
