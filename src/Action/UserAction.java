package Action;

import java.util.List;

import com.sun.org.apache.xml.internal.security.Init;


import Entity.SolRole;
import Entity.SolShop;
import Entity.SolShopuser;
import Entity.SolUsers;


public class UserAction extends BaseAction {
	
	public String userList() {
		try {
			List<SolRole> roleList=roleservice.roleList();
			responseMsg="[";
			//角色列表
			for(int i=0;i<roleList.size();i++)
			{
				SolRole role=roleList.get(i);
				responseMsg+="{ id:"+role.getRoleId()+", pId:0, name:'"+role.getRoleName()+"', open:true,icon:'../IMAGES/role.png'}";
				if(i<roleList.size()-1)
				{
					responseMsg+=",";
				}
				List<SolUsers> userList=userservice.findUserByRoleId(role.getRoleId());
				//对应角色的用户列表
				for(int j=0;j<userList.size();j++)
				{
					if(i==roleList.size()-1&&j==0)
					{
						responseMsg+=",";
					}
					SolUsers user=userList.get(j);
					responseMsg+="{ id:"+user.getUserId()+"00000, pId:"+role.getRoleId()+", name:'"+user.getUserUsername()+"', open:true,icon:'../IMAGES/user.png'}";
					if(i<roleList.size()-1||j<userList.size()-1)
					{
						responseMsg+=",";
					}
					
					//查找对应用户的店铺
					List<SolShopuser> shopuserList=shopuserservice.findShopUserByUserId(user.getUserId());
					for(int w=0;w<shopuserList.size();w++)
					{
						if(i==roleList.size()-1&&j==userList.size()-1&&w==0)
						{
							responseMsg+=",";
						}
						SolShopuser shopuser=shopuserList.get(w);
						responseMsg+="{ id:"+shopuser.getSolShop().getShopId()+"0000000000, pId:"+user.getUserId()+"00000, name:'"+shopuser.getSolShop().getShopName()+"',icon:'../IMAGES/shop.png'}";
						if(i<roleList.size()-1||j<userList.size()-1||w<shopuserList.size()-1)
						{
							responseMsg+=",";
						}
					}
				}
			}
			responseMsg+="]";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String addShopUser()
	{
		List<SolShopuser> shopuserList=shopuserservice.findShopUserByUserId(id);
		boolean isSame=false;
		for(int i=0;i<shopuserList.size();i++)
		{
			if(shopuserList.get(i).getSolShop().getShopId()==shopid)
			{
				isSame=true;
			}
		}
		if(isSame)
		{
			responseMsg="0";
		}
		else 
		{
			shopuserservice.addShopUser(id, shopid);
			responseMsg="1";
		}
		return SUCCESS;
	}
	
	public String deleteShopUser()
	{
		shopuserservice.deleteShopUser(id, shopid);
		responseMsg="1";
		return SUCCESS;
	}
	
	public String doAddUser()
	{
		List<SolUsers> userList=userservice.userList();
		if(!userList.isEmpty())
		{
			for(int i=0;i<userList.size();i++)
			{
				if(userList.get(i).getUserUsername().equals(username))
				{
					responseMsg="0";
					return SUCCESS;
				}
			}
		}
		String mark=roleservice.findSolRoleById(id).getRoleMark();
		if(mark.equals("是"))
		{
			SolRole srole=new SolRole();
			srole.setRoleId(id);
			
			SolUsers user=new SolUsers();
			user.setSolRole(srole);
			user.setUserUsername(username);
			user.setUserPassword(password);
			
			userservice.save(user);
			responseMsg="1";
		
			return SUCCESS;
		}
		else 
		{
			responseMsg="2";
			return SUCCESS;
		}
	}
	
}
