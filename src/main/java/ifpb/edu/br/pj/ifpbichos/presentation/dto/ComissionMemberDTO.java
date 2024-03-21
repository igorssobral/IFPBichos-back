package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;

public class ComissionMemberDTO extends UserDTO {
	
	private ComissionMemberRole role;

	public ComissionMemberDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComissionMemberDTO(String name,String cpf, String phoneNumber, String email,String login, String password, UserRoles userRole, ComissionMemberRole role) {
		super(name,cpf, phoneNumber, email, login, password, userRole);

		this.role = role;
	}





	public ComissionMemberRole getRole() {
		return role;
	}
	public void setRole(ComissionMemberRole role) {
		this.role = role;
	}
	
	
}
