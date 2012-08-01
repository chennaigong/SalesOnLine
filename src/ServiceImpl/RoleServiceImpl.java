package ServiceImpl;

import java.util.List;

import Entity.SolRole;
import Service.RoleService;

public class RoleServiceImpl extends BaseServiceImpl<SolRole> implements RoleService{

	@Override
	public List<SolRole> roleList() {
		return basedao.findAll(SolRole.class);
	}
	
	@Override
	public void addRole(String name,String mark)
	{
		SolRole role=new SolRole();
		role.setRoleName(name);
		role.setRoleMark(mark);
		basedao.save(role);
	}

	@Override
	public SolRole findSolRoleByMaxId() {
		
		List<SolRole> solroleList=basedao.findByPage("select model from SolRole as model order by model.roleId DESC", 0, 1);
		if(solroleList.isEmpty())
		{
			return null;
		}
		else 
		{
			return solroleList.get(0);
		}
	}

	@Override
	public void modifyRole(int id,String mark) {
		SolRole role=basedao.findById(SolRole.class, id);
		role.setRoleMark(mark);
		basedao.saveOrUpdate(role);
	}

	@Override
	public SolRole findSolRoleById(int id) {
		return basedao.findById(SolRole.class, id);
	}

	@Override
	public void saveRole(SolRole role) {
		basedao.saveOrUpdate(role);
	}
}
