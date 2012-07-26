package Action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import Entity.SolUsers;
import Util.TaoBaoAPI;


public class LogisticsAction extends BaseAction {

	private String tid;
	private String out_id;
	private String company_code;	
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOut_id() {
		return out_id;
	}

	public void setOut_id(String out_id) {
		this.out_id = out_id;
	}

	public String getCompany_code() {
		return company_code;
	}

	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}

	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String logisticsIndex()
	{
		return SUCCESS;
	}
	
	public String logisticsList()
	{
		responseMsg=TaoBaoAPI.logisticString();
		return SUCCESS;
	}
	
	public String logicsticsSend()
	{
		SolUsers user=userservice.findSolUser(username);
		String top_session=user.getUserSessionkey();
		TaoBaoAPI.logisticSend(top_session, Long.valueOf(tid), out_id, company_code.split("@")[0]);
		Map session=ActionContext.getContext().getSession();
		String role=(String) session.get("role");
		
		if(role.equals("0"))
		{
			String username=(String) session.get("username");
			url="../userIndex.action?username="+username;
		}
		else 
		{
			url="userIndex.action";
		}
		
		return SUCCESS;
	}
	
}
