package Action;


import Util.TaoBaoAPI;


public class TradesAction extends BaseAction{
	
	public String tradeList()
	{
		responseMsg=TaoBaoAPI.tradeString(top_session);
		return SUCCESS;
	}
	
}
