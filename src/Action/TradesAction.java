package Action;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Entity.SolTrades;
import Util.TaoBaoAPI;
import Util.WebConfig;


public class TradesAction extends BaseAction{
	

	public String tradeIndex(){
		return SUCCESS;
	}
	
	public String tradeList()
	{
		try {
			
			List<SolTrades> tradeList=tradeservice.tradeList(username);
			System.out.println(tradeList.size());
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
				jsonArray.put(jsonObject);
			}
			responseMsg=jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
}
