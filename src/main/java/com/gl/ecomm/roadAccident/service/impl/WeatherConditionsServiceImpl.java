package com.gl.ecomm.roadAccident.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.gl.ecomm.roadAccident.mapper.WeatherConditionsMapper;
import com.gl.ecomm.roadAccident.model.WeatherConditions;
import com.gl.ecomm.roadAccident.service.WeatherConditionsService;

public class WeatherConditionsServiceImpl implements WeatherConditionsService {
	@Autowired
	private WeatherConditionsMapper weatherConditionsMapper;

	public WeatherConditionsMapper getWeatherConditionsMapper() {
		return weatherConditionsMapper;
	}

	public void setWeatherConditionsMapper(WeatherConditionsMapper weatherConditionsMapper) {
		this.weatherConditionsMapper = weatherConditionsMapper;
	}

	@Override
	public WeatherConditions getWeatherConditions(Integer code) {
		return weatherConditionsMapper.findWeatherConditions(code);
	}

	@Override
	public void addWeatherConditions(WeatherConditions wc) {
		weatherConditionsMapper.insertWeatherConditions(wc);
	}

}
