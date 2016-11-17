package com.niit.bloghub.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.niit.bloghub.dao.BlogDAO;
import com.niit.bloghub.dao.UsersDAO;
import com.niit.bloghub.daoimpl.BlogDAOImpl;
import com.niit.bloghub.daoimpl.UsersDAOImpl;
import com.niit.bloghub.models.Blog;
import com.niit.bloghub.models.Users;

@Configuration
@ComponentScan("com.niit.bloghub")
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationContext {

	@Bean(name="dataSource")
	public DataSource getOracleDataSource()
	{
		DriverManagerDataSource datasource=new DriverManagerDataSource();
		datasource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		datasource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		datasource.setUsername("BLOGHUB");
		datasource.setPassword("123456");
		datasource.setConnectionProperties(getHibernateProperties());
		return datasource;	
	}
	
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", "update");
		properties.setProperty("hibernate.show_sql", "true");
		properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		properties.setProperty("hibernate.format_sql", "true");
		return properties;
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource)
	{
		LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Blog.class);
		sessionBuilder.addAnnotatedClass(Users.class);
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager=new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
	@Autowired
	@Bean(name="blogDAO")
	public BlogDAO getBlogDao(SessionFactory sessionFactory)
	{
		return new BlogDAOImpl(sessionFactory);
	}
	
	@Autowired
	@Bean(name="usersDAO")
	public UsersDAO getUsersDao(SessionFactory sessionFactory)
	{
		return new UsersDAOImpl(sessionFactory);
	}
}

