package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolTbgoods;
import Entity.SolTradeorder;
import Entity.SolTrades;

public class TradeOrderAction extends BaseAction{
	private String tid;

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}
	
	public String tradeOrderIndex()
	{
		return SUCCESS;
	}
	public String tradeOrderList()
	{
		try 
		{
			List<SolTradeorder> tradeOrderList=tradeorderservice.tradeOrderList(tid);
			
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<tradeOrderList.size();i++)
			{
				SolTradeorder tradeOrder=tradeOrderList.get(i);
				
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", tradeOrder.getTradeorderId());
				jsonObject.put("tid", tradeOrder.getSolTrades().getTradeId());
				jsonObject.put("oid", tradeOrder.getTradeorderOid());
				jsonObject.put("numid", tradeOrder.getTradeorderNumid());
				
				SolTbgoods tbgoods=tbgoodsservice.findTbGoodsByNumId(tradeOrder.getTradeorderNumid());
				if(tbgoods==null)
				{
					jsonObject.put("name","此商品已被删除");
				}
				else 
				{
					jsonObject.put("name", tbgoods.getTbgoodsTitle());
				}
				jsonObject.put("num", tradeOrder.getTradeorderNum());
				jsonArray.put(jsonObject);
			}
			responseMsg=jsonArray.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return SUCCESS;
	}
	
}
