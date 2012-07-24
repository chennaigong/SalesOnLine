package Action;


import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Entity.SolTrades;
import Util.TaoBaoAPI;
import Util.WebConfig;


public class TradesAction extends BaseAction{
	
	private String sessionUrl;	//获取sessionKey的地址

	public String getSessionUrl() {
		return sessionUrl;
	}

	public void setSessionUrl(String sessionUrl) {
		this.sessionUrl = sessionUrl;
	}

	public String tradeIndex(){
		
		if(top_session!=null)
		{
			return "gotourl";
		}
		
		sessionUrl=WebConfig.GETSESSIONKEYURL;
		return SUCCESS;
	}
	
	public String tradeList()
	{
		try {
			
			List<SolTrades> tradeList=tradeservice.tradeList();
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
