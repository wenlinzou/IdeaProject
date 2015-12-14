package com.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
	public static String getCurrentDetailTime() {
		Calendar c = Calendar.getInstance();// 可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		return year + "-" + month + "-" + date + " " + hour + ":"+ (minute<10?"0"+minute:minute) + ":" + (second<10?"0"+second:second);
	}
	
	public static String getCurrentTimeAllNo(){
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");// 可以方便地修改日期格式

		String currentNow = dateFormat.format(now);
		return currentNow;
	}
	
	

}
