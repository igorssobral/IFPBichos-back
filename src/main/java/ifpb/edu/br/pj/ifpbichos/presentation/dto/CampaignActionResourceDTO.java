package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
public class CampaignActionResourceDTO {

    private Long id;
    private Long campaignId;
    private String justification;
    private String action;
    private BigDecimal cost;
    private LocalDateTime completionDate;
    private byte[] receipt;
    private BigDecimal withdrawalFromUndirectedBalance;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(Long campaignId) {
        this.campaignId = campaignId;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public LocalDateTime getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(LocalDateTime completionDate) {
        this.completionDate = completionDate;
    }

    public byte[] getReceipt() {
        return receipt;
    }

    public void setReceipt(byte[] receipt) {
        this.receipt = receipt;
    }

    public BigDecimal getWithdrawalFromUndirectedBalance() {
        return withdrawalFromUndirectedBalance;
    }

    public void setWithdrawalFromUndirectedBalance(BigDecimal withdrawalFromUndirectedBalance) {
        this.withdrawalFromUndirectedBalance = withdrawalFromUndirectedBalance;
    }
}
