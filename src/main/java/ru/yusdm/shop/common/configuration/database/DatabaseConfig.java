package ru.yusdm.shop.common.configuration.database;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    public static final String DATA_SOURCE_NAME = "dataSource";

    @Bean(name = DATA_SOURCE_NAME)
    public DataSource dataSource(DatabaseProps databaseProps){

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setUsername(databaseProps.getUser());
        hikariConfig.setPassword(databaseProps.getPassword());
        hikariConfig.setDriverClassName(databaseProps.getDriver());
        hikariConfig.setJdbcUrl(databaseProps.getUrl());
        hikariConfig.setSchema(databaseProps.getSchema());

        return new HikariDataSource(hikariConfig);
    }


    @Bean
    public SpringLiquibase liquibaseConfig(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/db/changelog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource);
        return liquibase;
    }


}
