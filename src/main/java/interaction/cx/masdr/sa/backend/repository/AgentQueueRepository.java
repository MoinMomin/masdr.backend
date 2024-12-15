package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.AgentQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentQueueRepository extends JpaRepository<AgentQueue, Long> {
    Optional<AgentQueue> findByQueueName(String queueName);
    List<AgentQueue> findByDescription(String description);
}
