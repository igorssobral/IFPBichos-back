package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import ifpb.edu.br.pj.ifpbichos.model.entity.CampaignActionResource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CampaignActionResourceDTO {

    private Long id;
    private Long campaignId;
    private String justification;
    private String action;
    private BigDecimal cost;
    private LocalDateTime completionDate;
    private byte[] receipt;
    private BigDecimal withdrawalFromUndirectedBalance;


    public CampaignActionResourceDTO(CampaignActionResource campaignActionResource) {
        this.id = campaignActionResource.getId();
        this.campaignId = campaignActionResource.getCampaign().getId();
        this.justification = campaignActionResource.getJustification();
        this.action = campaignActionResource.getAction();
        this.cost = campaignActionResource.getCost();
        this.completionDate = campaignActionResource.getCompletionDate();
        this.receipt = campaignActionResource.getReceipt();
        this.withdrawalFromUndirectedBalance = campaignActionResource.getWithdrawalFromUndirectedBalance();
    }


}
