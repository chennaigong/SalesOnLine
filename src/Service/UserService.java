package Service;

import java.util.List;


import Entity.SolUsers;

public interface UserService<T> {
	public List<SolUsers> userList();
	public SolUsers findSolUser(String username, String password);
	public SolUsers findSolUser(String username);
	public void save(SolUsers users);
	public List<SolUsers> findUserByRoleId(int roleId);
}
