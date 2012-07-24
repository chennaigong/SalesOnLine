package ServiceImpl;

import java.util.List;

import Entity.SolAdmin;
import Service.adminService;

public class adminServiceImpl extends BaseServiceImpl<SolAdmin> implements adminService{

	@Override
	public SolAdmin findSolAdmin(String username, String password) {
		List<SolAdmin> adminList=basedao.findByPropertys(SolAdmin.class, new String[]{"adminUsername","adminPassword"},
				new String[]{username,password});
		if (adminList.isEmpty()) {
			return null;
		}
		return adminList.get(0);
	}

}
