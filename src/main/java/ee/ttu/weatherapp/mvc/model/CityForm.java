package ee.ttu.weatherapp.mvc.model;

import java.util.List;


public class CityForm {

	private List<City> cities;

	public CityForm() {
		
	}

	public CityForm(List<City> cities) {
		this.cities = cities;
	}
	
	public List<City> getCities() {
		return cities;
	}

	public void setCities(List<City> cities) {
		this.cities = cities;
	}
}
