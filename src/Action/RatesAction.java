package Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import Entity.SolRates;
import Entity.SolShop;
import Entity.SolTrades;
import Entity.SolUsers;


public class RatesAction extends BaseAction {
	

	public String rateList()
	{
		try 
		{
			List<SolRates> rateList=rateservice.rateList(shopid);
			responseMsg=listToJson(rateList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String allRateList()
	{
		try 
		{
			List<SolRates> rateList=rateservice.rateList();
			responseMsg=listToJson(rateList);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	//将list转化为json格式
	public String listToJson(List<SolRates> rateList)
	{
		try 
		{
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<rateList.size();i++)
			{
				SolRates rate=rateList.get(i);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("tid", rate.getSolTrades().getTradeId());
				jsonObject.put("nick", rate.getSolTrades().getTradeBuyernick());
				jsonObject.put("result", rate.getRateResult());
				jsonObject.put("created", rate.getRateCreate());
				jsonObject.put("item_title", rate.getRateItemtitle());
				jsonObject.put("item_price", rate.getRateItemprice());
				jsonObject.put("content", rate.getRateContent());
				jsonObject.put("reply", rate.getRateReply());
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
