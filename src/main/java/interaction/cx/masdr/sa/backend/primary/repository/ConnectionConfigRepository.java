package interaction.cx.masdr.sa.backend.primary.repository;

import interaction.cx.masdr.sa.backend.mapper.ConnectionMapper;
import interaction.cx.masdr.sa.backend.primary.model.ConnectionConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConnectionConfigRepository extends JpaRepository<ConnectionConfig,Long> {
    public ConnectionConfig findById(long id);
    public ConnectionConfig findBytenantId(String id);
    @Query("SELECT DISTINCT c.tenantId FROM ConnectionConfig c")
    List<String> findAllTenantIds();
}
