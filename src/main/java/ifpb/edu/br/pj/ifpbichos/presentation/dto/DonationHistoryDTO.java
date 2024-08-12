package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.entity.Donation;
import ifpb.edu.br.pj.ifpbichos.model.enums.DonationPaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DonationHistoryDTO {
    private String titulo;
    private LocalDate date;
    private BigDecimal value;
    private DonationPaymentStatus status;

    public DonationHistoryDTO(Donation donation) {
        this.titulo = donation.getCampaign() != null ? donation.getCampaign().getTitle() : "Doação não direcionada";
        this.date = LocalDate.from(donation.getDate());
        this.value = donation.getDonationValue();
        this.status = donation.getStatus();
    }
}
