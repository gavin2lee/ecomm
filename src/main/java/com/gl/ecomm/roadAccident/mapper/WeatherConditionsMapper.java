package com.gl.ecomm.roadAccident.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.gl.ecomm.roadAccident.model.WeatherConditions;

public interface WeatherConditionsMapper {
	@Select("select * from weather_conditions where code = #{code}")
	WeatherConditions findWeatherConditions(@Param("code") Integer code);
	
	@Insert("insert into weather_conditions (code, label) values (#{code}, #{label})")
	void insertWeatherConditions(WeatherConditions wc);
}
