package Action;

import Util.TaoBaoAPI;

public class BuyerAction extends BaseAction {
	
	public String buyerIndex()
	{
		return SUCCESS;
	}
	
	public String buyerList()
	{
		responseMsg=TaoBaoAPI.tradeBuyerString(top_session);
		return SUCCESS;
	}
	
}
