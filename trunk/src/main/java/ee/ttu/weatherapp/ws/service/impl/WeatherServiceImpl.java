package ee.ttu.weatherapp.ws.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ee.ttu.weatherapp.dao.impl.JdbcCityDao;
import ee.ttu.weatherapp.dao.impl.JdbcWeatherDao;
import ee.ttu.weatherapp.domain.WeatherForecast;
import ee.ttu.weatherapp.domain.WeatherInfo;
import ee.ttu.weatherapp.mvc.model.City;
import ee.ttu.weatherapp.ws.service.WeatherService;

@Service("weatherInfoService")
public class WeatherServiceImpl implements WeatherService {

	@Resource(name = "jdbcWeatherDao")
	private JdbcWeatherDao weatherDao;
	
	@Resource(name = "jdbcCityDao")
	private JdbcCityDao cityDao;

	@Override
	public List<String> getCities() {

		List<City> cityDataList = cityDao.read();

		List<String> cityNameList = new ArrayList<String>();
		for(City city: cityDataList) {
			cityNameList.add(city.getName());
		}
		
		return cityNameList;
	}

	@Override
	public WeatherInfo getCurrentWeather(String city) {

		Calendar cal = Calendar.getInstance();
		int currentHour = cal.get(Calendar.HOUR_OF_DAY);

		String partOfDay = null;
		if (currentHour >= 0 && currentHour < 6) {
			partOfDay = "night";
		} else if (currentHour >= 6 && currentHour < 12) {
			partOfDay = "morning";
		} else if (currentHour >= 12 && currentHour < 18) {
			partOfDay = "afternoon";
		} else if (currentHour >= 18) {
			partOfDay = "evening";
		}
		
		WeatherInfo weatherInfo = weatherDao.readWeatherInfo(city, cal.getTime(), partOfDay);

		return weatherInfo;
	}

	@Override
	public List<WeatherForecast> getWeatherForecast(String city,
			int numberOfDays) {

		Calendar cal = Calendar.getInstance();
		Date startDate = cal.getTime();
		cal.add(Calendar.DAY_OF_MONTH, numberOfDays);
		Date endDate = cal.getTime();
		
		List<WeatherInfo> weatherInfoList = weatherDao.readWeatherInfo(city, startDate, endDate);

		List<WeatherForecast> weatherForecastList = new ArrayList<WeatherForecast>();
		for (int i = 0; i < numberOfDays; i++) {
			WeatherForecast weatherForecast = new WeatherForecast();
			Calendar helpCal = Calendar.getInstance();
			helpCal.add(Calendar.DAY_OF_MONTH, i);

			weatherForecast.setCity(city);
			weatherForecast.setDate(helpCal.getTime());

			weatherForecastList.add(weatherForecast);
		}

		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		for (WeatherInfo wi : weatherInfoList) {
			String wiDate = fmt.format(wi.getDate());

			for (int i = 0; i < weatherForecastList.size(); i++) {
				if (fmt.format(weatherForecastList.get(i).getDate()).equals(
						wiDate)) {
					weatherForecastList.get(i).getWeatherInfo().add(wi);
					break;
				}
			}
		}

		return weatherForecastList;
	}

}
