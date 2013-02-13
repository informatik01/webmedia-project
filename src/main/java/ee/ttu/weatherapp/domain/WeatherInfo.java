package ee.ttu.weatherapp.domain;

import java.util.Date;

public class WeatherInfo {

	private String weatherType;
	private int minTemp;
	private int maxTemp;
	private int wind;
	private String description;
	private Date date;
	private String city;
	private String partOfDay;


	public String getWeatherType() {
		return weatherType;
	}

	public void setWeatherType(String weatherType) {
		this.weatherType = weatherType;
	}

	public int getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}

	public int getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}

	public int getWind() {
		return wind;
	}

	public void setWind(int wind) {
		this.wind = wind;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPartOfDay() {
		return partOfDay;
	}
	
	public void setPartOfDay(String partOfDay) {
		this.partOfDay = partOfDay;
	}
}
