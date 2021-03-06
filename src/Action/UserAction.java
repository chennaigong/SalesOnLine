package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolTrades;
import Entity.SolUsers;
import Service.UserService;

public class UserAction extends BaseAction {

	public String userList() {
		try {

			List<SolUsers> userList = userservice.userList();
			JSONArray jsonArray = new JSONArray();
			for (int i = 0; i < userList.size(); i++) {
				SolUsers user = userList.get(i);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("username", user.getUserUsername());
				jsonObject.put("password", user.getUserPassword());
				jsonObject.put("session", user.getUserSessionkey());
				jsonObject.put("ispromise", user.getUserIspromise());
				jsonArray.put(jsonObject);
			}
			responseMsg = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String register() {
		// TODO Auto-generated method stub
		if (userservice.findSolUser(username) == null) {
			SolUsers solUsers = new SolUsers();
			solUsers.setUserUsername(username);
			solUsers.setUserPassword(password);
			userservice.save(solUsers);
			responseMsg = "1";
		} else {
			responseMsg = "0";
		}
		return SUCCESS;
	}
}
