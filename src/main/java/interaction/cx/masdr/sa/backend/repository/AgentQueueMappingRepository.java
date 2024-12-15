package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.AgentQueueMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentQueueMappingRepository extends JpaRepository<AgentQueueMapping, Long> {
   /* List<AgentQueueMapping> findByAgent_AgentId(Long agentId);  // Changed from findByAgentId
    List<AgentQueueMapping> findByQueue_QueueId(Long queueId);  // Changed from findByQueueId
    Optional<AgentQueueMapping> findByAgent_AgentIdAndQueue_QueueId(Long agentId, Long queueId); */ // Changed from findByAgentIdAndQueueId
}