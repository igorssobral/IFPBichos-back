package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequestDTO {
        private String title;
        private String description;
        private BigDecimal transactionAmount;
        private int installments;
        private String paymentId;
        private String status;
        private String paymentType;
        private String preferenceId;
        private Long CampaignId;
        private String userLogin;
        private String backUrl;
        private Boolean isDirected;

    }

