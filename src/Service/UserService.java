package Service;

import java.util.List;


import Entity.SolAdmin;
import Entity.SolUsers;

public interface UserService {
	public List<SolUsers> userList();
	public void modifyUser(SolUsers user);
	public SolUsers findSolUser(String username, String password);
	public SolUsers findSolUser(String username);
}
