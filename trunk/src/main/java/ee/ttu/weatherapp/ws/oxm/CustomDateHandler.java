package ee.ttu.weatherapp.ws.oxm;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.exolab.castor.mapping.GeneralizedFieldHandler;

/**
 * @see <a href="http://www.castor.org/xml-fieldhandlers.html">Castor XML FieldHandlers</a>
 * 
 */
public class CustomDateHandler extends GeneralizedFieldHandler {

	private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public Object convertUponGet(Object value) {

		return formatter.format((Date) value);
	}

	@Override
	public Object convertUponSet(Object value) {
		Date date = null;
		try {
			date = formatter.parse((String) value);
		} catch (ParseException e) {
			throw new IllegalArgumentException();
		}

		return date;
	}

	@Override
	public Class<Date> getFieldType() {

		return Date.class;
	}

}
