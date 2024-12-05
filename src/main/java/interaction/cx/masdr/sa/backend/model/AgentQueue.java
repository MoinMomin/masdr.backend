package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "agent_queues")
@Getter
@Setter
@NoArgsConstructor
public class AgentQueue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long queueId;

    private String queueName;
    private String description;

    @OneToMany(mappedBy = "queue")
    private List<AgentQueueMapping> agentMappings = new ArrayList<>();

    @OneToMany(mappedBy = "queue")
    private List<ServiceAgentQueueMapping> serviceMappings = new ArrayList<>();
}
