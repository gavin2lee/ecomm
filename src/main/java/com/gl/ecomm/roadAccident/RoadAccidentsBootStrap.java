package com.gl.ecomm.roadAccident;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gl.ecomm.roadAccident.model.WeatherConditions;
import com.gl.ecomm.roadAccident.service.WeatherConditionsService;

public class RoadAccidentsBootStrap {

	public static void main(String[] args) {
		// System.setProperty("spring.profiles.active", "dev");
		// ClassPathXmlApplicationContext ctx = new
		// ClassPathXmlApplicationContext("road-accidents/config/spring-data-source.xml","road-accidents/config/spring-application-context.xml");

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext();
		ctx.getEnvironment().setActiveProfiles("dev");
		ctx.setConfigLocations("road-accidents/config/spring-data-source.xml",
				"road-accidents/config/spring-application-context.xml");
		ctx.refresh();

		WeatherConditionsService wcs = ctx.getBean("weatherConditionsService", WeatherConditionsService.class);

		WeatherConditions wc = wcs.getWeatherConditions(1);

		System.out.println("WC:" + wc.getLabel());

		WeatherConditions wc1 = new WeatherConditions();
		wc1.setCode(1);
		wc1.setLabel("aaaa");

		//wcs.addWeatherConditions(wc1);

		WeatherConditions wc2 = wcs.getWeatherConditions(1);

		System.out.println("WC:" + wc2.getLabel());

		((ClassPathXmlApplicationContext) ctx).close();
	}

}
