package interaction.cx.masdr.sa.backend.primary.repository;

import interaction.cx.masdr.sa.backend.mapper.ConnectionMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionConfigRepository extends JpaRepository<ConnectionMapper,Long> {
    public ConnectionMapper findById(long id);
}
