package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolFunction;
import Entity.SolFunctionrole;
import Entity.SolRole;

public class RoleAction extends BaseAction{
	
	private String functionids;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunctionids() {
		return functionids;
	}

	public void setFunctionids(String functionids) {
		this.functionids = functionids;
	}

	public String roleList()
	{
		try {
			List<SolRole> rolelList=roleservice.roleList();
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<rolelList.size();i++)
			{
				SolRole role=rolelList.get(i);
				
				String havefun="";
				List<SolFunctionrole> functionroleList=functionroleservice.findFunRoleListByRoleId(role.getRoleId());
				if(!functionroleList.isEmpty())
				{
					for(int j=0;j<functionroleList.size();j++)
					{
						SolFunctionrole functionrole=functionroleList.get(j);
						String functionname=functionrole.getSolFunction().getFunctionName();
						String functionid=String.valueOf(functionrole.getSolFunction().getFunctionId());
						havefun+=functionid+"-"+functionname;
						if(j<functionroleList.size()-1)
						{
							havefun+=",";
						}
					}
				}
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", role.getRoleId());
				jsonObject.put("name", role.getRoleName());
				jsonObject.put("havefun", havefun);
				jsonObject.put("mark", role.getRoleMark());
				jsonArray.put(jsonObject);
			}
			responseMsg= jsonArray.toString();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String doAddRole()
	{
		roleservice.addRole(name, "��");
		String[] ids=functionids.split(",");
		
		SolRole role=roleservice.findSolRoleByMaxId();
		for(int i=0;i<ids.length;i++)
		{
			SolFunction function=new SolFunction();
			function.setFunctionId(Integer.parseInt(ids[i]));
			functionroleservice.addFunctionRole(role, function, null);
		}
		responseMsg="1";
		return SUCCESS;
	}
	
	public String disabledRole()
	{
		roleservice.modifyRole(id, "��");
		responseMsg="1";
		return SUCCESS;
	}
	
	public String enabledRole()
	{
		roleservice.modifyRole(id, "��");
		responseMsg="1";
		return SUCCESS;
	}
	
	public String modifyRole()
	{
		String[] functions=functionids.split(",");
		//�޸Ľ�ɫ��
		SolRole role=roleservice.findSolRoleById(id);
		role.setRoleName(name);
		roleservice.saveRole(role);
		
		//�޸Ľ�ɫ���ܹ�ϵ��Ӧ��
		List<SolFunctionrole> functionRoleList=functionroleservice.findFunRoleListByRoleId(id);
		
		//�ж��Ƿ��Ѿ����ڹ�ϵ���ݣ��������
		for(int i=0;i<functions.length;i++)
		{
			boolean isSame=false;
			for(int j=0;j<functionRoleList.size();j++)
			{
				SolFunctionrole functionrole=functionRoleList.get(j);
				if(functionrole.getSolFunction().getFunctionId()==Integer.parseInt(functions[i]))
				{
					isSame=true;
				}
			}
			if(!isSame)
			{
				SolFunction function=new SolFunction();
				function.setFunctionId(Integer.parseInt(functions[i]));
				
				functionroleservice.addFunctionRole(role, function, null);
			}
		}
		
		//�ж��Ƿ���ڽ�Ҫɾ����ϵ���ݣ�����ɾ��
		for(int i=0;i<functionRoleList.size();i++)
		{
			SolFunctionrole functionrole=functionRoleList.get(i);
			boolean isSame=false;
			for(int j=0;j<functions.length;j++)
			{
				if(functionrole.getSolFunction().getFunctionId()==Integer.parseInt(functions[j]))
				{
					isSame=true;
				}
			}
			if(!isSame)
			{
				functionroleservice.deleteFunctionRole(functionrole);
			}
		}
		
		responseMsg="1";
		return SUCCESS;
	}
	
	public String roleListMark()
	{
		try {
			List<SolRole> rolelList=roleservice.roleList("��");
			JSONArray jsonArray=new JSONArray();
			for(int i=0;i<rolelList.size();i++)
			{
				SolRole role=rolelList.get(i);
				JSONObject jsonObject=new JSONObject();
				jsonObject.put("id", role.getRoleId());
				jsonObject.put("name", role.getRoleName());
				jsonArray.put(jsonObject);
			}
			responseMsg= jsonArray.toString();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
}
