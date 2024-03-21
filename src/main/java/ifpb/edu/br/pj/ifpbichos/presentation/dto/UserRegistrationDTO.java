package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.enums.ComissionMemberRole;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonatorType;
import ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles;

public record UserRegistrationDTO(
		String name,
		String phoneNumber,
		String email,
		String login,
		String password,
		UserRoles userRole,
		String registration,
		DonatorType donatorType,
		String CPF,
		ComissionMemberRole role
){}