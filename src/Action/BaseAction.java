package Action;

import Service.RateService;
import Service.TradeService;
import Service.UserService;
import Service.WebconfigService;
import Service.adminService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	
	protected String top_session;	//sessionKey
	protected String responseMsg;	//ajax∑µªÿ÷µ
	protected final static String SUCCESS="success";
	
	protected TradeService tradeservice;
	protected RateService rateservice;
	protected adminService adminservice;
	protected UserService userservice;
	protected WebconfigService webconfigservice;
	

	public WebconfigService getWebconfigservice() {
		return webconfigservice;
	}

	public void setWebconfigservice(WebconfigService webconfigservice) {
		this.webconfigservice = webconfigservice;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public adminService getAdminservice() {
		return adminservice;
	}

	public void setAdminservice(adminService adminservice) {
		this.adminservice = adminservice;
	}

	public String index()
	{
		return SUCCESS;
	}

	public RateService getRateservice() {
		return rateservice;
	}

	public void setRateservice(RateService rateservice) {
		this.rateservice = rateservice;
	}

	public TradeService getTradeservice() {
		return tradeservice;
	}

	public void setTradeservice(TradeService tradeservice) {
		this.tradeservice = tradeservice;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	
	public String getTop_session() {
		return top_session;
	}

	public void setTop_session(String top_session) {
		this.top_session = top_session;
	}
}
