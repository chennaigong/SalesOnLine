package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolGoods;
import Entity.SolRates;
import Entity.SolTbgoods;

public class TbGoodsAction extends BaseAction{
	private int gid;
	
	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public String tbGoodsList()
	{
		try 
		{
			List<SolTbgoods> tbGooList=tbgoodsservice.tbGoodsList();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<tbGooList.size();i++)
			{
				SolTbgoods tbGoods=tbGooList.get(i);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("tbgoods_id", tbGoods.getTbgoodsId());
				jsonObject.put("shop_id", tbGoods.getSolShop().getShopId());
				if(tbGoods.getSolGoods()!=null)
					jsonObject.put("goods_id", tbGoods.getSolGoods().getGoodsId());
				jsonObject.put("tbgoods_numid", tbGoods.getTbgoodsNumid());
				jsonObject.put("tbgoods_title", tbGoods.getTbgoodsTitle());
				jsonObject.put("tbgoods_picurl", tbGoods.getTbgoodsPicurl());
				jsonObject.put("tbgoods_num", tbGoods.getTbgoodsNum());
				jsonObject.put("tbgoods_listtime", tbGoods.getTbgoodsListtime());
				jsonObject.put("tbgoods_delisttime", tbGoods.getTbgoodsDelisttime());
				jsonObject.put("tbgoods_modifytime", tbGoods.getTbgoodsModifytime());
				jsonObject.put("tbgoods_status", tbGoods.getTbgoodsStatus());
				jsonObject.put("tbgoods_price", tbGoods.getTbgoodsPrice());
				jsonArray.put(jsonObject);
			}
			responseMsg=jsonArray.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String modifyTbGoods()
	{
		SolTbgoods tbGoods=tbgoodsservice.findTbGoodsById(id);
		
		SolGoods goods=new SolGoods();
		goods.setGoodsId(gid);
		tbGoods.setSolGoods(goods);
		
		tbgoodsservice.updateTbGoods(tbGoods);
		responseMsg="1";
		return SUCCESS;
	}
	
}
