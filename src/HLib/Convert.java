package HLib;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Convert 
{

	private static DateFormat _dateFormatter = null;

	
	public static Date ToDate(String valueStr) throws ParseException
	{
		if (_dateFormatter == null)
		{
			_dateFormatter = SimpleDateFormat.getDateTimeInstance();
			_dateFormatter.setTimeZone(TimeZone.getTimeZone("UTC"));
		}
		
		return _dateFormatter.parse(valueStr);
	}


	public static long ToLong(String valueStr) 
	{	
		return Integer.parseInt(valueStr);
	}


	public static int ToInt(String valueStr) 
	{
		return Integer.parseInt(valueStr);
	}
}
