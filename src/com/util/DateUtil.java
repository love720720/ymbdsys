package com.util;

import java.util.Calendar;

import com.constant.Constant;

/**
 * 日期工具类
 *
 */
public class DateUtil {

	//得到今天开始时间毫秒数
	public static long getTodayLong(){
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}
	
	//得到明天开始时间毫秒数
	public static long getTomorrowLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime().getTime();
	}
	
	//得到昨天开始时间毫秒数
	public static long getYesterdayLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime().getTime();
	}
	
	//得到当前周的第一天毫秒数
	public static long getWeekdayFirstLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}
	
	//得到当前月的第一天毫秒数
	public static long getMonthFirstLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime().getTime();
	}
	
	//得到当前小时
	public static int getHour(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	//得到今日日期 格式yyyy-MM-dd
	public static String getToday(){
		Calendar calendar = Calendar.getInstance();
		String todayDate = Constant.YYYYMMDD_FOMAT.format(calendar.getTime());
		return todayDate;
	}
}
