package ee.ttu.weatherapp.ws.endpoint;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import ee.ttu.weatherapp.domain.WeatherForecast;
import ee.ttu.weatherapp.domain.WeatherInfo;
import ee.ttu.weatherapp.ws.oxm.GetCitiesRequest;
import ee.ttu.weatherapp.ws.oxm.GetCitiesResponse;
import ee.ttu.weatherapp.ws.oxm.GetCurrentWeatherRequest;
import ee.ttu.weatherapp.ws.oxm.GetCurrentWeatherResponse;
import ee.ttu.weatherapp.ws.oxm.GetWeatherForecastRequest;
import ee.ttu.weatherapp.ws.oxm.GetWeatherForecastResponse;
import ee.ttu.weatherapp.ws.service.WeatherService;

@Endpoint
public class WeatherServiceEndpoint {
	
	private static final Logger logger = Logger.getLogger(WeatherServiceEndpoint.class);
	
	private static final String namespaceURI = "http://ttu.ee/weatherapp/ws/schemas";

	@Resource(name = "weatherInfoService")
	private WeatherService weatherService;

	@PayloadRoot(localPart = "GetCitiesRequest", namespace = namespaceURI)
	@ResponsePayload
	public GetCitiesResponse getCities(@RequestPayload GetCitiesRequest request) {

		List<String> citiesList = null;
		try {
			citiesList = weatherService.getCities();
		} catch (Exception e) {
			logger.error("Error connecting to WeatherAppService operation GetCities.", e);
		}

		GetCitiesResponse response = new GetCitiesResponse();
		response.setCities(citiesList);

		return response;
	}

	@PayloadRoot(localPart = "GetCurrentWeatherRequest", namespace = namespaceURI)
	@ResponsePayload
	public GetCurrentWeatherResponse getCurrentWeather(
			@RequestPayload GetCurrentWeatherRequest request) {

		WeatherInfo weatherInfo = null;

		try {
			weatherInfo = weatherService.getCurrentWeather(request.getCity());
		} catch (Exception e) {
			logger.error("Error connecting to WeatherAppService operation GetCurrentWeather.", e);
		}

		GetCurrentWeatherResponse response = new GetCurrentWeatherResponse();
		response.setWeatherInfo(weatherInfo);

		return response;
	}

	@PayloadRoot(localPart = "GetWeatherForecastRequest", namespace = namespaceURI)
	@ResponsePayload
	public GetWeatherForecastResponse getWeatherForecast(
			@RequestPayload GetWeatherForecastRequest request) {

		List<WeatherForecast> weatherForecastList = null;
		try {
			weatherForecastList = weatherService.getWeatherForecast(
					request.getCity(), request.getNumberOfDays());
		} catch (Exception e) {
			logger.error("Error connecting to WeatherAppService operation GetWeatherForecast.");
		}

		GetWeatherForecastResponse response = new GetWeatherForecastResponse();
		response.setWeatherForecast(weatherForecastList);

		return response;
	}
}
