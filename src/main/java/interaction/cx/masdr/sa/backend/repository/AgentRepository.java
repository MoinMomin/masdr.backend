package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
    List<Agent> findByStatus(String status);
    List<Agent> findBySkillsContaining(String skill);
    Optional<Agent> findByName(String name);
}