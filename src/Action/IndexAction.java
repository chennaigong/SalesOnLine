package Action;


import Util.WebConfig;


public class IndexAction extends BaseAction{
	
	private String sessionUrl;	//获取sessionKey的地址

	public String getSessionUrl() {
		return sessionUrl;
	}

	public void setSessionUrl(String sessionUrl) {
		this.sessionUrl = sessionUrl;
	}

	public String index(){
		
		if(top_session!=null)
		{
			return "gotourl";
		}
		
		sessionUrl=WebConfig.GETSESSIONKEYURL;
		return SUCCESS;
	}

}
