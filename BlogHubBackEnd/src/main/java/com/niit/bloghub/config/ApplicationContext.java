package com.niit.bloghub.config;

import java.util.Properties;

import javax.sql.DataSource;

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
import com.niit.bloghub.daoimpl.BlogDAOImpl;
import com.niit.bloghub.models.Blog;

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
		datasource.setUsername("NISHITDB");
		datasource.setPassword("19051994");
		
		Properties p=new Properties();
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
		p.setProperty("hibernate.hbm2ddl.auto", "update");
		p.setProperty("hibernate.show_sql","true");
		p.setProperty("hibernate.format_sql", "true");
		datasource.setConnectionProperties(p);
		
		return datasource;	
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource ds)
	{
		LocalSessionFactoryBuilder sessionBuilder=new LocalSessionFactoryBuilder(ds);
		sessionBuilder.addAnnotatedClass(Blog.class);
		return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager tm=new HibernateTransactionManager(sessionFactory);
		return tm;
	}
	
	@Autowired
	@Bean(name="blogDAO")
	public BlogDAO getBlogDao(SessionFactory sf)
	{
		return new BlogDAOImpl(sf);
	}
}

