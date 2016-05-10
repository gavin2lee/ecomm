package com.gl.ecomm.roadAccident.service;

import com.gl.ecomm.roadAccident.model.WeatherConditions;

public interface WeatherConditionsService {
	WeatherConditions getWeatherConditions(Integer code);
	void addWeatherConditions(WeatherConditions wc);
}
