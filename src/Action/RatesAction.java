package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolRates;


public class RatesAction extends BaseAction {
	

	public String rateList()
	{
		try 
		{
			List<SolRates> rateList=rateservice.rateList();
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
			responseMsg=jsonArray.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
}
