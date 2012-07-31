package Action;

import Service.FunctionRoleService;
import Service.FunctionService;
import Service.RateService;
import Service.RoleService;
import Service.ShopService;
import Service.TradeService;
import Service.UserService;
import Service.WebconfigService;
import Service.adminService;
import ServiceImpl.shopServiceImpl;

import com.opensymphony.xwork2.ActionSupport;

public class BaseAction extends ActionSupport{
	
	
	protected final static String SUCCESS="success";
	
	protected TradeService tradeservice;
	protected RateService rateservice;
	protected adminService adminservice;
	protected UserService userservice;
	protected WebconfigService webconfigservice;
	protected ShopService shopservice;
	protected FunctionService functionservice;
	protected RoleService roleservice;
	protected FunctionRoleService functionroleservice;

	protected String responseMsg;	//ajax����ֵ
	protected String username;
	protected String password;
	protected String role;
	protected int shopid;
	protected int id;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShopid() {
		return shopid;
	}

	public void setShopid(int shopid) {
		this.shopid = shopid;
	}

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
	
	public RoleService getRoleservice() {
		return roleservice;
	}

	public void setRoleservice(RoleService roleservice) {
		this.roleservice = roleservice;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public FunctionService getFunctionservice() {
		return functionservice;
	}

	public void setFunctionservice(FunctionService functionservice) {
		this.functionservice = functionservice;
	}
	
	public ShopService getShopservice() {
		return shopservice;
	}

	public void setShopservice(ShopService shopservice) {
		this.shopservice = shopservice;
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

	public FunctionRoleService getFunctionroleservice() {
		return functionroleservice;
	}

	public void setFunctionroleservice(FunctionRoleService functionroleservice) {
		this.functionroleservice = functionroleservice;
	}
	
}
