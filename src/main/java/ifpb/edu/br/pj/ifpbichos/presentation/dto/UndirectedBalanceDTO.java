package ifpb.edu.br.pj.ifpbichos.presentation.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class UndirectedBalanceDTO {
    private BigDecimal balance;

    public UndirectedBalanceDTO(BigDecimal balance){
        this.balance = balance;
    }
}
