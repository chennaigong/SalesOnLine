package Action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.views.jsp.ElseTag;
import org.json.JSONArray;
import org.json.JSONObject;

import Util.TaoBaoAPI;
import Util.WebConfig;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.util.ResolverUtil.IsA;

import Entity.SolShop;
import Entity.SolUsers;

public class ShopAction extends BaseAction {
	
	private String top_session;
	public String getTop_session() {
		return top_session;
	}

	public void setTop_session(String top_session) {
		this.top_session = top_session;
	}

	public String shopList()
	{
		try {
			List<SolShop> shopList = shopservice.shopList();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < shopList.size(); i++) {
				SolShop shop = shopList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("id", shop.getShopId());
				jsonObject.put("name", shop.getShopName());
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
	
	public String addShop()
	{
		if(top_session!=null)
		{
			List<SolShop> shopList=shopservice.shopList();
			if(shopList.isEmpty())
			{
				try 
				{
					int count=0;
					String name=null;
					while (name==null&&count<8) {
						String nickStr=TaoBaoAPI.userNickString(top_session);
						String nick=nickStr.substring(nickStr.lastIndexOf("{"), nickStr.indexOf("}")+1);
						JSONObject nickObject=new JSONObject(nick);
						String n=nickObject.getString("nick");
						
						String shopStr=TaoBaoAPI.shopTitleString(n);
						String shop=shopStr.substring(shopStr.lastIndexOf("{"), shopStr.indexOf("}")+1);
						JSONObject titleObject=new JSONObject(shop);
						name=titleObject.getString("title");
					}
					if (name==null) {
						responseMsg="������ϣ����Ժ�����";
					}
					else {
						shopservice.addShop(top_session,name);
						responseMsg="��ӳɹ�";
					}
					
					
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			else
			{
				//���ж��Ƿ�����ͬ��sessionKey
				boolean isSame=false;
				for(int i=0;i<shopList.size();i++)
				{
					if(shopList.get(i).getShopSessionkey().equals(top_session))
					{
						isSame=true;
					}
				}
				//���ж�sessionKey�Ƿ�����ͬһ���û�,Ҫ�����Ա�API
				if(!isSame)
				{
					boolean isUserSame=false;
					int sameIndex=-1;
					for(int i=0;i<shopList.size();i++)
					{
						String oldUser=null;
						String newUser=null;
						int count1=0;
						int count2=0;
						while(count1<=8&&oldUser==null)
						{
							oldUser=TaoBaoAPI.userInfoString(shopList.get(i).getShopSessionkey());
							count1++;
						}
						while(count2<=8&&newUser==null)
						{
							newUser=TaoBaoAPI.userInfoString(top_session);
							count2++;
						}
						//����apiʧ��
						if(oldUser==null||newUser==null)
						{
							responseMsg="�������,���Ժ�����";
							return SUCCESS;
						}
						if(oldUser.equals(newUser))
						{
							isUserSame=true;
							sameIndex=i;
						}
					}
					if(!isUserSame)
					{
						try 
						{
							int count=0;
							String name=null;
							while (name==null&&count<8) {
								String nickStr=TaoBaoAPI.userNickString(top_session);
								String nick=nickStr.substring(nickStr.lastIndexOf("{"), nickStr.indexOf("}")+1);
								JSONObject nickObject=new JSONObject(nick);
								String n=nickObject.getString("nick");
								
								String shopStr=TaoBaoAPI.shopTitleString(n);
								String shop=shopStr.substring(shopStr.lastIndexOf("{"), shopStr.indexOf("}")+1);
								JSONObject titleObject=new JSONObject(shop);
								name=titleObject.getString("title");
							}
							if (name==null) {
								responseMsg="������ϣ����Ժ�����";
							}
							else {
								shopservice.addShop(top_session,name);
								responseMsg="��ӳɹ�";
							}
							
							
						} 
						catch (Exception e) {
							e.printStackTrace();
						}
					}
					else 
					{
						SolShop shop=shopList.get(sameIndex);
						shop.setShopIspromise("��");
						shop.setShopSessionkey(top_session);
						shopservice.modifyShop(shop);
						responseMsg="������Ȩ�ɹ�";
					}
				}
				else 
				{
					responseMsg="�����Ѵ��ڣ����ѳɹ���Ȩ";
				}
			}
		}
		
		return SUCCESS;
	}
	
	public String refreshShop()
	{
		SolShop shop=shopservice.findSOLShop(shopid);
		String userInfo=TaoBaoAPI.userInfoString(shop.getShopSessionkey());
		if(userInfo.indexOf("user_id")==-1)
		{
			responseMsg="0";
			shop.setShopIspromise("��");
			shopservice.modifyShop(shop);
		}
		else 
		{
			responseMsg="1";
			shop.setShopIspromise("��");
			shopservice.modifyShop(shop);
		}
		return SUCCESS;
	}
	
	
}
