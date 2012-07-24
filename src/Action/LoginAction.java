package Action;

import Entity.SolAdmin;
import Entity.SolUsers;


public class LoginAction extends BaseAction
{
	private String username;
	private String password;
	private String role;
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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
				responseMsg="1";
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
				responseMsg="1";
			}
		}
		return SUCCESS;
	}
}
