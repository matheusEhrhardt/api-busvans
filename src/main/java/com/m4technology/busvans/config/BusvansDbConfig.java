package com.m4technology.busvans.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableJpaRepositories(entityManagerFactoryRef = "busVansEntityManager",
        basePackages = {"com.m4technology.busvans.domain.repository"})
public class BusvansDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "busvans.datasource")
    public DataSource busvansDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean busVansEntityManager(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("busvansDataSource") DataSource dataSource){
        return builder
                .dataSource(dataSource)
                .packages("com.m4technology.busvans.domain.model")
                .build();
    }

    public BusvansSqlQueryConfig sql() throws Exception {
        YamlPropertySourceLoader ypsl = new YamlPropertySourceLoader();
        PropertySource yamlProperties = (PropertySource) ypsl.load("sqls", new ClassPathResource("sqls/Busvans-sql.yml")).get(0);
        Map<String, String> properties = ((Map<String, String>) yamlProperties.getSource());
        Map<String, String> queries = properties.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.toString()));
        return new BusvansSqlQueryConfig(queries);
    }
}
