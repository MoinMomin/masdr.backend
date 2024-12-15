package interaction.cx.masdr.sa.backend.repository;

import interaction.cx.masdr.sa.backend.model.ServiceAgentQueueMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServiceAgentQueueMappingRepository extends JpaRepository<ServiceAgentQueueMapping, Long> {
   /* List<ServiceAgentQueueMapping> findByService_ServiceId(Long serviceId);  // Changed from findByServiceId
    List<ServiceAgentQueueMapping> findByQueue_QueueId(Long queueId);  // Changed from findByQueueId
    Optional<ServiceAgentQueueMapping> findByService_ServiceIdAndQueue_QueueId(Long serviceId, Long queueId); */ // Changed from findByServiceIdAndQueueId
}