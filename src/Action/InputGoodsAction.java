package Action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.registry.infomodel.InternationalString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Entity.SolInputgoods;
import Entity.SolInputgoodsdetail;
import Entity.SolPurchase;
import Entity.SolRates;
import Entity.SolUsers;

import com.opensymphony.xwork2.ActionContext;


public class InputGoodsAction extends BaseAction
{
	private String mark;
	private String detail;
	private String print;
	
	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getPrint() {
		return print;
	}

	public void setPrint(String print) {
		this.print = print;
	}

	public String inputGoodsList()
	{
		List<SolInputgoods> inputGoodsList=inputgoodsservice.inputGoodsList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<inputGoodsList.size();i++)
		{
			SolInputgoods inputgoods=inputGoodsList.get(i);
			JSONObject jsonObject=new JSONObject();
			
			try {
				jsonObject.put("id", inputgoods.getInputgoodsId());
				if(inputgoods.getSolUsers()==null)
				{
					jsonObject.put("user", "管理员");
				}
				else
				{
					jsonObject.put("user", inputgoods.getSolUsers().getUserUsername());
				}
				
				jsonObject.put("time", inputgoods.getInputgoodsTime());
				jsonObject.put("remark", inputgoods.getInputgoodsMark());
				jsonObject.put("statue", inputgoods.getInputgoodsStatue());
				jsonObject.put("print", inputgoods.getPrintId());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		responseMsg= jsonArray.toString();
		return SUCCESS;
	}
	
	public String inputGoodsDetailList()
	{
		List<SolInputgoodsdetail> inputGoodsDetailList=inputgoodsdetailservice.inputGoodsDetailList(id);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<inputGoodsDetailList.size();i++)
		{
			SolInputgoodsdetail inputgoodsdetail=inputGoodsDetailList.get(i);
			JSONObject jsonObject=new JSONObject();
			
			try {
				jsonObject.put("id", inputgoodsdetail.getInputgoodsdetailId());
				jsonObject.put("goods", inputgoodsdetail.getSolGoods().getGoodsName());
				jsonObject.put("warntime", inputgoodsdetail.getInputgoodsdetailWarntime());
				jsonObject.put("badtime", inputgoodsdetail.getInputgoodsdetailBadtime());
				jsonObject.put("iquantity", inputgoodsdetail.getInputgoodsdetailInquantity());
				jsonObject.put("nowquantity", inputgoodsdetail.getInputgoodsdetailNowquantity());
				jsonObject.put("cost", inputgoodsdetail.getInputgoodsdetailCost());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		responseMsg= jsonArray.toString();
		return SUCCESS;
	}
	
	public String updateInputGoods()
	{
		SimpleDateFormat   sDateFormat   =   new   SimpleDateFormat("yyyy-MM-dd HH:mm:ss");   
		String   date   =   sDateFormat.format(new Date());
		//更新入库表
		inputgoodsservice.updateInputgoods(id, date, mark, "已入库", print);
		String[] inStrings=detail.split(",");
		for(int i=0;i<inStrings.length;i++)
		{
			String detailId=inStrings[i].split("@")[0];
			String warntime=inStrings[i].split("@")[1];
			String badtime=inStrings[i].split("@")[2];
			String quantity=inStrings[i].split("@")[3];
			//更新入库明细表
			inputgoodsdetailservice.updateInputGoodsDetail(Integer.parseInt(detailId), warntime, 
					badtime, Integer.parseInt(quantity));
			SolInputgoodsdetail detail=inputgoodsdetailservice.inputGoodsDetailListById(Integer.parseInt(detailId));
			//更新货品当前数量
			goodsservice.modifyGoods(detail.getSolGoods().getGoodsId(), Integer.parseInt(quantity));
			//更新货品成本价
			List<SolInputgoodsdetail> inputGoodsDetailList=inputgoodsdetailservice
				.inputGoodsDetailListByGoods(detail.getSolGoods().getGoodsId());
			float qu=0;
			float co=0;
			for(int j=0;j<inputGoodsDetailList.size();j++)
			{
				SolInputgoodsdetail inputgoods=inputGoodsDetailList.get(j);
				float q=Float.parseFloat(String.valueOf(inputgoods.getInputgoodsdetailNowquantity()));
				float c=Float.parseFloat(inputgoods.getInputgoodsdetailCost());
				co+=q*c;
				qu+=q;
			}
			float goodsCost=(float)co/(float)qu;
			goodsservice.modifyGoods(detail.getSolGoods().getGoodsId(), String.valueOf(goodsCost));
			
		}
		
		responseMsg="1";
		return SUCCESS;
	}
}
