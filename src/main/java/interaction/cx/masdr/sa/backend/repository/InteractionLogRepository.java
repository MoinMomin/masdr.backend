package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.InteractionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InteractionLogRepository extends JpaRepository<InteractionLog, Long> {
   /* Optional<InteractionLog> findByInteractionId(Long interactionId);
    List<InteractionLog> findByTranscriptContaining(String keyword);*/
}
