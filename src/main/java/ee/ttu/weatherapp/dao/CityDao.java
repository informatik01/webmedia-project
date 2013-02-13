package ee.ttu.weatherapp.dao;

import java.util.List;

import ee.ttu.weatherapp.mvc.model.City;

public interface CityDao {

	public List<City> read();
	
	public void insert(String[] cities);
	
	public void update(List<City> cityList);
	
	public void delete(int cityId);
	
	public void delete(String[] cityIds);
	
}
