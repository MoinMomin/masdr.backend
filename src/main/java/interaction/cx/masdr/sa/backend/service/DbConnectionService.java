package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.mapper.ConnectionMapper;

import java.util.List;

public interface DbConnectionService {
    public void addNewDataSource(ConnectionMapper connectionMapper);
    public void configAllDataSources();
}
