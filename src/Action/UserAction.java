package Action;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

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
				jsonObject.put("post", user.getUserPost());
				jsonObject.put("rating", user.getUserRating());
				jsonArray.put(jsonObject);
			}
			responseMsg = jsonArray.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public String doRegister() {
		System.out.println("123");
		if (userservice.findSolUser(username) == null) {
			SolUsers solUsers = new SolUsers();
			solUsers.setUserUsername(username);
			solUsers.setUserPassword(password);
			solUsers.setUserPost("ÆÕÍ¨Ô±¹¤");
			solUsers.setUserRating("1");
			userservice.save(solUsers);
			responseMsg = "1";
		} else {
			responseMsg = "0";
		}
		return SUCCESS;
	}
}
