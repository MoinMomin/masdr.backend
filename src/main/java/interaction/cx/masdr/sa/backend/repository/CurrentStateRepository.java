package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.CurrentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrentStateRepository extends JpaRepository<CurrentState,String> {
    public Optional<CurrentState> findById(String tenantId);
}
