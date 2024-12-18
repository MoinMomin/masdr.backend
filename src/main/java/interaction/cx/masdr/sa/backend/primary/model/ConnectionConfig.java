package interaction.cx.masdr.sa.backend.primary.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;

@Entity
@Table(name = "connection_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tenantId;

    @Column(columnDefinition = "json", nullable = true)
    private String mysql;

    @Column(name = "api_domain", length = 50, nullable = true, unique = true)
    private String apiDomain;

    @Column(name = "xmpp_domain", length = 200, nullable = true)
    private String xmppDomain;

    @Column(name = "signal_domain", length = 150)
    private String signalDomain;

    @Column(columnDefinition = "json", nullable = true)
    private String xmppConfig;

    @Column(name = "license_key", length = 200, nullable = true)
    private String licenseKey;

    @Column(length = 6, nullable = true)
    private String storage = "oracle";

    @Column(name = "user_max_sessions", nullable = true)
    private Byte userMaxSessions = 3;

    @Column(columnDefinition = "json", nullable = true)
    private String awsDetails;

    @Column(columnDefinition = "json", nullable = true)
    private String ociDetails;

    @Column(columnDefinition = "json")
    private String androidPushDetails;

    @Column(columnDefinition = "json")
    private String iosPushDetails;

    @Column(columnDefinition = "json")
    private String webPushDetails;

    @Column(columnDefinition = "json")
    private String minioDetails;

    @Column(columnDefinition = "json")
    private String emailConfig;
}
