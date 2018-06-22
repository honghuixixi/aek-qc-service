package com.aek.ebey.qc.web.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 *
 */
public class DateUtils {

	/**
	 * 得到某月的第一天
	 */
	public static String getStartDate(String year, String month) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, Integer.parseInt(year));

		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);

		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DATE));

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 得到某月的下一个月的第一天
	 */
	public static String getEndDate(String year, String month) {
		Calendar cal = Calendar.getInstance();

		cal.set(Calendar.YEAR, Integer.parseInt(year));

		cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		int value = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, value);
		cal.add(Calendar.DAY_OF_MONTH, 1);

		return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
	}

	/**
	 * 得到某月的天数
	 */
	public static Integer getDays(String year, String month) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(year + "-" + month);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

	}
	

}
