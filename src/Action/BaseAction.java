package Action;

import Service.RateService;
import Service.TradeService;
import Service.UserService;
import Service.WebconfigService;
import Service.adminService;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	
	
	protected final static String SUCCESS="success";
	protected final static String USSUCCESS="ussuccess";
	
	protected TradeService tradeservice;
	protected RateService rateservice;
	protected adminService adminservice;
	protected UserService userservice;
	protected WebconfigService webconfigservice;
	
	protected String responseMsg;	//ajax����ֵ
	protected String username;
	protected String password;
	protected String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
	
}
