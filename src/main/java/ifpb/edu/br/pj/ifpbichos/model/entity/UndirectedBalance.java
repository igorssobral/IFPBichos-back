package ifpb.edu.br.pj.ifpbichos.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Formatter;

@Entity(name = "UNDIRECTED_BALANCE")
@Data
public class UndirectedBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private BigDecimal balance;

    public UndirectedBalance(BigDecimal balance){
        this.balance = balance;
    }

    public UndirectedBalance(){

    }
}
