package agjs.common.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("agjs.*.impl")
@EnableTransactionManagement
public class SpringConfig {

	@Bean
	public DataSource dataSource() {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/jdbc/AGJS");
		bean.setProxyInterface(DataSource.class);

		try {
			bean.afterPropertiesSet();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);
		}

		return (DataSource) bean.getObject();
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {

		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("agjs.bean");
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		props.setProperty("hibernate.current_session_context_class",
				"org.springframework.orm.hibernate5.SpringSessionContext");
		props.setProperty("hibernate.show_sql", "true");
		builder.addProperties(props);
		return builder.buildSessionFactory();
	}

}
