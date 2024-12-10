package ifpb.edu.br.pj.ifpbichos.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "files")
@Data
public class Files {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "create_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;

    @Column(name = "update_at")
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime updateAt;

    private String fileName;

    private byte[] data;

    private String type;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "campaign_id", referencedColumnName = "id")
    private Campaign campaigns;

    @SuppressWarnings("unused")
    public Files(){

    }

    public Files(String fileName, byte[] data, String type, Campaign campaigns) {
        this.fileName = fileName;
        this.data = data;
        this.type = type;
        this.campaigns = campaigns;
    }

    @PrePersist
    protected  void onCreate(){
        this.createdAt = LocalDateTime.now();
    }


    @PreUpdate
    protected void onUpdate(){
        this.updateAt = LocalDateTime.now();
    }


}
