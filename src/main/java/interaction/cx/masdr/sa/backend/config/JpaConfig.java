package interaction.cx.masdr.sa.backend.config;

import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    @Autowired
    private DataSource tenantRoutingDataSource;
    private static final Logger logger = LoggerFactory.getLogger(JpaConfig.class);

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
        logger.info("JpaConfig.entityManagerFactory.........");
        return builder
                .dataSource(tenantRoutingDataSource)
                .packages("interaction.cx.masdr.sa.backend.model","interaction.cx.masdr.sa.backend.mapper") // Entity package
                .persistenceUnit("tenantPU")
                .build();
    }
    @Bean
    public EntityManagerFactoryBuilder entityManagerFactoryBuilder(JpaVendorAdapter jpaVendorAdapter) {
        // Pass the JpaVendorAdapter and JPA properties

        logger.info("JpaConfig.entityManagerFactoryBuilder.........");
        return new EntityManagerFactoryBuilder(jpaVendorAdapter, getJpaProperties(), null);
    }
    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        logger.info("JpaConfig.jpaVendorAdapter.........");
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setGenerateDdl(true); // for automatic schema generation
        adapter.setShowSql(true); // to show SQL logs
        adapter.setDatabasePlatform("org.hibernate.dialect.MySQL8Dialect"); // specify dialect
        return adapter;
    }
    private Map<String, Object> getJpaProperties() {
        logger.info("JpaConfig.getJpaProperties.........");
        // Here you can define any JPA properties that need to be set for your project
        return Map.of(
                "hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect",
                "hibernate.hbm2ddl.auto", "update",
                "hibernate.show_sql", "true"
        );
    }
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        logger.info("JpaConfig.transactionManager.........");
        return new JpaTransactionManager(entityManagerFactory);
    }
}
