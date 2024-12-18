package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.mapper.TenantMapper;
import interaction.cx.masdr.sa.backend.primary.repository.ConnectionConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenantServiceImpl implements TenantService{
    @Autowired
    ConnectionConfigRepository connectionConfigRepository;
    @Override
    public List<TenantMapper> getTenantList() {
        return connectionConfigRepository.findAllTenantIds().stream().distinct().map(tenantId->new TenantMapper(tenantId)).toList();
    }
}
