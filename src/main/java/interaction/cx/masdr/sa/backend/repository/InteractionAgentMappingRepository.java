package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.InteractionAgentMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
@Repository
public interface InteractionAgentMappingRepository extends JpaRepository<InteractionAgentMapping, Long> {
   /* List<InteractionAgentMapping> findByAgent_AgentId(Long agentId);  // Changed from findByAgentId
    List<InteractionAgentMapping> findByInteraction_InteractionId(Long interactionId);  // Changed from findByInteractionId
    List<InteractionAgentMapping> findByJoinTimeBetween(LocalDateTime start, LocalDateTime end);*/
}