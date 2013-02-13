package ee.ttu.weatherapp.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WeatherController {

	@RequestMapping(value = "/weather/new", method = RequestMethod.GET)
	public String insertWeather() {
		
		return "weather_insert";
	}
	
	@RequestMapping(value = "/weather/update", method = RequestMethod.GET)
	public String updateWeather() {
		
		return "weather_update";
	}
}
