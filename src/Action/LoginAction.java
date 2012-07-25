package Action;

import Entity.SolAdmin;
import Entity.SolUsers;


public class LoginAction extends BaseAction
{

	public String doLogin()
	{
		
		if(role.equals("��ͨ�û�"))
		{
			SolUsers user=userservice.findSolUser(username, password);
			if(user==null)
			{
				responseMsg="0";
			}
			else 
			{
				responseMsg="1";
			}
		}
		else if(role.equals("����Ա"))
		{
			SolAdmin admin=adminservice.findSolAdmin(username, password);
			if(admin==null)
			{
				responseMsg="0";
			}
			else 
			{
				responseMsg="userIndex.action";
			}
		}
		return SUCCESS;
	}
}
