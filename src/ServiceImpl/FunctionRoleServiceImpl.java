package ServiceImpl;

import java.util.List;

import Entity.SolFunction;
import Entity.SolFunctionrole;
import Entity.SolRole;
import Service.FunctionRoleService;

public class FunctionRoleServiceImpl extends BaseServiceImpl<SolFunctionrole> implements FunctionRoleService{


	@Override
	public void addFunctionRole(SolRole role, SolFunction function, String mark) {
		SolFunctionrole functionrole=new SolFunctionrole();
		functionrole.setSolFunction(function);
		functionrole.setSolRole(role);
		functionrole.setFunctionroleMark(mark);
		basedao.save(functionrole);
	}

	@Override
	public List<SolFunctionrole> findFunRoleListByRoleId(int roleId) {
		return basedao.findByProperty(SolFunctionrole.class, "solRole.roleId", roleId);
	}

	@Override
	public void deleteFunctionRole(SolFunctionrole functionrole) {
		basedao.delete(functionrole);
	}

	@Override
	public SolFunctionrole findFunctionRoleById(int id) {
		
		return basedao.findById(SolFunctionrole.class, id);
	}

}
