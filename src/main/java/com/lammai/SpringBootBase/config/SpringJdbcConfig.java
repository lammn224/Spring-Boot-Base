package com.lammai.SpringBootBase.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {
    @Bean
    @Primary
    @ConfigurationProperties("spring.datasource.postgresql")
    public DataSourceProperties postgresqlDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean
    @Primary
    public HikariDataSource postgresqlDataSource(DataSourceProperties postgresqlDataSourceProperties) {
        return postgresqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

    @Bean
    @Primary
    public JdbcTemplate postgresqlJdbcTemplate(@Qualifier("postgresqlDataSource") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

//    @Bean
//    @ConfigurationProperties("spring.datasource.mysql")
//    public DataSourceProperties mysqlDataSourceProperties() {
//        return new DataSourceProperties();
//    }
//
//    @Bean
//    public HikariDataSource mysqlDataSource(DataSourceProperties mysqlDataSourceProperties) {
//        return mysqlDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
//    }
//
//    @Bean
//    public JdbcTemplate secondaryJdbcTemplate(@Qualifier("mysqlDataSource") DataSource dataSource) {
//        return new JdbcTemplate(dataSource);
//    }
}

