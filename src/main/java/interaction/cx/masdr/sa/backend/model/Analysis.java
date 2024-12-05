package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "analyses")
@Getter @Setter @NoArgsConstructor
public class Analysis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long analysisId;

    @OneToOne
    @JoinColumn(name = "interaction_id")
    private Interaction interaction;

    @ElementCollection
    @CollectionTable(name = "analysis_data")
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> analysisData = new HashMap<>();
}