package Service;

import java.util.List;

import Entity.SolRole;

public interface RoleService {
	public List<SolRole> roleList();
	public void addRole(String name,String mark);
	public SolRole findSolRoleByMaxId();
	public SolRole findSolRoleById(int id);
	public void modifyRole(int id,String mark);
	public void saveRole(SolRole role);
}
