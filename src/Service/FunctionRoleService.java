package Service;

import java.util.List;

import Entity.SolFunction;
import Entity.SolFunctionrole;
import Entity.SolRole;

public interface FunctionRoleService {
	public void addFunctionRole(SolRole role,SolFunction function,String mark);
	public List<SolFunctionrole> findFunRoleListByRoleId(int roleId);
	public void deleteFunctionRole(SolFunctionrole functionrole);
	public SolFunctionrole findFunctionRoleById(int id);
}
