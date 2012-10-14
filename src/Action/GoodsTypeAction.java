package Action;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolGoods;
import Entity.SolGoodstype;
import Entity.SolRates;

public class GoodsTypeAction extends BaseAction{
	
	private String mark;
	private String name;
	private String isLeaf;
	private String pId;
	public String getPId() {
		return pId;
	}

	public void setPId(String id) {
		pId = id;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String goodsTypeList()
	{
		List<SolGoodstype> goodsTypeList=goodstypeservice.goodsTypeList();
		responseMsg="[";
		for(int i=0;i<goodsTypeList.size();i++)
		{
			SolGoodstype goodstype=goodsTypeList.get(i);
			responseMsg+="{ id:'type"+goodstype.getGoodstypeId()+"', pId:'type"+goodstype.getGoodstypeParent()
						+"', name:'"+goodstype.getGoodstypeName()+"', open:true,icon:'../IMAGES/type.png'}";
			if(i<goodsTypeList.size()-1)
			{
				responseMsg+=",";
			}
		}
		responseMsg+="]";
		return SUCCESS;
	}
	
	public String goodsTypeListByMark()
	{
		List<SolGoodstype> goodsTypeList=goodstypeservice.goodsTypeList("是");
		responseMsg="[";
		for(int i=0;i<goodsTypeList.size();i++)
		{
			SolGoodstype goodstype=goodsTypeList.get(i);
			responseMsg+="{ id:'type"+goodstype.getGoodstypeId()+"', pId:'type"+goodstype.getGoodstypeParent()
						+"', name:'"+goodstype.getGoodstypeName()+"', open:true,icon:'../IMAGES/type.png'}";
			if(i<goodsTypeList.size()-1)
			{
				responseMsg+=",";
			}
		}
		responseMsg+="]";
		return SUCCESS;
	}
	
	
	public String goodsTypeListByPId()
	{
		List<SolGoodstype> goodsTypeList=goodstypeservice.goodsTypeList(id);
		try {
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<goodsTypeList.size();i++)
			{
				SolGoodstype type=goodsTypeList.get(i);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", type.getGoodstypeId());
				jsonObject.put("name", type.getGoodstypeName());
				jsonObject.put("mark", type.getGoodstypeMark());
				jsonArray.put(jsonObject);
			}
			responseMsg = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String goodsTypeListById()
	{
		SolGoodstype goodsType=goodstypeservice.findGoodstypeById(id);
		try {
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("id", goodsType.getGoodstypeId());
			jsonObject.put("name", goodsType.getGoodstypeName());
			jsonObject.put("mark", goodsType.getGoodstypeMark());
			jsonObject.put("isleaf", goodsType.getGoodstypeIsleaf());
			jsonArray.put(jsonObject);
			responseMsg = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String modifyGoodsTypeMarkN()
	{
		goodstypeservice.modifyGoodsType(id, mark);
		
		SolGoodstype tmpGoodstype=goodstypeservice.findGoodstypeById(id);
		//如果点击商品的直接父类，则先禁用商品
		if(tmpGoodstype.getGoodstypeIsleaf().equals("是"))
		{
			List<SolGoods> goodsList=goodsservice.goodsListByType(tmpGoodstype.getGoodstypeId());
			for(int j=0;j<goodsList.size();j++)
			{
				SolGoods goods=goodsList.get(j);
				goodsservice.modifyGoodsMark(goods.getGoodsId(), "否");
			}
		}
		//将修改应用于子类别中
		List<SolGoodstype> goodstypeList=goodstypeservice.goodsTypeList(id);
		while (!goodstypeList.isEmpty()) {
			List<SolGoodstype> goodstypeList_tmp=new ArrayList<SolGoodstype>();
			for(int i=0;i<goodstypeList.size();i++)
			{
				SolGoodstype goodstype=goodstypeList.get(i);
				goodstypeservice.modifyGoodsType(goodstype.getGoodstypeId(), mark);
				
				String isLeaf=goodstype.getGoodstypeIsleaf();
				//其下有商品列表，则禁用商品
				if(isLeaf.equals("是"))
				{
					List<SolGoods> goodsList=goodsservice.goodsListByType(goodstype.getGoodstypeId());
					for(int j=0;j<goodsList.size();j++)
					{
						SolGoods goods=goodsList.get(j);
						goodsservice.modifyGoodsMark(goods.getGoodsId(), "否");
					}
				}
				
				List<SolGoodstype> goodstypeList_son=goodstypeservice.goodsTypeList(goodstype.getGoodstypeId());
				goodstypeList_tmp.addAll(goodstypeList_son);
			}
			goodstypeList=goodstypeList_tmp;
		}
			
		responseMsg="1";
		return SUCCESS;
	}
	
	public String modifyGoodsTypeMarkY()
	{
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
	
	public String doAddSGoodType()
	{
		goodstypeservice.addSGoodsType(name, pId, isLeaf, mark);
		responseMsg="1";
		return SUCCESS;
	}
}
