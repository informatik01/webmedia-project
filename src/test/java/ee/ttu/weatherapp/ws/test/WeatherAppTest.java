/**
 * 
 */
package ee.ttu.weatherapp.ws.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ee.ttu.weatherapp.dao.impl.JdbcWeatherDao;
import ee.ttu.weatherapp.domain.WeatherForecast;
import ee.ttu.weatherapp.domain.WeatherInfo;

/**
 * @author Levan
 * 
 */
public class WeatherAppTest {

	private static ApplicationContext ctx = null;

	/**
	 * 
	 */
	public WeatherAppTest() {

	}

	public static void main(String[] args) {
		try {
			ctx = new ClassPathXmlApplicationContext(
					"**/META-INF/spring/datasource.xml");
		} catch (BeansException e) {
			System.out.println("*****BeansException!");
			e.printStackTrace();
		}

		JdbcWeatherDao dao = (JdbcWeatherDao) ctx.getBean("jdbcWeatherDao");
		// List<String> cities = dao.readCitiesData();
		// WeatherInfo wi = dao.readCurrentWeatherData("Pärnu", new Date(112,
		// 03, 04), "morning");

		Calendar cal = Calendar.getInstance();
		cal.set(2012, Calendar.APRIL, 1);
		Date startDate = cal.getTime();
		cal.set(2012, Calendar.APRIL, 4);
		Date endDate = cal.getTime();

		List<WeatherInfo> wiList = dao.readWeatherInfo("Tallinn",
				startDate, endDate);

		// PrintWriter out = null;
		// try {
		// out = new PrintWriter("C:\\Users\\Levan\\Desktop\\result.txt",
		// "UTF-8");
		//
		// System.out.println("Showing list of cities:");
		// out.write("The result (üõöä):\n");
		// for (String city : cities) {
		// System.out.println("\t" + city);
		// out.write("\t" + city + "\n");
		// }
		//
		// System.out.println("\nShowing results for \"WeatherInfo\":");
		// System.out.println(wi.getWeatherType());
		// System.out.println(wi.getMinTemp());
		// System.out.println(wi.getMaxTemp());
		// System.out.println(wi.getWind());
		// System.out.println(wi.getPartOfDay());
		// System.out.println(wi.getDescription());

		// System.out.println("*****Result for \"readWeatherInfoData()\":\n");
		// for(WeatherInfo wi: wiList) {
		// System.out.println("Weather: " + wi.getWeatherType());
		// System.out.println("Min temperature: " + wi.getMinTemp());
		// System.out.println("Max temperature: " + wi.getMaxTemp());
		// System.out.println("Wind (m/s): " + wi.getWind());
		// System.out.println("Part of day: " + wi.getPartOfDay());
		// System.out.println("Description: " + wi.getDescription());
		// System.out.println("Date: " + wi.getDate());
		// System.out.println();
		//
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		// Date date1 = fmt.parse("2012-4-2");
		// System.out.println("Date1 = " + date1);
		// if(date1.equals(wi.getDate())) {
		// System.out.println("Dates are equal!");
		// }
		// }
		System.out.println("************************\n");

		int numberOfDays = 4;
		String city = "Tallinn";

		List<WeatherForecast> weatherForecastList = new ArrayList<WeatherForecast>();
		for (int i = 0; i < numberOfDays; i++) {
			WeatherForecast weatherForecast = new WeatherForecast();
			Calendar helpCal = Calendar.getInstance();
			helpCal.set(Calendar.DAY_OF_MONTH, 1);
			helpCal.add(Calendar.DAY_OF_MONTH, i);

			weatherForecast.setCity(city);
			weatherForecast.setDate(helpCal.getTime());

			weatherForecastList.add(weatherForecast);
		}

		for (WeatherInfo wi : wiList) {
			String wiDate = fmt.format(wi.getDate());
			// System.out.println("wiDate = " + wiDate);

			for (int i = 0; i < weatherForecastList.size(); i++) {
				if (fmt.format(weatherForecastList.get(i).getDate()).equals(
						wiDate)) {
					weatherForecastList.get(i).getWeatherInfo().add(wi);
					break;
				}
			}
		}

		System.out.println("Testing List<WeatherForecast>:\n");
		for (WeatherForecast wf : weatherForecastList) {
			System.out.println("WeatherForecast for " + wf.getDate());
			if (!wf.getWeatherInfo().isEmpty()) {
				for (WeatherInfo wi : wf.getWeatherInfo()) {
					System.out.println("\tWeather: " + wi.getWeatherType());
					System.out.println("\tMin temperature: " + wi.getMinTemp());
					System.out.println("\tMax temperature: " + wi.getMaxTemp());
					System.out.println("\tWind (m/s): " + wi.getWind());
					System.out.println("\tPart of day: " + wi.getPartOfDay());
					System.out.println("\tDescription: " + wi.getDescription());
					System.out.println("\tDate: " + wi.getDate());
					System.out.println();
				}
			} else {
				System.out.println("\nNo weather forecast data.");
			}
			System.out.println("***********************************\n");
		}
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (Exception e) {
		// e.printStackTrace();
		// } finally {
		// if (out != null) {
		// try {
		// out.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// }
	}
}
