package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String token;
    private String newPassword;
}
