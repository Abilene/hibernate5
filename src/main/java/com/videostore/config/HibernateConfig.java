package com.videostore.config;
/**
* herramienta de mapeo objeto-relacional para la plataforma Java que
* facilita el mapeo de atributos entre una base de datos relacional tradicional y el modelo de objetos de una aplicación
* */
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
*  Hibernate configuration with H2 in memory database
 *  @since 12/18/19
* */

@Configuration
@EnableTransactionManagement
/**The annotation @EnableTransactionManagement tells Spring that classes with the @Transactional
 * annotation should be wrapped with the Transactional Aspect. With this the @Transactional is now ready to be used
 * */
public class HibernateConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory(){
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan(
                new String[]{"com.videostore.customer.data.entity"});
        sessionFactoryBean.setHibernateProperties(hibernateProperties());

        return sessionFactoryBean;
    }

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("sa");

        return dataSource;
    }

    @Bean
    public Properties hibernateProperties(){
        Properties hibernateProps = new Properties();
        /*The values create, create-drop, validate, and update
        * basically influence how the schema tool management will manipulate the database schema at startup.
        * */
        hibernateProps.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        hibernateProps.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        hibernateProps.setProperty("show_sql", "true");

        return hibernateProps;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());

        return transactionManager;
    }
}
