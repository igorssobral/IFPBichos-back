package ifpb.edu.br.pj.ifpbichos.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "notificacoes")
public class Notificacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String mensagem;

    @Column(nullable = false)
    private Boolean lida;

    @ManyToOne
    @JoinColumn(name = "campaign_id")
    private Campaign campaign;


}
