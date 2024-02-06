package com.pack.connect.multipledb.mysql.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "userEntityManagerFactory", basePackages = {
		"com.pack.connect.multipledb.mysql.repository" }, transactionManagerRef = "userTransactionManager")
public class MySqlDbConfig {

	@Autowired
	private Environment env;

	@Bean(name = "userDataSource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().url(env.getProperty("spring.db2.datasource.url"))
				.driverClassName(env.getProperty("spring.db2.datasource.driver-class-name"))
				.username(env.getProperty("spring.db2.datasource.username"))
				.password(env.getProperty("spring.db2.datasource.password")).build();
	}

	@Bean(name = "userEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean userEntityManagerFactoryBean(EntityManagerFactoryBuilder builder,
			@Qualifier("userDataSource") DataSource dataSource) {

		HashMap<String, Object> props = new HashMap<>();
		props.put("hibernate.hbm2ddl.auto", "update");

		return builder.dataSource(dataSource).properties(props).packages("com.pack.connect.multipledb.mysql.entities")
				.persistenceUnit("db2").build();
	}

	@Bean(name = "userTransactionManager")
	public PlatformTransactionManager userTransactionManager(
			@Qualifier("userEntityManagerFactory") EntityManagerFactory userEntityManagerFactory) {

		return new JpaTransactionManager(userEntityManagerFactory);
	}
}
