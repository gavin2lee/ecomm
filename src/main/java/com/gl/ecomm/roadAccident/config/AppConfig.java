package com.gl.ecomm.roadAccident.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gl.ecomm.roadAccident.mapper.WeatherConditionsMapper;

@Configuration
public class AppConfig {
	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Bean
	public SqlSessionFactory getSqlSessionFactory() {
		SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
		factory.setDataSource(dataSource);

		try {
			return factory.getObject();
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	@Bean
	public MapperFactoryBean getWeatherConditionsMapper() {
		MapperFactoryBean mapperFactory = new MapperFactoryBean();
		mapperFactory.setSqlSessionFactory(getSqlSessionFactory());
		mapperFactory.setMapperInterface(WeatherConditionsMapper.class);
		//TODO
		return null;
	}

}
