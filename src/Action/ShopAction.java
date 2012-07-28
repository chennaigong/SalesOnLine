package Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;

import Entity.SolShop;
import Entity.SolUsers;

public class ShopAction extends BaseAction {
	
	public String shopList()
	{
		try {
			List<SolShop> shopList = shopservice.shopList();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < shopList.size(); i++) {
				SolShop shop = shopList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", shop.getShopId());
				jsonObject.put("session", shop.getShopSessionkey());
				jsonObject.put("ispromise", shop.getShopIspromise());
				jsonArray.put(jsonObject);
			}
			responseMsg = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String usShopList()
	{
		Map session=ActionContext.getContext().getSession();
		String username=(String) session.get("username");
		SolUsers user=userservice.findSolUser(username);
		String userCan=user.getUserCan();
		if(userCan!=null)
		{
			String[] shopIds=userCan.split(",");
			
			List<SolShop> shopList=new ArrayList<SolShop>();
			
			for(int i=0;i<shopIds.length;i++)
			{
				SolShop shop=shopservice.findSOLShop(Integer.parseInt(shopIds[i]));
				shopList.add(shop);
			}
			try {
				JSONArray jsonArray = new JSONArray();
				for (int i = 0; i < shopList.size(); i++) {
					SolShop shop = shopList.get(i);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("id", shop.getShopId());
					jsonObject.put("session", shop.getShopSessionkey());
					jsonObject.put("ispromise", shop.getShopIspromise());
					jsonArray.put(jsonObject);
				}
				responseMsg = jsonArray.toString();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return SUCCESS;
	}
	
}
