package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Helpers {
	
	public static final String NOPERMIT="not permit";
	public static final String NONEDATA="none data";
	
	/**
	 * @return 获取格式为yyyy-MM-dd kk:mm:ss的当前时间
	 */
	public static String nowTime()
	{
		Date d=new Date();//获取时间
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//转换格式
		String endTime=sdf.format(d);
		return endTime;
	}
	
	/**
	 * @param time	要加的时间的字符串，格式"yyyy-MM-dd kk:mm:ss"
	 * @param month	要加的月数
	 * @return		加过的时间的字符串，格式"yyyy-MM-dd kk:mm:ss"
	 * @throws ParseException
	 */
	public static String addTimeMonth(String time,int month)
	{
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//转换格式
		    Date mydate= sdf.parse(time);
		    Calendar cal=Calendar.getInstance();
		    cal.setTime(mydate);
		    cal.add(Calendar.MONTH, month);
		    return sdf.format(cal.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * @param time	要加的时间的字符串，格式"yyyy-MM-dd kk:mm:ss"
	 * @param day	要加的天
	 * @return		加过的时间的字符串，格式"yyyy-MM-dd kk:mm:ss"
	 * @throws ParseException
	 */
	public static String addTimeDay(String time,int day)
	{
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//转换格式
		    Date mydate= sdf.parse(time);
		    Calendar cal=Calendar.getInstance();
		    cal.setTime(mydate);
		    cal.add(Calendar.DAY_OF_MONTH, day);
		    return sdf.format(cal.getTime());
		} catch (Exception e) {
			return null;
		}
	}
	
	
	/**
	 * @param one	时间1
	 * @param two	时间2
	 * @return		-1 one<two;0 one=two;1 one>two;
	 */
	public static int compareDate(String one,String two)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//转换格式
	    try {
	    	
			Date oneD= sdf.parse(one);
			Date twoD= sdf.parse(two);
			return oneD.compareTo(twoD);
			
		} catch (ParseException e) {
			return 1;
		}
	    
		
	}
	
	/**
	 * @param str	淘宝返回的字符串
	 * @return		自己定义的json格式
	 */
	public static String strCut(String str)
	{
		if(str!=null)
		{
			if(str.indexOf("[")==-1||str.lastIndexOf("]")==-1)
			{
				//未授权，则不会有total_results字符串
				if(str.indexOf("total_results")==-1)
				{
					return NOPERMIT;
				}
				else 
				{
					return NONEDATA;
				}
			}
			return str.substring(str.indexOf("["), str.lastIndexOf("]")+1);
		}
		return null;
	}
	
}
