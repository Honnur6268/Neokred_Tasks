//package com.pack.connect.multipledb.h2.config;
//
//import java.util.HashMap;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.env.Environment;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import jakarta.persistence.EntityManagerFactory;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(entityManagerFactoryRef = "productEntityManagerFactory", basePackages = {
//		"com.pack.connect.multipledb.h2.repository" },
//transactionManagerRef = "productTransactionManager"
//		)
//public class H2DbConfig {
//
//	@Autowired
//	private Environment env;
//
//	@Primary
//	@Bean(name = "prodcutDataSource")
//	public DataSource dataSource() {
//		return DataSourceBuilder.create().url(env.getProperty("spring.db1.datasource.url"))
//				.driverClassName(env.getProperty("spring.db1.datasource.driver-class-name"))
//				.username(env.getProperty("spring.db1.datasource.username"))
//				.password(env.getProperty("spring.db1.datasource.password")).build();
//	}
//
//	@Primary
//	@Bean(name = "productEntityManagerFactory")
//	public LocalContainerEntityManagerFactoryBean productEntityManagerFactoryBean(EntityManagerFactoryBuilder builder,
//			@Qualifier("prodcutDataSource") DataSource dataSource) {
//
//		HashMap<String, Object> props = new HashMap<>();
//		props.put("hibernate.hbm2ddl.auto", "update");
//
//		return builder.dataSource(dataSource).properties(props).packages("com.pack.connect.multipledb.h2.entities")
//				.persistenceUnit("db1").build();
//	}
//
//	@Primary
//	@Bean(name = "productTransactionManager")
//	public PlatformTransactionManager productTransactionManager(
//			@Qualifier("productEntityManagerFactory") EntityManagerFactory productEntityManagerFactory) {
//
//		return new JpaTransactionManager(productEntityManagerFactory);
//	}
//}
