package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
   /* List<Service> findByServiceLevel(String serviceLevel);
    Optional<Service> findByServiceName(String serviceName);
    List<Service> findByParentServiceId(Long parentServiceId);*/
}