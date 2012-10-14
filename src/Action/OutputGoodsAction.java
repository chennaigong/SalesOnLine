package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import Entity.SolInputgoodsdetail;
import Entity.SolOutputgoods;
import Entity.SolOutputgoodsdetail;

public class OutputGoodsAction extends BaseAction
{
	public String outputGoodsList()
	{
		List<SolOutputgoods> outputGoodsList=outputgoodsservice.outputGoodsList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<outputGoodsList.size();i++)
		{
			SolOutputgoods outputgoods=outputGoodsList.get(i);
			JSONObject jsonObject=new JSONObject();
			
			try {
				jsonObject.put("id", outputgoods.getOutputgoodsId());
				if(outputgoods.getSolUsers()==null)
				{
					jsonObject.put("user", "¹ÜÀíÔ±");
				}
				else
				{
					jsonObject.put("user", outputgoods.getSolUsers().getUserUsername());
				}
				
				jsonObject.put("time", outputgoods.getOutputgoodsTime());
				jsonObject.put("towhere", outputgoods.getOutputgoodsTowhere());
				jsonObject.put("mark", outputgoods.getOutputgoodsMark());
				jsonObject.put("statue", outputgoods.getOutputgoodsStatue());
				jsonObject.put("print", outputgoods.getPrintId());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		responseMsg= jsonArray.toString();
		return SUCCESS;
	}
	
	public String outputGoodsDetailList()
	{
		List<SolOutputgoodsdetail> outputGoodsDetailList=outputgoodsdetailservice.outputGoodsDetailList(id);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<outputGoodsDetailList.size();i++)
		{
			SolOutputgoodsdetail outputgoodsdetail=outputGoodsDetailList.get(i);
			JSONObject jsonObject=new JSONObject();
			
			try {
				jsonObject.put("id", outputgoodsdetail.getOutputgoodsdetailId());
				jsonObject.put("quantity", outputgoodsdetail.getOutputgoodsdetailQuantity());
				jsonObject.put("price", outputgoodsdetail.getOutputgoodsdetailPrice());
				jsonObject.put("goodsname", outputgoodsdetail.getGoodsId());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		responseMsg= jsonArray.toString();
		return SUCCESS;
	}
}
