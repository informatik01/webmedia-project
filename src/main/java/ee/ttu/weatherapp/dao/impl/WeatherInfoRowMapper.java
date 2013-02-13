package ee.ttu.weatherapp.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import ee.ttu.weatherapp.domain.WeatherInfo;

public class WeatherInfoRowMapper implements RowMapper<WeatherInfo> {

	@Override
	public WeatherInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		WeatherInfo weatherInfo = new WeatherInfo();

		weatherInfo.setPartOfDay(rs.getString("day_part_name"));
		weatherInfo.setWeatherType(rs.getString("weather_type"));
		weatherInfo.setMinTemp(rs.getInt("min_temp"));
		weatherInfo.setMaxTemp(rs.getInt("max_temp"));
		weatherInfo.setWind(rs.getInt("wind"));
		weatherInfo.setDescription(rs.getString("description"));
		weatherInfo.setDate(rs.getDate("date"));

		return weatherInfo;
	}
}
