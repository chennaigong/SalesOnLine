package Entity;

/**
 * SolUsers entity. @author MyEclipse Persistence Tools
 */

public class SolUsers implements java.io.Serializable {

	// Fields

	private String userUsername;
	private String userPassword;
	private String userCan;
	private String userPost;
	private String userRating;

	// Constructors

	/** default constructor */
	public SolUsers() {
	}

	/** minimal constructor */
	public SolUsers(String userUsername) {
		this.userUsername = userUsername;
	}

	/** full constructor */
	public SolUsers(String userUsername, String userPassword, String userCan,
			String userPost, String userRating) {
		this.userUsername = userUsername;
		this.userPassword = userPassword;
		this.userCan = userCan;
		this.userPost = userPost;
		this.userRating = userRating;
	}

	// Property accessors

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserCan() {
		return this.userCan;
	}

	public void setUserCan(String userCan) {
		this.userCan = userCan;
	}

	public String getUserPost() {
		return this.userPost;
	}

	public void setUserPost(String userPost) {
		this.userPost = userPost;
	}

	public String getUserRating() {
		return this.userRating;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}

}