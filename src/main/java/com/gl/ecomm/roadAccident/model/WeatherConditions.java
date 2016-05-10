package com.gl.ecomm.roadAccident.model;

import java.io.Serializable;

public class WeatherConditions implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4823255988233746192L;
	private Integer code;
	private String label;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

}
