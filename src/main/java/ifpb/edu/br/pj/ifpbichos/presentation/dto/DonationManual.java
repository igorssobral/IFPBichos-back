package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import java.math.BigDecimal;

public record DonationManual(String title, String description, BigDecimal donationValue, String userLogin) {

}
