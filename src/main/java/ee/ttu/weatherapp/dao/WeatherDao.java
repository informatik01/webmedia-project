package ee.ttu.weatherapp.dao;

import java.util.Date;
import java.util.List;

import ee.ttu.weatherapp.domain.WeatherForecast;
import ee.ttu.weatherapp.domain.WeatherInfo;

public interface WeatherDao {

	public WeatherInfo readWeatherInfo(String cityName, Date date, String partOfDay);

	public List<WeatherInfo> readWeatherInfo(String cityName, Date startDay, Date endDay);
	
	public void insertWeatherInfo(WeatherForecast weatherForecast);
	
	public void updateWeatherInfo(WeatherForecast weatherForecast, int weatherId);
	
	public void deleteWeatherInfo(int weatherId);

}
