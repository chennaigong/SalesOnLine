package Action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

import Entity.SolAdmin;
import Entity.SolUsers;


public class LoginAction extends BaseAction
{

	public String doLogin()
	{
		
		if(role.equals("普通用户"))
		{
			SolUsers user=userservice.findSolUser(username, password);
			if(user==null)
			{
				responseMsg="0";
			}
			else 
			{
				responseMsg="mainIndex.action";
				Map session=ActionContext.getContext().getSession();
				session.put("role", "0");
				session.put("username", username);
			}
		}
		else if(role.equals("管理员"))
		{
			SolAdmin admin=adminservice.findSolAdmin(username, password);
			if(admin==null)
			{
				responseMsg="0";
			}
			else 
			{
				responseMsg="admin/mainIndex.action";
				Map session=ActionContext.getContext().getSession();
				session.put("role", "1");
			}
		}
		return SUCCESS;
	}
	
	public String loginOut()
	{
		Map session=ActionContext.getContext().getSession();
		session.remove("role");
		session.remove("username");
		return SUCCESS;
	}
}
