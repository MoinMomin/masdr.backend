package interaction.cx.masdr.sa.backend.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import interaction.cx.masdr.sa.backend.config.DataSourceConfig;
import interaction.cx.masdr.sa.backend.config.TenantRoutingDataSource;
import interaction.cx.masdr.sa.backend.exception.AlreadyAdded;
import interaction.cx.masdr.sa.backend.mapper.ConnectionMapper;
import interaction.cx.masdr.sa.backend.primary.model.ConnectionConfig;
import interaction.cx.masdr.sa.backend.primary.repository.ConnectionConfigRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
        ConnectionConfig connectionConfig=connectionConfigRepository.findBytenantId(connectionMapper.getTenantId());
        if(connectionConfig==null) {
            ConnectionConfig connectionConfig1=new ConnectionConfig();
            try {
                String  mysql = new ObjectMapper().writeValueAsString(connectionMapper);
                connectionConfig1.setMysql(mysql);
                connectionConfig1.setTenantId(connectionMapper.getTenantId());
                logger.info("addNewDataSource ..........");
                connectionConfigRepository.save(connectionConfig1);
                tenantRoutingDataSource.addDataSource(connectionMapper.getTenantId(), connectionMapper.getUrl(), connectionMapper.getUserName(), connectionMapper.getPassword(), "interaction.cx.masdr.sa.backend.model");

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
          }else{
            throw new AlreadyAdded("already addded configuration with this tenantId");
        }
        }
    @Override
    public void configAllDataSources(){
      List<ConnectionConfig>  connectionConfigs= connectionConfigRepository.findAll();
        for (ConnectionConfig connectionConfig:connectionConfigs) {
            try {
                try {
                    ConnectionMapper connectionMapper = new ObjectMapper().readValue(connectionConfig.getMysql(), ConnectionMapper.class);
                    tenantRoutingDataSource.addDataSource(connectionMapper.getTenantId(), connectionMapper.getUrl(), connectionMapper.getUserName(), connectionMapper.getPassword(), "interaction.cx.masdr.sa.backend.model");

                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
             }catch (RuntimeException re){
                logger.error(" error during add Data Sources "+connectionConfig.getTenantId());
            }
        }
    }
}
