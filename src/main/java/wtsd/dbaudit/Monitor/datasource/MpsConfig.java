package wtsd.dbaudit.Monitor.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        entityManagerFactoryRef="entityManagerFactoryMps", //实体管理引用
        transactionManagerRef="transactionManagerMps", //事务管理引用
        basePackages= { "wtsd.dbaudit.Monitor.dao.mps" }) //mps Repository所在位置

public class MpsConfig {
    //注入Mps数据源
    @Autowired @Qualifier("mpsDataSource")
    private DataSource mpsDataSource;

    @Autowired
    private HibernateProperties hibernateProperties;

    //配置EntityManager实体
    @Bean(name = "entityManagerMps")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryMps(builder).getObject().createEntityManager();
    }

    //注入Jpa配置实体
    @Autowired
    private JpaProperties jpaProperties;

    //配置EntityManager工厂实体
    @Bean(name = "entityManagerFactoryMps")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryMps (EntityManagerFactoryBuilder builder) {

        Map<String,Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(),new HibernateSettings());
        return builder
                .dataSource(mpsDataSource)
                .properties(properties)
                .packages("wtsd.dbaudit.Monitor.db.mps") //设置实体类所在位置
                .persistenceUnit("mpsPersistenceUnit")
                .build();
    }
/*
    获取Jpa配置信息
    private Map<String, String> getVendorProperties(DataSource dataSource) {
        return jpaProperties.getHibernateProperties(dataSource);
    }*/

    //配置事务
    @Bean(name = "transactionManagerMps")
    public PlatformTransactionManager transactionManagerMps(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryMps(builder).getObject());
    }

}