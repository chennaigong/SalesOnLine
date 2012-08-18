package Action;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import Entity.SolShop;
import Entity.SolTrades;
import Entity.SolUsers;
import Util.TaoBaoAPI;
import Util.WebConfig;


public class TradesAction extends BaseAction{
	
	private String status;
	private String tid;
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String tradeIndex(){
		return SUCCESS;
	}
	
	
	public String allTradeList()
	{
		try {
			
			List<SolTrades> tradeList=tradeservice.tradeList();
			
			responseMsg=listToJson(tradeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String allTradeListStatus()
	{
		try {
			
			List<SolTrades> tradeList=tradeservice.tradeList(status);
			
			responseMsg=listToJson(tradeList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String modifyTradeRead()
	{
		tradeservice.updateTrade(tid, "是");
		responseMsg="1";
		return SUCCESS;
	}
	
	//将list转化为json格式
	public String listToJson(List<SolTrades> tradeList)
	{
		try {
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<tradeList.size();i++)
			{
				SolTrades trade=tradeList.get(i);
				
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("tid", trade.getTradeId());
				jsonObject.put("status", trade.getTradeStatus());
				jsonObject.put("total_fee", trade.getTradeTotalfee());
				jsonObject.put("created", trade.getTradeCreate());
				jsonObject.put("pay_time", trade.getTradePaytime());
				jsonObject.put("buyer_nick", trade.getTradeBuyernick());
				jsonObject.put("payment", trade.getTradePayment());
				jsonObject.put("modified", trade.getTradeModified());
				jsonObject.put("isread", trade.getTradeIsread());
				jsonArray.put(jsonObject);
			}
			return jsonArray.toString();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return "0";
	}
}
