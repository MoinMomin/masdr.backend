package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "interaction_agent_mappings")
@Getter @Setter @NoArgsConstructor
public class InteractionAgentMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;

    @ManyToOne
    @JoinColumn(name = "interaction_id")
    private Interaction interaction;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    private LocalDateTime joinTime;
    private LocalDateTime leaveTime;
}