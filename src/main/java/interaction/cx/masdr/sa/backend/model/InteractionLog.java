package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "interaction_logs")
@Getter @Setter @NoArgsConstructor
public class InteractionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @OneToOne
    @JoinColumn(name = "interaction_id")
    private Interaction interaction;

    @Column(columnDefinition = "TEXT")
    private String transcript;
    private String recordingUrl;
}