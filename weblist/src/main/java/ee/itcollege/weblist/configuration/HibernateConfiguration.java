package ee.itcollege.weblist.configuration;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class HibernateConfiguration {

    @Bean
    public DataSource getDataSource() {
    	
    	ComboPooledDataSource pooledDataSource = new ComboPooledDataSource();
    	
    	pooledDataSource.setMinPoolSize(2);
    	pooledDataSource.setMaxPoolSize(10);
    	
    	pooledDataSource.setJdbcUrl("jdbc:h2:~/db/test");
    	try {
			pooledDataSource.setDriverClass("org.h2.Driver");
		}
    	catch (PropertyVetoException e) {
			e.printStackTrace();
		}
    	
        return pooledDataSource;
    }
    
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(getDataSource());
        emf.setPackagesToScan(new String[]{"ee.itcollege.weblist.entity"});
        HibernateJpaVendorAdapter hibernateAdapter = new HibernateJpaVendorAdapter();
        hibernateAdapter.setGenerateDdl(true);
        emf.setJpaVendorAdapter(hibernateAdapter);
        return emf;
    }
    
    @Bean
    public DataSourceTransactionManager txManager() {
        return new DataSourceTransactionManager(getDataSource());
    }
    
    @Bean
    public JpaTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory().getObject());
    }
    
    
}
