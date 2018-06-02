package by.babroval.bike.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DateUtils {

	public static final Integer CONVERT_TIME_MINUTE_VALUE = 60000;

	public static final String DATE_TIME_FORMAT = "dd-MM-yyyy_HH-mm-ss";

	public static Timestamp getCurrentDateTime() {

		long currentTime = System.currentTimeMillis();
		Timestamp date = new Timestamp(currentTime);

		return date;
	}

	public static String getCurrentDateTimeAsStr() {

		long currentTime = System.currentTimeMillis();
		Date date = new Date(currentTime);

		DateFormat formatter = new SimpleDateFormat(DATE_TIME_FORMAT);
		String strDate = formatter.format(date);

		return strDate;
	}

}