package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolRates;
import Entity.SolTrades;
import Util.TaoBaoAPI;

public class BuyerAction extends BaseAction {
	
	public String buyerList()
	{
		try 
		{
			List tradeList=tradeservice.tradeBuyerList();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<tradeList.size();i++)
			{
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("buyer_nick", tradeList.get(i));
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
