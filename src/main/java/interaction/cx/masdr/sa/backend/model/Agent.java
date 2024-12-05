package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
public class Agent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agentId;

    private String name;
    private String skills;
    private String role;
    private String status;

    @OneToMany(mappedBy = "agent")
    private List<AgentQueueMapping> queueMappings = new ArrayList<>();

    @OneToMany(mappedBy = "agent")
    private List<InteractionAgentMapping> interactionMappings = new ArrayList<>();
}