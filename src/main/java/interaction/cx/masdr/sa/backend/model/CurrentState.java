package interaction.cx.masdr.sa.backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrentState {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long currentStateId;*/
   /* @Id
    private String userId;*/
    @Id
    private String graphId;
    private String data;
}