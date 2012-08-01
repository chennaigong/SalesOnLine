package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



public class Helpers {
	
	public static final String NOPERMIT="not permit";
	public static final String NONEDATA="none data";
	
	/**
	 * @return ��ȡ��ʽΪyyyy-MM-dd kk:mm:ss�ĵ�ǰʱ��
	 */
	public static String nowTime()
	{
		Date d=new Date();//��ȡʱ��
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//ת����ʽ
		String endTime=sdf.format(d);
		return endTime;
	}
	
	/**
	 * @param time	Ҫ�ӵ�ʱ����ַ�������ʽ"yyyy-MM-dd kk:mm:ss"
	 * @param month	Ҫ�ӵ�����
	 * @return		�ӹ���ʱ����ַ�������ʽ"yyyy-MM-dd kk:mm:ss"
	 * @throws ParseException
	 */
	public static String addTimeMonth(String time,int month)
	{
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//ת����ʽ
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
	 * @param time	Ҫ�ӵ�ʱ����ַ�������ʽ"yyyy-MM-dd kk:mm:ss"
	 * @param day	Ҫ�ӵ���
	 * @return		�ӹ���ʱ����ַ�������ʽ"yyyy-MM-dd kk:mm:ss"
	 * @throws ParseException
	 */
	public static String addTimeDay(String time,int day)
	{
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//ת����ʽ
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
	 * @param one	ʱ��1
	 * @param two	ʱ��2
	 * @return		-1 one<two;0 one=two;1 one>two;
	 */
	public static int compareDate(String one,String two)
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");//ת����ʽ
	    try {
	    	
			Date oneD= sdf.parse(one);
			Date twoD= sdf.parse(two);
			return oneD.compareTo(twoD);
			
		} catch (ParseException e) {
			return 1;
		}
	    
		
	}
	
	/**
	 * @param str	�Ա����ص��ַ���
	 * @return		�Լ������json��ʽ
	 */
	public static String strCut(String str)
	{
		if(str!=null)
		{
			if(str.indexOf("[")==-1||str.lastIndexOf("]")==-1)
			{
				//δ��Ȩ���򲻻���total_results�ַ���
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
