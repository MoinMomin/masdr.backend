package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.enums.InteractionType;
import interaction.cx.masdr.sa.backend.model.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
  /*  List<Interaction> findByType(InteractionType type);
    List<Interaction> findByStatus(String status);
    List<Interaction> findByStartTimeBetween(LocalDateTime start, LocalDateTime end);*/
}