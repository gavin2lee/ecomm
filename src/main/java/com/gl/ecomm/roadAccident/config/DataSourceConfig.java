package com.gl.ecomm.roadAccident.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@PropertySource("classpath:datasource.properties")
public class DataSourceConfig {
	public static final String KEY_POOL_TYPE = "connection.pool.type";
	public static final String KEY_DRIVER_CLASS = "jdbc.driverClass";
	public static final String KEY_USER = "jdbc.user";
	public static final String KEY_PASSWORD = "jdbc.password";
	public static final String KEY_URL = "jdbc.url";
	
	@Autowired
	private Environment env;

	@Bean(name = "dataSource")
	@Profile("development")
	public DataSource getDataSourceForDev() {
		ComboPooledDataSource ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(env.getProperty(KEY_DRIVER_CLASS));
			ds.setJdbcUrl(env.getProperty(KEY_URL));
			ds.setUser(env.getProperty(KEY_USER));
			ds.setPassword(env.getProperty(KEY_PASSWORD));
		} catch (PropertyVetoException e) {
			throw new RuntimeException("Failed to create C3P0 datasource.", e);
		}

		return ds;
	}

	@Bean(name = "dataSource")
	@Profile("production")
	public DataSource getDataSourceForProd() {
		return null;
	}
}
