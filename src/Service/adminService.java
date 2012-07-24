package Service;

import Entity.SolAdmin;

public interface adminService {
	public SolAdmin findSolAdmin(String username,String password);
}
