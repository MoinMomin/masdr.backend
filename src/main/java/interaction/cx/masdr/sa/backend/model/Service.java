package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "services")
@Getter
@Setter
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    private String serviceName;
    private String description;
    private String serviceLevel;
    private Long parentServiceId;

    @OneToMany(mappedBy = "service")
    private List<ServiceAgentQueueMapping> queueMappings = new ArrayList<>();

    @OneToMany(mappedBy = "parentService")
    private List<Service> subServices = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "parent_service_id")
    private Service parentService;
}