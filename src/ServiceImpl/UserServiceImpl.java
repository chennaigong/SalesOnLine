package ServiceImpl;

import java.util.List;


import Entity.SolAdmin;
import Entity.SolUsers;
import Service.UserService;

public class UserServiceImpl extends BaseServiceImpl<SolUsers> implements UserService {

	@Override
	public List<SolUsers> userList() {
		return basedao.findAll(SolUsers.class);
	}

	@Override
	public void modifyUser(SolUsers user) {
		basedao.saveOrUpdate(user);
	}

	@Override
	public SolUsers findSolUser(String username, String password) {
		List<SolUsers> userList=basedao.findByPropertys(SolUsers.class, new String[]{"userUsername","userPassword"},
				new String[]{username,password});
		if (userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}

	@Override
	public SolUsers findSolUser(String username) {
		List<SolUsers> userList=basedao.findByPropertys(SolUsers.class, new String[]{"userUsername"},
				new String[]{username});
		if (userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}
	

}
