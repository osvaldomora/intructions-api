package mx.santander.fiduciarioplus.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import mx.santander.fiduciarioplus.dto.enums.Days;

/**
 * Esta clase permite realizar diferentes de operaciones con las fechas.
 * @author iacruz
 *
 */
public class DateUtil {

	/**Constructor que evita que se pueda instancia como un Objeto*/
	private DateUtil() {}
	
	/**
	 * Este metodo permite obtener el numero del dia de la semana, de una fecha ingresada.
	 * Los numeros van de 1 (Domingo) hasta el 7 (Sabado)
	 * @param date Fecha de cual se quiere obtener el numero del dia
	 * @return numero del dia de la semana
	 */
	public static int getDay(Date date) {
		int day = 0;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		day = calendar.get(Calendar.DAY_OF_WEEK);
		return day;
	}
	
	/**
	 * Este metodo permite obtener el numero del dia del mes.
	 * @param date Fecha a evaluar
	 * @return el numero del dia del mes.
	 */
	public static int getDayOfMonth(Date date) {
		int day = 0;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * Este metodo permite sumar o restar dias a una fecha, se ingresa un numero positivo para sumar
	 * y uno negativo para restar.
	 * @param date Fecha la cual se quiere modificar
	 * @param days Numero de dias a restar/sumar
	 * @return Objecto Calendar con la fecha actualizada
	 */
	public static Calendar getDateMinusOrSumDay(Date date, int days) {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar;
	}
	
	/**
	 * Este metodo permite sumar o restar meses a una fecha, se ingresa un numero positivo para sumar
	 * y uno negativo para restar.
	 * @param date Fecha la cual se quiere modificar
	 * @param days Numero de meses a restar/sumar
	 * @return Objecto Calendar con la fecha actualizada
	 */
	public static Calendar getDateMinusOrSumMonth(Date date, int month) {

		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, month);
		return calendar;
	}
	
	/**
	 * Este metodo permite modificar el tiempo de una fecha ingresada. 
	 * Consideraciones a tener:
	 * Si se asignan valores positivos o negativos superiores a 23 hrs, estos se sumaran o restaran,
	 * si se asignan valores positivos o negativos superiores a 59 min, estos se sumaran o restaran y cambiara la hora,
	 * si se asignan valores positivos o negativos superiores a 59 seg, estos se sumaran o restaran y cambiara el minuto.
	 * @param date Fecha a actualizar
	 * @param hour Horas que se asigana a la fecha
	 * @param minute Minutos que se asiganaran a la feha
	 * @param second Segundos que se asignaran a la fecga
	 * @return Objecto Calendar con fecha actualizada
	 */
	public static Calendar setTime(Date date, int hour, int minute, int second) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, hour);
		calendar.set(Calendar.MINUTE, minute);
		calendar.set(Calendar.SECOND, second);
		return calendar;
	}

	/**
	 * Este metodo permite obtener el nombre del dia de la semana.
	 * @param date Fecha de la cual se quiere obtener el dia
	 * @return El nombre de dia en español
	 */
	public static String getNameDayOfWeek(Date date) {
		String day = null;
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		switch (calendar.get(Calendar.DAY_OF_WEEK)) {
		case 1:
			day = Days.DOMINGO.getName();
			break;
		case 2:
			day = Days.LUNES.getName();
			break;
		case 3:
			day = Days.MARTES.getName();
			break;
		case 4:
			day = Days.MIERCOLES.getName();
			break;
		case 5:
			day = Days.JUEVES.getName();
			break;
		case 6:
			day = Days.VIERNES.getName();
			break;
		case 7:
			day = Days.SABADO.getName();
			break;
		}
		return day;
	}

}
