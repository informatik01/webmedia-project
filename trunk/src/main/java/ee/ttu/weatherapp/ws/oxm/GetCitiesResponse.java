package ee.ttu.weatherapp.ws.oxm;

import java.util.List;

public class GetCitiesResponse {

	private List<String> cities;

	public List<String> getCities() {
		return cities;
	}

	public void setCities(List<String> cities) {
		this.cities = cities;
	}
}
