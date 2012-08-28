package org.scbit.lsbi.renren.service;

import javax.annotation.Resource;

import org.scbit.lsbi.renren.business.WeatherManager;
import org.scbit.lsbi.renren.pojo.TbWeather;
import org.springframework.stereotype.Component;

@Component("weatherService")
public class WeatherService {
	
	private WeatherManager weatherManager;

	public WeatherManager getWeatherManager() {
		return weatherManager;
	}

	@Resource(name="weatherManager")
	public void setWeatherManager(WeatherManager weatherManager) {
		this.weatherManager = weatherManager;
	}
	
	public void save(TbWeather weather) {
		weatherManager.save(weather);
	}
	
	public boolean isExist(String woeid) {
		return weatherManager.isExist(woeid);
	}
	
	public TbWeather getById(String woeid) {
		return weatherManager.getById(woeid);
	}

}
