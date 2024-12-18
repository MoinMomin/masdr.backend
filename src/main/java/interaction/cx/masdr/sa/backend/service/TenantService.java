package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.mapper.TenantMapper;

import java.util.List;

public interface TenantService {
    public List<TenantMapper> getTenantList();
}
