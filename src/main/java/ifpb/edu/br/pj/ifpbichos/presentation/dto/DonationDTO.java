package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class DonationDTO {

	private LocalDateTime date;
	private float donationValue;
	private Boolean isDirected;
	
	

	
	
}
