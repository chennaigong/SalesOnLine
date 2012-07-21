package Action;

import Util.TaoBaoAPI;


public class RatesAction extends BaseAction {
	

	public String rateList()
	{
		responseMsg=TaoBaoAPI.rateString(top_session);
		return SUCCESS;
	}
	
	public String rateIndex()
	{
		return SUCCESS;
	}
}
