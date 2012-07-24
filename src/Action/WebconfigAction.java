package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolWebconfig;

public class WebconfigAction extends BaseAction{

	private String defaultTime;
	private String threadInterval;
	
	public String getDefaultTime() {
		return defaultTime;
	}

	public void setDefaultTime(String defaultTime) {
		this.defaultTime = defaultTime;
	}

	public String getThreadInterval() {
		return threadInterval;
	}

	public void setThreadInterval(String threadInterval) {
		this.threadInterval = threadInterval;
	}

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
	
	public String updateWebconfig()
	{
		webconfigservice.updateWebconfig(defaultTime,threadInterval);
		responseMsg="1";
		return SUCCESS;
	}
}
