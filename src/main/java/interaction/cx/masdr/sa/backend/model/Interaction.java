package interaction.cx.masdr.sa.backend.model;

import interaction.cx.masdr.sa.backend.enums.InteractionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "interactions")
@Getter @Setter @NoArgsConstructor
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;

    @Enumerated(EnumType.STRING)
    private InteractionType type;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String status;
    private String customerFeedback;

    @OneToMany(mappedBy = "interaction")
    private List<InteractionAgentMapping> agentMappings = new ArrayList<>();

    @OneToOne(mappedBy = "interaction", cascade = CascadeType.ALL)
    private InteractionLog log;

    @OneToOne(mappedBy = "interaction", cascade = CascadeType.ALL)
    private Analysis analysis;
}