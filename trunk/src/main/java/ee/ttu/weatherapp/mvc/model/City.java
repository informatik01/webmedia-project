package ee.ttu.weatherapp.mvc.model;

import java.util.Comparator;

public class City {

	private int id;
	private String name;

	public static Comparator<City> CityComparator = new Comparator<City>() {
		
		public int compare(City city1, City city2) {
			return city1.getName().compareTo(city2.getName());
		}
	};
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
	
}
