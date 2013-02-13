package ee.ttu.weatherapp.mvc.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ee.ttu.weatherapp.dao.impl.JdbcCityDao;
import ee.ttu.weatherapp.mvc.model.City;
import ee.ttu.weatherapp.mvc.model.CityForm;

@Controller
public class CityController {
	
	@Resource(name = "jdbcCityDao")
	private JdbcCityDao cityDao;

	@ModelAttribute("cityForm")
	public CityForm populateCitiyList() {

		List<City> cities = cityDao.read();
		Collections.sort(cities, City.CityComparator);
		CityForm cityForm = new CityForm(cities);

		return cityForm;
	}

	@RequestMapping(value = "cities", method = RequestMethod.GET)
	public String showCities() {

		return "cities";
	}

	@RequestMapping(value = "cities/update", method = RequestMethod.GET)
	public String editCity(Model model) {

		return "cities_update";
	}

	@RequestMapping(value = "cities/update", method = RequestMethod.POST)
	public String updateCity(@ModelAttribute("cityForm") CityForm cityForm, HttpServletRequest request) {

		String[] checkedCitiesIds = request.getParameterValues("checkedCities");

		if (checkedCitiesIds != null) {
			List<City> updatedCityList = new ArrayList<City>();
			List<String> checkedIdList = Arrays.asList(checkedCitiesIds);
 			
			for(City city: cityForm.getCities()) {
				if (checkedIdList.contains(Integer.toString(city.getId()))) {
					updatedCityList.add(city);
				} 
			}
			
			if (request.getParameter("update") != null) {
				cityDao.update(updatedCityList);
			} else if (request.getParameter("delete") != null) {
				cityDao.delete(checkedCitiesIds);
			}
		}

		return "redirect:../cities";
	}

	@RequestMapping(value = "cities/new", method = RequestMethod.GET)
	public String insertCity() {
		
		return "cities_insert";
	}
	
	@RequestMapping(value = "cities/new", method = RequestMethod.POST)
	public String addCity(HttpServletRequest request) {

		String[] newCities = null;
		String citiesString = request.getParameter("new_cities");
		if (citiesString != null) {
			newCities = citiesString.split("[\\r?\\n,;]+");
		}
		for (int i = 0; i < newCities.length; i++) {
			newCities[i] = newCities[i].trim();
		}
		
		List<City> oldCities = cityDao.read();
		List<String> added = new ArrayList<String>();
		boolean isAlready = false;
		if (oldCities != null) {
			for (String newCity: newCities) {
				for (City oldCity: oldCities) {
					if (newCity.equalsIgnoreCase(oldCity.getName())) {
						isAlready = true;
						break;
					}
				}
				
				if (isAlready == false && newCity.matches("(\\p{L}+\\s*-?\\s*)+")) {
					added.add(newCity);
				} else {
					isAlready = false;
				}
			}
			
			// Making nice city names: first letter upper case, the rest are lower case
			for (int i = 0; i < added.size(); i++) {
				String cityName = added.get(i).toLowerCase();
				String niceCityName = cityName.substring(0, 1).toUpperCase() + cityName.substring(1);
				added.set(i, niceCityName);
			}
		}
		
//		for(String city: added) {
//			System.out.println("city: " + city);
//		}
		
		String[] addedCities = Arrays.copyOf(added.toArray(), added.size(), String[].class);
		cityDao.insert(addedCities);
		return "redirect:../cities";
	}
}
