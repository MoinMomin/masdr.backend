package interaction.cx.masdr.sa.backend.config;
import com.zaxxer.hikari.HikariDataSource;
import interaction.cx.masdr.sa.backend.exception.TenantNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TenantRoutingDataSource extends AbstractRoutingDataSource {
    @Autowired
    DataSourceInitializer dataSourceInitializer;
    @Autowired
    DataSourceConfig dataSourceConfig;
    //private final Map<Object, Object> dataSources = new ConcurrentHashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(TenantRoutingDataSource.class);
    @Override
    protected Object determineCurrentLookupKey() {
        // This is where we dynamically determine which DataSource to use based on the tenant ID
        logger.info("TenantRoutingDataSource.determineCurrentLookupKey.................");
        return TenantContext.getCurrentTenant();
    }
    public void validateTenantDataSource(String tenantId) {
        logger.info("TenantRoutingDataSource.validateTenantDataSource.................");
        if (!dataSourceConfig.dataSources.containsKey(tenantId)) {
            throw new TenantNotFound("Database not available for tenant ID: " + tenantId);
        }
    }
    public void addDataSource(String tenantId, String url, String username, String password,String packageScan) {
        logger.info("TenantRoutingDataSource.addDataSource.................");
        createDatabaseIfNotExists(url, username, password);
        DataSource dataSource = createDataSource(url, username, password);
        dataSourceConfig.dataSources.put(tenantId, dataSource);
        dataSourceInitializer.createSchemaForDataSource(dataSource,tenantId,packageScan);
        setTargetDataSources(dataSourceConfig.dataSources); // Update the available DataSources
        afterPropertiesSet(); // Refresh the DataSource routing configuration
    }

    private DataSource createDataSource(String url, String username, String password) {
        logger.info("TenantRoutingDataSource.createDataSource.................");
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setMaximumPoolSize(10);
        dataSource.setMinimumIdle(5);
        return dataSource;
    }
    private void createDatabaseIfNotExists(String url, String username, String password) {
        try {
            String baseUrl = url.substring(0, url.lastIndexOf("/")); // Extract base URL
            String dbName = url.substring(url.lastIndexOf("/") +1);
            // Extract DB name
            logger.info("TenantRoutingDataSource.createDatabaseIfNotExists.................");
            try (Connection connection = DriverManager.getConnection(baseUrl, username, password);
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to create database: " + e.getMessage(), e);
        }
    }
}
