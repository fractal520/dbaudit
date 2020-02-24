package wtsd.dbaudit.Monitor.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "tpcDataSource")
    @Qualifier("tpcDataSource")
    @Primary
    @ConfigurationProperties(prefix="spring.datasource.tpc")
    public DataSource tpcDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "omsDataSource")
    @Qualifier("omsDataSource")
    @ConfigurationProperties(prefix="spring.datasource.oms")
    public DataSource omsDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mpsDataSource")
    @Qualifier("mpsDataSource")
    @ConfigurationProperties(prefix="spring.datasource.mps")
    public DataSource mpsDataSource() {
        return DataSourceBuilder.create().build();
    }

}