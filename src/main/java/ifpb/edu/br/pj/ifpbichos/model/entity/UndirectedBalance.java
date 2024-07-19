package ifpb.edu.br.pj.ifpbichos.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "UNDIRECTED_BALANCE")
@Data
public class UndirectedBalance {

    @Id
    private Long id;
    @Column(nullable = false)
    private Double balance;
}
