package com.gl.ecomm.roadAccident.dao;

import com.gl.ecomm.roadAccident.model.WeatherConditions;

public interface WeatherConditionsDao {
	WeatherConditions findByCode(Integer code);
}
