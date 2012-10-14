package Action;

import Service.FunctionRoleService;
import Service.FunctionService;
import Service.GoodsService;
import Service.GoodsTypeService;
import Service.InoutGoodsService;
import Service.InputGoodsDetailService;
import Service.InputGoodsService;
import Service.OutputGoodsDetailService;
import Service.OutputGoodsService;
import Service.PurchaseDetailService;
import Service.PurchaseService;
import Service.RateService;
import Service.RoleService;
import Service.ShopService;
import Service.ShopUserService;
import Service.TbGoodsService;
import Service.TradeOrderService;
import Service.TradeService;
import Service.UserService;
import Service.WebconfigService;
import Service.adminService;

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
	protected ShopUserService shopuserservice;
	protected GoodsTypeService goodstypeservice;
	protected GoodsService goodsservice;
	protected TbGoodsService tbgoodsservice;
	protected TradeOrderService tradeorderservice;
	protected InputGoodsService inputgoodsservice;
	protected OutputGoodsService outputgoodsservice;
	protected InoutGoodsService inoutgoodsservice;
	protected PurchaseService purchaseservice;
	protected PurchaseDetailService purchasedetailservice;
	protected InputGoodsDetailService inputgoodsdetailservice;
	protected OutputGoodsDetailService outputgoodsdetailservice;
	
	protected String responseMsg;	//ajax∑µªÿ÷µ
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
	
	public TradeOrderService getTradeorderservice() {
		return tradeorderservice;
	}

	public void setTradeorderservice(TradeOrderService tradeorderservice) {
		this.tradeorderservice = tradeorderservice;
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
	
	public GoodsService getGoodsservice() {
		return goodsservice;
	}

	public TbGoodsService getTbgoodsservice() {
		return tbgoodsservice;
	}

	public void setTbgoodsservice(TbGoodsService tbgoodsservice) {
		this.tbgoodsservice = tbgoodsservice;
	}
	
	public void setGoodsservice(GoodsService goodsservice) {
		this.goodsservice = goodsservice;
	}
	
	public InoutGoodsService getInoutgoodsservice() {
		return inoutgoodsservice;
	}

	public void setInoutgoodsservice(InoutGoodsService inoutgoodsservice) {
		this.inoutgoodsservice = inoutgoodsservice;
	}
	
	public OutputGoodsService getOutputgoodsservice() {
		return outputgoodsservice;
	}

	public void setOutputgoodsservice(OutputGoodsService outputgoodsservice) {
		this.outputgoodsservice = outputgoodsservice;
	}

	public PurchaseService getPurchaseservice() {
		return purchaseservice;
	}

	public void setPurchaseservice(PurchaseService purchaseservice) {
		this.purchaseservice = purchaseservice;
	}
	
	public InputGoodsService getInputgoodsservice() {
		return inputgoodsservice;
	}

	public void setInputgoodsservice(InputGoodsService inputgoodsservice) {
		this.inputgoodsservice = inputgoodsservice;
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
	
	public GoodsTypeService getGoodstypeservice() {
		return goodstypeservice;
	}

	public void setGoodstypeservice(GoodsTypeService goodstypeservice) {
		this.goodstypeservice = goodstypeservice;
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

	public ShopUserService getShopuserservice() {
		return shopuserservice;
	}

	public void setShopuserservice(ShopUserService shopuserservice) {
		this.shopuserservice = shopuserservice;
	}

	public PurchaseDetailService getPurchasedetailservice() {
		return purchasedetailservice;
	}

	public void setPurchasedetailservice(PurchaseDetailService purchasedetailservice) {
		this.purchasedetailservice = purchasedetailservice;
	}

	public InputGoodsDetailService getInputgoodsdetailservice() {
		return inputgoodsdetailservice;
	}

	public void setInputgoodsdetailservice(
			InputGoodsDetailService inputgoodsdetailservice) {
		this.inputgoodsdetailservice = inputgoodsdetailservice;
	}

	public OutputGoodsDetailService getOutputgoodsdetailservice() {
		return outputgoodsdetailservice;
	}

	public void setOutputgoodsdetailservice(
			OutputGoodsDetailService outputgoodsdetailservice) {
		this.outputgoodsdetailservice = outputgoodsdetailservice;
	}

	
	
}
