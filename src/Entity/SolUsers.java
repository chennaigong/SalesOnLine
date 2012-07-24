package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolUsers entity. @author MyEclipse Persistence Tools
 */

public class SolUsers implements java.io.Serializable {

	// Fields

	private String userUsername;
	private String userPassword;
	private String userSessionkey;
	private String userIspromise;
	private Set solTradeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolUsers() {
	}

	/** minimal constructor */
	public SolUsers(String userUsername) {
		this.userUsername = userUsername;
	}

	/** full constructor */
	public SolUsers(String userUsername, String userPassword,
			String userSessionkey, String userIspromise, Set solTradeses) {
		this.userUsername = userUsername;
		this.userPassword = userPassword;
		this.userSessionkey = userSessionkey;
		this.userIspromise = userIspromise;
		this.solTradeses = solTradeses;
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

	public String getUserSessionkey() {
		return this.userSessionkey;
	}

	public void setUserSessionkey(String userSessionkey) {
		this.userSessionkey = userSessionkey;
	}

	public String getUserIspromise() {
		return this.userIspromise;
	}

	public void setUserIspromise(String userIspromise) {
		this.userIspromise = userIspromise;
	}

	public Set getSolTradeses() {
		return this.solTradeses;
	}

	public void setSolTradeses(Set solTradeses) {
		this.solTradeses = solTradeses;
	}

}