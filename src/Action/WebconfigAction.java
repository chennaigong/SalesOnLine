package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolWebconfig;

public class WebconfigAction extends BaseAction{

	public String webconfigList()
	{
		try {
			List<SolWebconfig> webconfigList=webconfigservice.webconfigList();
			JSONArray jsonArray=new JSONArray();
			JSONObject jsonObject=new JSONObject();
			
			for(int i=0;i<webconfigList.size();i++)
			{
				SolWebconfig webconfig=webconfigList.get(i);
				jsonObject.put(webconfig.getWebconfigKey(), webconfig.getWebconfigValue());
			}
			
			jsonArray.put(jsonObject);
			
			responseMsg=jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
