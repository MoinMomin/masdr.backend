package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AnalysisRepository extends JpaRepository<Analysis, Long> {
   /* Optional<Analysis> findByInteractionId(Long interactionId);*/
}