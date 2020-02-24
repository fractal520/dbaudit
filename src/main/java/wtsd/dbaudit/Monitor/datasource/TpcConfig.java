package wtsd.dbaudit.Monitor.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef="entityManagerFactoryTpc", //实体管理引用
        transactionManagerRef="transactionManagerTpc", //事务管理引用
        basePackages= { "wtsd.dbaudit.Monitor.dao.tpc" }) //设置tpc Repository所在位置

public class TpcConfig {
    //注入TPC数据源
    @Autowired @Qualifier("tpcDataSource")
    private DataSource tpcDataSource;

    @Autowired
    private HibernateProperties hibernateProperties;

    //配置EntityManager实体
    @Primary
    @Bean(name = "entityManagerTpc")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTpc(builder).getObject().createEntityManager();
    }

    //注入Jpa配置实体
    @Autowired
    private JpaProperties jpaProperties;

    //配置EntityManager工厂实体
    @Primary
    @Bean(name = "entityManagerFactoryTpc")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTpc (EntityManagerFactoryBuilder builder) {

        Map<String,Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(),new HibernateSettings());
        return builder
                .dataSource(tpcDataSource)
                .properties(properties)
                .packages("wtsd.dbaudit.Monitor.db.tpc") //设置实体类所在位置
                .persistenceUnit("tpcPersistenceUnit")
                .build();
    }
/*
    获取Jpa配置信息
    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }*/

    //配置事务
    @Primary
    @Bean(name = "transactionManagerTpc")
    public PlatformTransactionManager transactionManagerTpc(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTpc(builder).getObject());
    }

}