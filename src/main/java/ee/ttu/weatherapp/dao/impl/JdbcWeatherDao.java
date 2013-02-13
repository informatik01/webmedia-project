package ee.ttu.weatherapp.dao.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ee.ttu.weatherapp.dao.WeatherDao;
import ee.ttu.weatherapp.domain.WeatherForecast;
import ee.ttu.weatherapp.domain.WeatherInfo;

/**
 * 
 * 
 */
public class JdbcWeatherDao extends JdbcDaoSupport implements WeatherDao {

	private static final Logger logger = Logger.getLogger(JdbcWeatherDao.class);
	
	private static final String SELECT_WHERE_DATE_SQL = "SELECT wt.weather_type, wi.min_temp, wi.max_temp, "
			+ "wi.wind, wi.description, wi.date, c.city_name, dp.day_part_name "
			+ "FROM weather_info wi INNER JOIN cities c ON wi.city_id = c.city_id "
			+ "INNER JOIN day_parts dp ON wi.day_part_id = dp.day_part_id "
			+ "INNER JOIN weather_types wt ON wi.weather_type_id = wt.weather_type_id "
			+ "WHERE c.city_name = ? AND wi.date = ? AND dp.day_part_name = ? "
			+ "ORDER BY wi.date, dp.day_part_name";
	
	private static final String SELECT_WHERE_DATE_BETWEEN_SQL = "SELECT wt.weather_type, wi.min_temp, wi.max_temp, "
			+ "wi.wind, wi.description, wi.date, c.city_name, dp.day_part_name "
			+ "FROM weather_info wi INNER JOIN cities c ON wi.city_id = c.city_id "
			+ "INNER JOIN day_parts dp ON wi.day_part_id = dp.day_part_id "
			+ "INNER JOIN weather_types wt ON wi.weather_type_id = wt.weather_type_id "
			+ "WHERE c.city_name = ? AND wi.date BETWEEN ? AND ? "
			+ "ORDER BY wi.date, wi.day_part_id";
	
	@Override
	public WeatherInfo readWeatherInfo(String cityName, Date date, String partOfDay) {
		WeatherInfo weatherInfo = null;
		
		try {
			weatherInfo = getJdbcTemplate().queryForObject(SELECT_WHERE_DATE_SQL, new WeatherInfoRowMapper(), cityName, date, partOfDay);
		} catch (EmptyResultDataAccessException e) {
			if (logger.isEnabledFor(Level.ERROR)) {
				logger.error("*****No result returned by the following query:\n" + SELECT_WHERE_DATE_SQL, e);
			}
		}

		return weatherInfo;
	}

	@Override
	public List<WeatherInfo> readWeatherInfo(String cityName, Date startDate, Date endDate) {
		List<WeatherInfo> weatherInfoList = null;
		
		try {
			weatherInfoList = getJdbcTemplate().query(SELECT_WHERE_DATE_BETWEEN_SQL, new WeatherInfoRowMapper(), cityName, startDate, endDate);
		} catch (EmptyResultDataAccessException e) {
			if (logger.isEnabledFor(Level.ERROR)) {
				logger.error("*****No result returned by the following query:\n" + SELECT_WHERE_DATE_BETWEEN_SQL, e);
			}
		}

		return weatherInfoList;
	}


	@Override
	public void insertWeatherInfo(WeatherForecast weatherForecast) {
		
	}

	@Override
	public void updateWeatherInfo(WeatherForecast weatherForecast, int weatherId) {
		
	}

	@Override
	public void deleteWeatherInfo(int weatherId) {
		
	}

}
