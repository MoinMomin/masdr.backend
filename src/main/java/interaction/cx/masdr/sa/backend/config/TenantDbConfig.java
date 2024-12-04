package interaction.cx.masdr.sa.backend.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "interaction.cx.masdr.sa.backend.repository", // Adjust to your tenant repository package
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager"
)
public class TenantDbConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory(
            EntityManagerFactoryBuilder builder, @Qualifier("tenantRoutingDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("interaction.cx.masdr.sa.backend.model") // Adjust to your tenant model/entity package
                .persistenceUnit("tenantPU")
                .build();
    }

    @Bean
    public PlatformTransactionManager tenantTransactionManager(
            @Qualifier("tenantEntityManagerFactory") LocalContainerEntityManagerFactoryBean tenantEntityManagerFactory) {
        return new JpaTransactionManager(tenantEntityManagerFactory.getObject());
    }
}
