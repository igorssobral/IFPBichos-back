package ifpb.edu.br.pj.ifpbichos.presentation.dto;

public record LoginResponseDTO(String token, String user, ifpb.edu.br.pj.ifpbichos.model.enums.UserRoles userRole) {
}