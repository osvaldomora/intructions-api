package mx.santander.fiduciarioplus.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class DateUtil {

	private static final int LAST_HOUR = 23;
	private static final int LAST_MINUTE = 59;
	private static final int LAST_SECOND = 59;

	public static Date getFinalDate() {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date());
		//calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR);
		//calendar.set(Calendar.MINUTE, LAST_MINUTE);
		//calendar.set(Calendar.SECOND, LAST_SECOND);
		return calendar.getTime();
	}

	public static Date getFinalDate(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		//calendar.set(Calendar.HOUR_OF_DAY, LAST_HOUR);
		//calendar.set(Calendar.MINUTE, LAST_MINUTE);
		//calendar.set(Calendar.SECOND, LAST_SECOND);
		return calendar.getTime();
	}

	public static Date getDateMinusOrSumDay(Date date,int minusDay) {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, minusDay);
		return calendar.getTime();
	}

	public static String getDayOfWeek(Date date) {
		DateFormat format2 = new SimpleDateFormat("EEEE");
		return format2.format(date);
	}
}
