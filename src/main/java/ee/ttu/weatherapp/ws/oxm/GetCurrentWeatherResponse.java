package ee.ttu.weatherapp.ws.oxm;

import ee.ttu.weatherapp.domain.WeatherInfo;

public class GetCurrentWeatherResponse {

	private WeatherInfo weatherInfo;

	public WeatherInfo getWeatherInfo() {
		return weatherInfo;
	}

	public void setWeatherInfo(WeatherInfo weatherInfo) {
		this.weatherInfo = weatherInfo;
	}
}
