package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;
import lombok.Data;

@Data
public class ComissionMemberDTO extends UserDTO {
	
	private ComissionMemberRole role;


	public ComissionMemberDTO(String name,String cpf, String phoneNumber, String email,String login, String password, UserRoles userRole, ComissionMemberRole role) {
		super(name,cpf, phoneNumber, email, login, password, userRole);
		this.role = role;
	}

	
	
}
