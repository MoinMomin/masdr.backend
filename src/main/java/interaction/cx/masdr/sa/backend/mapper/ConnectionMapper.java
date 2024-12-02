package interaction.cx.masdr.sa.backend.mapper;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
        @Table(name = "ConnectionConfig")
public class ConnectionMapper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tenantId;
    private String url;
    private String userName;
    private String password;
}
