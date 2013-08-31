package ee.ttu.weatherapp.ws.service;

import java.util.List;

import ee.ttu.weatherapp.domain.WeatherForecast;
import ee.ttu.weatherapp.domain.WeatherInfo;

public interface WeatherService {

	public List<String> getCities();

	public WeatherInfo getCurrentWeather(String city);

	public List<WeatherForecast> getWeatherForecast(String city, int numberOfDays);

}
