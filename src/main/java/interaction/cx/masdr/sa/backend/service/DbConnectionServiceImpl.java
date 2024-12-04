package interaction.cx.masdr.sa.backend.service;

import interaction.cx.masdr.sa.backend.config.DataSourceConfig;
import interaction.cx.masdr.sa.backend.config.TenantRoutingDataSource;
import interaction.cx.masdr.sa.backend.mapper.ConnectionMapper;
import interaction.cx.masdr.sa.backend.primary.repository.ConnectionConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbConnectionServiceImpl implements DbConnectionService{
    @Autowired
    DataSourceConfig dataSourceConfig;
  /*  @Autowired
    @Qualifier("entityManagerFactoryPrimary")
    private EntityManagerFactory entityManagerFactory;*/

    @Autowired
    private TenantRoutingDataSource tenantRoutingDataSource;
    @Autowired
    private ConnectionConfigRepository connectionConfigRepository;
    private static final Logger logger = LoggerFactory.getLogger(DbConnectionServiceImpl.class);
    @Override
    public void addNewDataSource(ConnectionMapper connectionMapper) {
        logger.info("addNewDataSource ..........");
        connectionConfigRepository.save(connectionMapper);
        tenantRoutingDataSource.addDataSource(connectionMapper.getTenantId(), connectionMapper.getUrl(), connectionMapper.getUserName(), connectionMapper.getPassword(),"interaction.cx.masdr.sa.backend.model");
    }
    @Override
    public void configAllDataSources(){
      List<ConnectionMapper>  connectionMapperList= connectionConfigRepository.findAll();
        for (ConnectionMapper connectionMapper:connectionMapperList) {
            tenantRoutingDataSource.addDataSource(connectionMapper.getTenantId(), connectionMapper.getUrl(), connectionMapper.getUserName(), connectionMapper.getPassword(),"interaction.cx.masdr.sa.backend.model");

        }
    }
}
