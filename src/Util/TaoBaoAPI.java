package Util;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.LogisticsCompaniesGetRequest;
import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.request.TraderatesGetRequest;
import com.taobao.api.request.TradesSoldGetRequest;
import com.taobao.api.request.TradesSoldIncrementGetRequest;
import com.taobao.api.response.LogisticsCompaniesGetResponse;
import com.taobao.api.response.LogisticsOfflineSendResponse;
import com.taobao.api.response.TraderatesGetResponse;
import com.taobao.api.response.TradesSoldGetResponse;
import com.taobao.api.response.TradesSoldIncrementGetResponse;

public class TaoBaoAPI {
	
	/**
	 * 交易taobao.trades.sold.get
	 * @param sessionKey
	 * @param startTime  格式："2013-7-20 17:46:00"
	 * @param endTime  格式："2013-7-20 17:46:00"
	 * @return	交易列表
	 */
	public static String tradeString(String sessionKey,String startTime,String endTime)
	{
		try {
			
			TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
			TradesSoldGetRequest req=new TradesSoldGetRequest();
			req.setFields("tid,status,total_fee,created,pay_time,buyer_nick,payment");
			Date dateTime = SimpleDateFormat.getDateTimeInstance().parse(startTime);
			req.setStartCreated(dateTime);
			Date dateTime1 = SimpleDateFormat.getDateTimeInstance().parse(endTime);
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
	public static String rateString(String sessionKey,String startTime,String endTime)
	{
		try {
			
			 TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
	    	 TraderatesGetRequest req=new TraderatesGetRequest();
	    	 req.setFields("tid,nick,result,created,item_title,item_price,content,reply,num_iid");
	    	 req.setRateType("get");
	    	 req.setRole("buyer");
	    	 Date dateTime = SimpleDateFormat.getDateTimeInstance().parse(startTime);
	    	 req.setStartDate(dateTime);
	    	 Date dateTime1 = SimpleDateFormat.getDateTimeInstance().parse(endTime);
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
	 * @return	物流公司列表
	 */
	public static String logisticString()
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
	
	public static String modifyTradeString(String sessionKey,String startTime,String endTime)
	{
		try {
			
			TaobaoClient client=new DefaultTaobaoClient(WebConfig.APIURL, WebConfig.APPKEY, WebConfig.APPSECRET);
			TradesSoldIncrementGetRequest req=new TradesSoldIncrementGetRequest();
			req.setFields("tid,status,total_fee,created,pay_time,buyer_nick,payment,modified");
			Date dateTime = SimpleDateFormat.getDateTimeInstance().parse(startTime);
			req.setStartModified(dateTime);
			Date dateTime1 = SimpleDateFormat.getDateTimeInstance().parse(endTime);
			req.setEndModified(dateTime1);
			TradesSoldIncrementGetResponse response = client.execute(req , sessionKey);
			return response.getBody();
			
		} catch (Exception e) {
			System.out.println("API执行modifyTradeString出错");
		}
		return null;
	}
}

