package com.ticketing.solution.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.jdbc.support.JdbcTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
@RequiredArgsConstructor
public class DataSourceConfiguration {

    private final DataSource dataSource;

    @PostConstruct
    public void init() {
        initializeDatabase();
    }

    private void initializeDatabase() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-drop-mysql.sql"));
        populator.addScript(new ClassPathResource("/org/springframework/batch/core/schema-mysql.sql"));
        populator.execute(dataSource);
    }

    @Bean
    public JdbcTransactionManager transactionManager(DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }
}
