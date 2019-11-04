package com.cost168.costaudit.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * ClassName: OrderUtil 
 * @Description: TODO
 * @author lixiang
 * @date 2019-5-30下午3:53:33
 * @Company  广东华联软件科技有限公司
 */
public class OrderUtil {
	public static AtomicInteger race = new AtomicInteger(0);
	private static Date date = new Date();
	private static final int THREADS_COUNT = 99;

	private static int increase() {
		if(race.get() >= THREADS_COUNT) {
			race = new AtomicInteger(0);
		} else {
			return race.incrementAndGet();
		}
		return race.get();
	}
	
	public static synchronized String buildOrderId(String prefix) {
		date.setTime(System.currentTimeMillis());
		return prefix + String.format("%1$tY%1$tm%1$td%1$tH%1$tM%1$tS%2$02d", date, increase());
	}
	
	public static long getDaySub(String beginDateStr, String endDateStr) {
		Date beginDate;
		Date endDate;
		long day = 0;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			beginDate = format.parse(beginDateStr);
			endDate = format.parse(endDateStr);
			double dday = (double)(endDate.getTime() - beginDate.getTime()) / (24 * 60 * 60 * 1000);
			if(dday>0 && dday<1){
				day = 1;
			}else{
				day = Math.round(dday);
			}
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		return day+1;
	}
	
	/**
	 * 
	 * @Description: 某月周末是哪些天
	 * @param @param year
	 * @param @param month
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author lixiang
	 * @date 2019-6-11下午4:45:03
	 * @Company  广东华联软件科技有限公司
	 */
	public static List<String> getYearMonthWeekDays(int year, int month) {
		List<String> list = new ArrayList<String>();
	    Calendar calendar = Calendar.getInstance();
	    calendar.set(Calendar.YEAR, year);// 不设置的话默认为当年
	    calendar.set(Calendar.MONTH, month - 1);// 设置月份
	    calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为当月第一天
	    int daySize = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);// 当月最大天数
	    for (int i = 0; i < daySize-1; i++) {
	        calendar.add(Calendar.DATE, 1);//在第一天的基础上加1
	        int week = calendar.get(Calendar.DAY_OF_WEEK);
	        if (week == Calendar.SATURDAY || week == Calendar.SUNDAY) {// 1代表周日，7代表周六 判断这是一个星期的第几天从而判断是否是周末
	            //list.add(year+"-"+month+"-"+calendar.get(Calendar.DAY_OF_MONTH));// 得到当天是一个月的第几天
	        	list.add(calendar.get(Calendar.DAY_OF_MONTH)+"");
	        }
	    }
		return list;
	}
	/**
	 * 
	 * @Description: 某年某月有多少天
	 * @param @param year
	 * @param @param month
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author lixiang
	 * @date 2019-6-11下午4:44:28
	 * @Company  广东华联软件科技有限公司
	 */
	public static int getYearMonthDays(int year,int month){
		int a=0;
		switch(month){
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
			a=31;
			break;
			case 4:
			case 6:
			case 9:
			case 11:
			a=30;
			break;
			case 2:
			if((year%4==0&&year%100!=0)||year%400==0){
				a=29;
			}else{
				a=28;
			}
		}
		return a;
	}
}
