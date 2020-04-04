package com.cocoivan.base.util;

import com.alibaba.fastjson.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;

/**
 * 时间工具类
 * 
 * @author Lee
 * 
 *         2012-4-24 下午1:47:41
 */
public class DateUtils {

	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String MONTH_FORMAT = "yyyy-MM";
	//	public static final long MONTH_LONG = 2651224907L;

	public static String getDate(int paramInt) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(new Date());
		localCalendar.add(5, paramInt);
		String str = dateToString(localCalendar.getTime(), "yyyy-MM-dd");
		return str;
	}

	public static String getDate(int paramInt, String paramString) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(new Date());
		localCalendar.add(5, paramInt);
		String str = dateToString(localCalendar.getTime(), paramString);
		return str;
	}

	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String dateToStringDay(Date date) {
		return dateToString(date, "yyyy-MM-dd");
	}

	public static String dateToStringCalendarDay(Date date) {
		return dateToString(date, "MM月dd日");
	}

	public static String dateToString(Date date, String format) {
		if ((date == null) || (format == null))
			return null;
		SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(format);
		return localSimpleDateFormat.format(date);
	}

	public static Date stringToDate(String dateStr) {
		return stringToDate(dateStr, "yyyy-MM-dd");
	}

	public static Date stringToDate(String dateStr, String format) {
		Date localDate = null;
		if ((dateStr != null) && (format != null))
			try {
				SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat(format);
				localDate = localSimpleDateFormat.parse(dateStr);
			}
			catch (ParseException localParseException) {
				localDate = null;
			}
		return localDate;
	}

	public static String getNowYear() {
		Calendar localCalendar = Calendar.getInstance();
		int i = localCalendar.get(1);
		return String.valueOf(i);
	}

	public static String getNowMonth() {
		Calendar localCalendar = Calendar.getInstance();
		int i = localCalendar.get(2) + 1;
		if (i < 10)
			return "0" + i;
		return String.valueOf(i);
	}

	public static String getNowDay() {
		return dateToString(new Date(), "dd");
	}

	public static String getYestday() {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.add(5, -1);
		return dateToString(localCalendar.getTime(), "dd");
	}

	public static Date getDateFront(int paramInt) {
		return new Date(Calendar.getInstance().getTimeInMillis() - 2651224907L * paramInt);
	}

	public static String getNowHour() {
		return dateToString(new Date(), "HH");
	}

	public static String getNowMinute() {
		return dateToString(new Date(), "mm");
	}

	public static Date setDate(String paramString, int paramInt) {
		if (paramString != null) {
			Date localDate = stringToDate(paramString, "yyyy-MM-dd");
			return setDate(localDate, paramInt);
		}
		return null;
	}

	public static Date setDate(Date paramDate, int paramInt) {
		Date localDate = null;
		Calendar localCalendar = null;
		if (paramDate != null) {
			localCalendar = Calendar.getInstance();
			localCalendar.setTime(paramDate);
			localCalendar.add(5, paramInt);
			localDate = localCalendar.getTime();
		}
		return localDate;
	}

	public static int getDayBetween(Date date1, Date date2) {
		Calendar localCalendar1 = Calendar.getInstance();
		Calendar localCalendar2 = Calendar.getInstance();
		if (date1.before(date2)) {
			localCalendar1.setTime(date1);
			localCalendar2.setTime(date2);
		}
		else {
			localCalendar1.setTime(date2);
			localCalendar2.setTime(date1);
		}
		int i = 0;
		int j = localCalendar1.get(6);
		int k = localCalendar2.get(6);
		int l = localCalendar1.get(1);
		int i1 = localCalendar2.get(1);
		localCalendar1.clear();
		localCalendar1.set(l, 0, 1);
		while (l != i1) {
			localCalendar1.set(l++, 11, 31);
			i += localCalendar1.get(6);
		}
		return i + k - j;
	}

	public static Date addDay(Date date, int offset) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(date);
		localCalendar.add(Calendar.DAY_OF_MONTH, offset);
		return localCalendar.getTime();
	}

	public static Date addMinute(Date date, int offset) {
		long newTime = date.getTime() + 1000 * 60 * offset;
		date.setTime(newTime);
		return date;
	}

	public static Date getDateStart(Date date) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(date);
		localCalendar.set(Calendar.HOUR_OF_DAY, 0);
		localCalendar.set(Calendar.MINUTE, 0);
		localCalendar.set(Calendar.SECOND, 0);
		localCalendar.set(Calendar.MILLISECOND, 0);
		return localCalendar.getTime();
	}

	public static Date getDateEnd(Date date) {
		Calendar localCalendar = Calendar.getInstance();
		localCalendar.setTime(date);
		localCalendar.set(Calendar.HOUR_OF_DAY, 23);
		localCalendar.set(Calendar.MINUTE, 59);
		localCalendar.set(Calendar.SECOND, 59);
		localCalendar.set(Calendar.MILLISECOND, 0);
		return localCalendar.getTime();
	}

	public static Date getTodayStart() {
		return getDateStart(new Date());
	}

	public static Date getTodayEnd() {
		return getDateEnd(new Date());
	}

	public static String MilliSecondToString(int ms) {
		ms /= 1000;

		int minute = ms / 60;
		int second = ms % 60;

		String strMinute = "";
		String strSecond = "";

		if (minute < 10) {
			strMinute = "0";
		}

		if (second < 10) {
			strSecond = "0";
		}

		strMinute += minute;
		strSecond += second;

		return strMinute + ":" + strSecond;
	}

	public static String getBeforeTimeStr(Date date) {
		Date now = new Date();
		long detSec = (now.getTime() - date.getTime()) / 1000;
		int month = 2592000;
		int day = 86400;
		int hour = 3600;
		int min = 60;

		String result;

		if (detSec > month) {
			//大于一个月，直接显示时间
			Calendar calendar = Calendar.getInstance();
			DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);
			result = dateFormat.format(calendar.getTime());
		}
		else if (detSec > day) {
			//大于一天，显示几天前
			result = (int) (detSec / day) + "天前";
		}
		else if (detSec > hour) {
			//大于一小时，显示几小时前
			result = (int) (detSec / hour) + "小时前";
		}
		else if (detSec > min) {
			//大于一分钟，显示几分钟前
			result = (int) (detSec / min) + "分钟前";
		}
		else {
			//显示一分钟前
			result = "1分钟前";
		}

		return result;
	}

	public static String getAfterTimeStr(Date date) {
		Date now = new Date();
		long detSec = (date.getTime() - now.getTime()) / 1000;
		int month = 2592000;
		int day = 86400;
		int hour = 3600;
		int min = 60;

		String result;

		if (detSec > month) {
			Calendar calendar = Calendar.getInstance();
			result = dateToString(calendar.getTime(), DATE_FORMAT);
		}
		else if (detSec > day) {
			result = (int) (detSec / day) + "天后";
		}
		else if (detSec > hour) {
			result = (int) (detSec / hour) + "小时后";
		}
		else if (detSec > min) {
			result = (int) (detSec / min) + "分钟后";
		}
		else {
			result = "1分钟后";
		}

		return result;
	}

	/**
	 * 获取当前时间，String 格式
	 * @return
	 */
	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String today = f.format(date);
		return today;
	}

	/**
	 * @param birthDay
	 * @return calculate the age from birthDay
	 * @throws Exception
	 */
	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
		}

		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				//monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
				else {
					//do nothing
				}
			}
			else {
				//monthNow>monthBirth
				age--;
			}
		}
		else {
			//monthNow<monthBirth
			//donothing
		}

		return age;
	}

	public static String timeLeftStr(Date toDate) {

		long time = toDate.getTime();
		long duration = time - System.currentTimeMillis();

		// 秒
		long sec = (duration / 1000) % 60;
		// 分钟
		long min = (duration / 1000 / 60) % 60;
		// 小时
		long hour = (duration / 1000 / 60 / 60) % 24;
		// 天
		long day = duration / 1000 / 60 / 60 / 24;

		if (day > 0) {
			return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
		}

		if (hour > 0) {
			return hour + "小时" + min + "分钟" + sec + "秒";
		}

		if (min > 0) {
			return min + "分钟" + sec + "秒";
		}
		return sec + "秒";

	}

	/**
	 * 大于天，展示xx天 小于天，展示xx小时xx分 小于时，还剩xx分 小于分，小于一分钟
	 * @param endTime
	 * @return
	 */
	public static String timeLeftStrShort(Date endTime) {

		long time = endTime.getTime();
		long duration = time - System.currentTimeMillis();

		// 秒
		long sec = (duration / 1000) % 60;
		// 分钟
		long min = (duration / 1000 / 60) % 60;
		// 小时
		long hour = (duration / 1000 / 60 / 60) % 24;
		// 天
		long day = duration / 1000 / 60 / 60 / 24;

		if (day > 0) {
			return day + "天";
		}

		if (hour > 0) {
			return hour + "小时";
		}

		if (min > 0) {
			return min + "分钟";
		}

		if (day == 0 && hour == 0 && min == 0 && sec == 0) {
			return "结束";
		}
		return "小于一分钟";
	}

	public static Date getYesterdayDateTime() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
		return calendar.getTime();
	}

	/**
	 * 获取昨天的日期
	 * @return
	 */
	public static String getYesterdayDate() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_MONTH, -1); //设置为前一天
		String str = dateToString(calendar.getTime(), "yyyy-MM-dd");
		return str;
	}

	public static Date dayStart(Date time) {

		Calendar now = Calendar.getInstance();
		now.setTime(time);
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);

		return now.getTime();
	}

	public static Date dayEnd(Date time) {

		Calendar now = Calendar.getInstance();
		now.setTime(time);

		//如果是一天的结束则不加一天
		if (now.get(Calendar.HOUR_OF_DAY) == 0 && now.get(Calendar.MINUTE) == 0
				&& now.get(Calendar.SECOND) == 0) {
			return now.getTime();
		}

		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);

		now.add(Calendar.DAY_OF_YEAR, 1);

		return now.getTime();
	}
	
	public static LinkedList<JSONObject> timeList = new LinkedList<JSONObject>();
	
	/**
	 * 计时的方法
	 * @param desc 这次计时过程的描述.
	 */
	public static void startTiming(String desc) {
		JSONObject jsonObj = new JSONObject();
		long start = System.currentTimeMillis();
		jsonObj.put("startTime", start);
		jsonObj.put("desc", desc);
		timeList.addLast(jsonObj);
	}
	/**
	 * 停止计时,之后可以选择再次开始计时或者打印出计时的信息
	 * @see
	 */
	public static void endTiming() {
		JSONObject jsonObj = timeList.getLast();
		long end = System.currentTimeMillis();
		jsonObj.put("endTime", end);
	}
	/**
	 * 展示计时信息,展示后,容器内保存的计时信息会被清空. 
	 */
	public static void showTiming() {
		System.out.println("==============打印计时的消息开始===================");
		for(JSONObject jsonObj : timeList) {
			Object start = jsonObj.get("startTime");
			Object end = jsonObj.get("endTime");
			long startNum = Long.parseLong(String.valueOf(start));
			long endNum = Long.parseLong(String.valueOf(end));
			System.out.println("描述:" + jsonObj.getString("desc") + ",耗时:" + (endNum - startNum));
		}
		System.out.println("==============打印计时的消息结束===================");
		timeList.clear();
	}

	public static Integer ymd() {
        String ymd = DateUtils.dateToString(new Date(), "yyyyMMdd");
        return Integer.valueOf(ymd);
    }
}
