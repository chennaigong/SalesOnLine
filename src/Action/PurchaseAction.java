package Action;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import Entity.SolGoods;
import Entity.SolInputgoods;
import Entity.SolPurchase;
import Entity.SolPurchasedetail;
import Entity.SolRole;
import Entity.SolUsers;

public class PurchaseAction extends BaseAction{
	private String detail;
	private String ptime;
	private String pbussiness;
	private String pprice;
	private String pprint;
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPtime() {
		return ptime;
	}
	public void setPtime(String ptime) {
		this.ptime = ptime;
	}
	public String getPbussiness() {
		return pbussiness;
	}
	public void setPbussiness(String pbussiness) {
		this.pbussiness = pbussiness;
	}
	public String getPprice() {
		return pprice;
	}
	public void setPprice(String pprice) {
		this.pprice = pprice;
	}
	public String getPprint() {
		return pprint;
	}
	public void setPprint(String pprint) {
		this.pprint = pprint;
	}
	
	public String doAddPurchase()
	{
		
		Map session=ActionContext.getContext().getSession();
		String role=(String) session.get("role");
		SolUsers users=null;
		if(role!=null)
		{
			if(role.equals("0"))
			{
				String username=(String) session.get("username");
				users=userservice.findSolUser(username);
			}
		}
		
		SolPurchase purchase=purchaseservice.addPurchase(ptime, pbussiness, pprice, pprint);
		SolInputgoods inputgoods=inputgoodsservice.addInputgoods(users, null, null, "´ýÈë¿â", null);
		String[] pdetailStrings=detail.split(",");
		for(int i=0;i<pdetailStrings.length;i++)
		{
			String goodsId=pdetailStrings[i].split("-")[0];
			SolGoods goods=new SolGoods();
			goods.setGoodsId(Integer.parseInt(goodsId));
			
			String quantity=pdetailStrings[i].split("-")[1];
			String cost=pdetailStrings[i].split("-")[2];
			purchasedetailservice.addPurchaseDetail(goods, purchase, Integer.parseInt(quantity), cost);
			inputgoodsdetailservice.addInputGoodsDetail(goods, inputgoods, null, null, Integer.parseInt(quantity),
					Integer.parseInt(quantity), cost);
		}
		responseMsg="1";
		return SUCCESS;
	}
	
	public String doPurchaseList()
	{
		List<SolPurchase> purchaseList=purchaseservice.purchaseList();
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<purchaseList.size();i++)
		{
			SolPurchase purchase=purchaseList.get(i);
			JSONObject jsonObject=new JSONObject();
			
			try {
				jsonObject.put("id", purchase.getPurchaseId());
				jsonObject.put("bussiness", purchase.getPurchaseName());
				jsonObject.put("time", purchase.getPurchaseTime());
				jsonObject.put("price", purchase.getPurchasePrice());
				jsonObject.put("print", purchase.getPrintId());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		responseMsg= jsonArray.toString();
		return SUCCESS;
	}
	
	public String doPurchaseDetailList()
	{
		List<SolPurchasedetail> purchaseDetailList=purchasedetailservice.purchaseDetailList(id);
		JSONArray jsonArray=new JSONArray();
		for(int i=0;i<purchaseDetailList.size();i++)
		{
			SolPurchasedetail purchaseDetail=purchaseDetailList.get(i);
			JSONObject jsonObject=new JSONObject();
			
			try {
				jsonObject.put("id", purchaseDetail.getPurchasedetailId());
				jsonObject.put("goods", purchaseDetail.getSolGoods().getGoodsName());
				jsonObject.put("quantity", purchaseDetail.getPurchasedetailQuantity());
				jsonObject.put("cost", purchaseDetail.getPurchasedetailCost());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonArray.put(jsonObject);
		}
		responseMsg= jsonArray.toString();
		return SUCCESS;
	}
}
