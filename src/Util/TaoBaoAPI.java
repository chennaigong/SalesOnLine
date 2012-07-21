package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.LogisticsCompaniesGetRequest;
import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.request.TraderatesGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.response.LogisticsCompaniesGetResponse;
import com.taobao.api.response.LogisticsOfflineSendResponse;
import com.taobao.api.response.TraderatesGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;

public class TaoBaoAPI {
	
	/**
	 * 交易taobao.trades.sold.get
	 * @param sessionKey
	 * @return	交易列表
	 */
	public static String tradeString(String sessionKey)
	{
		try {
			
			TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
			TradesSoldGetRequest req=new TradesSoldGetRequest();
			req.setFields("tid,status,total_fee,created,pay_time,buyer_nick,buyer_area,payment");
			Date dateTime = SimpleDateFormat.getDateTimeInstance().parse("2012-1-01 00:00:00");
			req.setStartCreated(dateTime);
			Date dateTime1 = SimpleDateFormat.getDateTimeInstance().parse("2013-7-20 17:46:00");
			req.setEndCreated(dateTime1);
			TradesSoldGetResponse response = client.execute(req , sessionKey);
			return response.getBody();
			
		} catch (Exception e) {
			System.out.println("API执行tradeString出错");
		}
		return null;
	}
	
	/**
	 * 评价taobao.traderates.get
	 * @param sessionKey
	 * @return	评价列表
	 */
	public static String rateString(String sessionKey)
	{
		try {
			
			 TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
	    	 TraderatesGetRequest req=new TraderatesGetRequest();
	    	 req.setFields("tid,nick,result,created,item_title,item_price,content,reply");
	    	 req.setRateType("get");
	    	 req.setRole("buyer");
	    	 Date dateTime = SimpleDateFormat.getDateTimeInstance().parse("2012-01-01 00:00:00");
	    	 req.setStartDate(dateTime);
	    	 Date dateTime1 = SimpleDateFormat.getDateTimeInstance().parse("2012-08-01 00:00:00");
	    	 req.setEndDate(dateTime1);
	    	 TraderatesGetResponse response = client.execute(req , sessionKey);
	    	 return response.getBody();
			
		} catch (Exception e) {
			System.out.print("API执行rateString出错");
		}
		return null;
	}
	/**
	 * 物流taobao.logistics.companies.get
	 * @param sessionKey
	 * @return	物流公司列表
	 */
	public static String logisticString(String sessionKey)
	{
		try {
			
			 TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
	    	 LogisticsCompaniesGetRequest req=new LogisticsCompaniesGetRequest();
	    	 req.setFields("code,name,reg_mail_no");
	    	 req.setIsRecommended(true);
	    	 req.setOrderMode("offline");
	    	 LogisticsCompaniesGetResponse response = client.execute(req);
	    	 return response.getBody();
			
		} catch (Exception e) {
			System.out.print("API执行logisticString出错");
		}
		return null;
	} 
	
	/**
	 * 物流taobao.logistics.offline.send
	 * @param sessionKey
	 * @return	是否发货成功
	 */
	public static String logisticSend(String sessionKey,long tid,String out_id,String companyCode)
	{
		try {
			
			TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
			LogisticsOfflineSendRequest req=new LogisticsOfflineSendRequest();
			req.setTid(tid);
			req.setOutSid(out_id);
			req.setCompanyCode(companyCode);
			LogisticsOfflineSendResponse response = client.execute(req , sessionKey);
			return response.getBody();
			
		} catch (Exception e) {
			System.out.print("API执行logisticSend出错");
		}
		return null;
	} 
	
	/**
	 * 交易taobao.trades.sold.get
	 * @param sessionKey
	 * @return	买家列表（其实是交易的列表里只查询买家昵称）
	 */
	public static String tradeBuyerString(String sessionKey)
	{
		try {
			
			TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
			TradesSoldGetRequest req=new TradesSoldGetRequest();
			req.setFields("buyer_nick");
			Date dateTime = SimpleDateFormat.getDateTimeInstance().parse("2012-1-01 00:00:00");
			req.setStartCreated(dateTime);
			Date dateTime1 = SimpleDateFormat.getDateTimeInstance().parse("2012-7-20 17:46:00");
			req.setEndCreated(dateTime1);
			TradesSoldGetResponse response = client.execute(req , sessionKey);
			return response.getBody();
			
		} catch (Exception e) {
			System.out.println("API执行tradeString出错");
		}
		return null;
	}
}
