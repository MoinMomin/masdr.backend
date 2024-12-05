package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name = "service_agent_queue_mappings")
@Getter @Setter @NoArgsConstructor
public class ServiceAgentQueueMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mappingId;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @ManyToOne
    @JoinColumn(name = "queue_id")
    private AgentQueue queue;
}