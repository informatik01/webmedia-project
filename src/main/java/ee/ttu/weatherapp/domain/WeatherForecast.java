package ee.ttu.weatherapp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ee.ttu.weatherapp.domain.WeatherInfo;

public class WeatherForecast {

	private String city;
	private Date date;

	private List<WeatherInfo> weatherInfo;

	public WeatherForecast() {
		this.weatherInfo = new ArrayList<WeatherInfo>();
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<WeatherInfo> getWeatherInfo() {
		return weatherInfo;
	}

	public void setWeatherInfo(List<WeatherInfo> weatherInfo) {
		this.weatherInfo = weatherInfo;
	}

}
