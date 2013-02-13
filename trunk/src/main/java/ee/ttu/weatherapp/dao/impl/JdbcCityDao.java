package ee.ttu.weatherapp.dao.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import ee.ttu.weatherapp.dao.CityDao;
import ee.ttu.weatherapp.mvc.model.City;

public class JdbcCityDao extends JdbcDaoSupport implements CityDao {

	private static final Logger logger = Logger.getLogger(JdbcCityDao.class);
	
	private static final String SELECT_SQL = "SELECT city_id AS id, city_name AS name FROM cities";
	private static final String INSERT_SQL = "INSERT INTO cities VALUES ";
	private static final String UPDATE_SQL = "UPDATE cities SET city_name = CASE city_id ";
	private static final String DELETE_SQL = "DELETE FROM cities WHERE city_id = ?";
	
	@Override
	public List<City> read() {
		List<City> cities = null;
		
		try {
			cities = getJdbcTemplate().query(SELECT_SQL, BeanPropertyRowMapper.newInstance(City.class));
			
			if (cities == null) {
				System.out.println("*****No data fetched from table \"cities\"!");
			}
		} catch (EmptyResultDataAccessException e) {
			if (logger.isEnabledFor(Level.ERROR)) {
				logger.error("*****No result returned by the following query:\n" + SELECT_SQL, e);
			}
		}

		return cities;
	}
	
	@Override
	public void insert(String[] cities) {
		if (cities == null || cities.length == 0) {
			return;
		}
		
		StringBuilder sql = new StringBuilder(INSERT_SQL);
		for (int i = 0; i < cities.length; i++) {
			sql.append("(DEFAULT, ?)");
			if (i + 1 < cities.length) {
				sql.append(", ");
			}
		}
		
		int rowsAffected = getJdbcTemplate().update(sql.toString(), (Object[])cities);
		if (rowsAffected == 0) {
			System.out.println("No row was inserted into table \"cities\".");
		}
	}
	
	@Override
	public void update(List<City> cityList) {
		if (cityList == null) {
			return;
		}
		
		StringBuilder sql = new StringBuilder(UPDATE_SQL);
		for(City city: cityList) {
			sql.append(String.format("WHEN %d THEN '%s' ", city.getId(), city.getName()));
		}
		sql.append("ELSE city_name END");
		
		try {
			int rowsAffected = getJdbcTemplate().update(sql.toString());
			if (rowsAffected == 0) {
				System.out.println("No row was updated in table \"cities\".");
			}
		} catch (Exception e) {
			logger.error("Error updating database \"cities\"", e);
		}
	}
	
	@Override
	public void delete(int cityId) {
		int rowsAffected = getJdbcTemplate().update(DELETE_SQL, cityId);
		
		if (rowsAffected == 0) {
			System.out.println("No row was deleted in table \"cities\".");
		}
	}

	@Override
	public void delete(final String[] cityIds) {
		int[] rowsAffected = getJdbcTemplate().batchUpdate(DELETE_SQL, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i)
					throws SQLException {

				ps.setInt(1, Integer.parseInt(cityIds[i]));
			}

			@Override
			public int getBatchSize() {

				return cityIds.length;
			}
		});
		
		if (rowsAffected.length == 0) {
			System.out.println("No rows was deleted in table \"cities\".");
		}
	}
}
