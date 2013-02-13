package ee.ttu.weatherapp.ws.oxm;

import java.util.List;

import ee.ttu.weatherapp.domain.WeatherForecast;

public class GetWeatherForecastResponse {

	private List<WeatherForecast> weatherForecastlist;

	public List<WeatherForecast> getWeatherForecast() {
		return weatherForecastlist;
	}

	public void setWeatherForecast(List<WeatherForecast> weatherForecast) {
		this.weatherForecastlist = weatherForecast;
	}
}
