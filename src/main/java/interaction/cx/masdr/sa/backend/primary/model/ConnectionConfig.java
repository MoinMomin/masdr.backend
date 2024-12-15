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

    @Column(columnDefinition = "json", nullable = false)
    private Map<String, Object> mysql;

    @Column(name = "api_domain", length = 50, nullable = false, unique = true)
    private String apiDomain;

    @Column(name = "xmpp_domain", length = 200, nullable = false)
    private String xmppDomain;

    @Column(name = "signal_domain", length = 150)
    private String signalDomain;

    @Column(columnDefinition = "json", nullable = false)
    private Map<String, Object> xmppConfig;

    @Column(name = "license_key", length = 200, nullable = false)
    private String licenseKey;

    @Column(length = 6, nullable = false)
    private String storage = "oracle";

    @Column(name = "user_max_sessions", nullable = false)
    private Byte userMaxSessions = 3;

    @Column(columnDefinition = "json", nullable = false)
    private Map<String, Object> awsDetails;

    @Column(columnDefinition = "json", nullable = false)
    private Map<String, Object> ociDetails;

    @Column(columnDefinition = "json")
    private Map<String, Object> androidPushDetails;

    @Column(columnDefinition = "json")
    private Map<String, Object> iosPushDetails;

    @Column(columnDefinition = "json")
    private Map<String, Object> webPushDetails;

    @Column(columnDefinition = "json")
    private Map<String, Object> minioDetails;

    @Column(columnDefinition = "json")
    private Map<String, Object> emailConfig;
}
