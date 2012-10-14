package Action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolGoods;
import Entity.SolGoodstype;
import Entity.SolRates;
import Entity.SolTbgoods;

public class GoodsAction extends BaseAction{
	
	private String gname;
	private String gmnemonic;
	private String gdepart;
	private String gfactory;
	private String gsellprice;
	private String gcostprice;
	private String gdurability;
	private String galarmdays;
	private String gremark;
	private String gmark;
	
	public String getGname() {
		return gname;
	}

	public void setGname(String gname) {
		this.gname = gname;
	}
	
	public String getGmnemonic() {
		return gmnemonic;
	}

	public void setGmnemonic(String gmnemonic) {
		this.gmnemonic = gmnemonic;
	}

	public String getGdepart() {
		return gdepart;
	}

	public void setGdepart(String gdepart) {
		this.gdepart = gdepart;
	}

	public String getGfactory() {
		return gfactory;
	}

	public void setGfactory(String gfactory) {
		this.gfactory = gfactory;
	}

	public String getGsellprice() {
		return gsellprice;
	}

	public void setGsellprice(String gsellprice) {
		this.gsellprice = gsellprice;
	}

	public String getGcostprice() {
		return gcostprice;
	}

	public void setGcostprice(String gcostprice) {
		this.gcostprice = gcostprice;
	}

	public String getGdurability() {
		return gdurability;
	}

	public void setGdurability(String gdurability) {
		this.gdurability = gdurability;
	}

	public String getGalarmdays() {
		return galarmdays;
	}

	public void setGalarmdays(String galarmdays) {
		this.galarmdays = galarmdays;
	}

	public String getGremark() {
		return gremark;
	}

	public void setGremark(String gremark) {
		this.gremark = gremark;
	}

	public String getGmark() {
		return gmark;
	}

	public void setGmark(String gmark) {
		this.gmark = gmark;
	}

	public String goodsListByType()
	{
		List<SolGoods> goodsList=goodsservice.goodsListByType(id);
		try 
		{
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<goodsList.size();i++)
			{
				SolGoods goods=goodsList.get(i);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", goods.getGoodsId());
				jsonObject.put("name", goods.getGoodsName());
				jsonObject.put("mnemonic", goods.getGoodsMnemonic());
				jsonObject.put("depart", goods.getGoodsDepart());
				jsonObject.put("factory", goods.getGoodsFactory());
				jsonObject.put("sellprice", goods.getGoodsSellprice());
				jsonObject.put("costprice", goods.getGoodsCostprice());
				jsonObject.put("durability", goods.getGoodsDurability());
				jsonObject.put("alarmdays", goods.getGoodsAlarmdays());
				jsonObject.put("remark", goods.getGoodsRemark());
				jsonObject.put("mark", goods.getGoodsMark());
				jsonArray.put(jsonObject);
			}
			responseMsg=jsonArray.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String goodsListByTypeMark()
	{
		List<SolGoods> goodsList=goodsservice.goodsListByType(id,"是");
		try 
		{
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<goodsList.size();i++)
			{
				SolGoods goods=goodsList.get(i);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", goods.getGoodsId());
				jsonObject.put("name", goods.getGoodsName());
				jsonObject.put("mnemonic", goods.getGoodsMnemonic());
				jsonObject.put("depart", goods.getGoodsDepart());
				jsonObject.put("factory", goods.getGoodsFactory());
				jsonObject.put("sellprice", goods.getGoodsSellprice());
				jsonObject.put("costprice", goods.getGoodsCostprice());
				jsonObject.put("durability", goods.getGoodsDurability());
				jsonObject.put("alarmdays", goods.getGoodsAlarmdays());
				jsonObject.put("remark", goods.getGoodsRemark());
				jsonObject.put("mark", goods.getGoodsMark());
				jsonArray.put(jsonObject);
			}
			responseMsg=jsonArray.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String goodsListById()
	{
		SolGoods goods=goodsservice.goodsListById(id);
		try 
		{
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", goods.getGoodsId());
			jsonObject.put("name", goods.getGoodsName());
			jsonObject.put("mnemonic", goods.getGoodsMnemonic());
			jsonObject.put("depart", goods.getGoodsDepart());
			jsonObject.put("factory", goods.getGoodsFactory());
			jsonObject.put("sellprice", goods.getGoodsSellprice());
			jsonObject.put("costprice", goods.getGoodsCostprice());
			jsonObject.put("durability", goods.getGoodsDurability());
			jsonObject.put("alarmdays", goods.getGoodsAlarmdays());
			jsonObject.put("remark", goods.getGoodsRemark());
			jsonObject.put("mark", goods.getGoodsMark());
			jsonObject.put("nowquantity", goods.getGoodsNowquantity());
			jsonArray.put(jsonObject);
			responseMsg=jsonArray.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String disableGoods()
	{
		goodsservice.modifyGoodsMark(id, "否");
		responseMsg="1";
		return SUCCESS;
	}
	
	public String enableGoods()
	{
		goodsservice.modifyGoodsMark(id, "是");
		SolGoods goods=goodsservice.goodsListById(id);
		id=goods.getSolGoodstype().getGoodstypeId();
		
		SolGoodstype goodstype=goodstypeservice.findGoodstypeById(id);
		//将修改应用于父类别中
		while(id!=0)
		{
			goodstype=goodstypeservice.findGoodstypeById(id);
			goodstypeservice.modifyGoodsType(id, "是");
			id=Integer.parseInt(goodstype.getGoodstypeParent());
		}
		responseMsg="1";
		return SUCCESS;
	}
	
	public String doAddGoods()
	{
		goodsservice.addGoods(id,0,gname,gmnemonic,gdepart,gfactory,gsellprice,gcostprice,
				gdurability,galarmdays,gremark,gmark);
		responseMsg="1";
		return SUCCESS;
	}
	
	public String goodsRateList()
	{
		List<SolTbgoods> tbList=tbgoodsservice.tbGoodsListByGoods(id);
		List<SolRates> rateList=new ArrayList<SolRates>();
		for(int i=0;i<tbList.size();i++)
		{
			SolTbgoods tbgoods=tbList.get(i);
			String numId=tbgoods.getTbgoodsNumid();
			List<SolRates> rateList_tmp=rateservice.rateListByNumId(numId);
			rateList.addAll(rateList_tmp);
		}
		int goodCount=0;
		int neutralCount=0;
		int badCount=0;
		for(int i=0;i<rateList.size();i++)
		{
			SolRates rate=rateList.get(i);
			if(rate.getRateResult().equals("good"))
			{
				goodCount++;
			}
			else if(rate.getRateResult().equals("neutral"))
			{
				neutralCount++;
			}
			else if(rate.getRateResult().equals("bad"))
			{
				badCount++;
			}
		}
		responseMsg=goodCount+","+neutralCount+","+badCount;
		return SUCCESS;
	}
	
}
