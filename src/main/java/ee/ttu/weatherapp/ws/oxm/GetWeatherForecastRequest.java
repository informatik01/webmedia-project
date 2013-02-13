package ee.ttu.weatherapp.ws.oxm;

public class GetWeatherForecastRequest {

	private String city;
	private int numberOfDays;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getNumberOfDays() {
		return numberOfDays;
	}

	public void setNumberOfDays(int numberOfDays) {
		this.numberOfDays = numberOfDays;
	}

}
