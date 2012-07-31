package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolUsers entity. @author MyEclipse Persistence Tools
 */

public class SolUsers implements java.io.Serializable {

	// Fields

	private String userUsername;
	private SolRole solRole;
	private String userPassword;
	private Set solShopusers = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolUsers() {
	}

	/** minimal constructor */
	public SolUsers(String userUsername) {
		this.userUsername = userUsername;
	}

	/** full constructor */
	public SolUsers(String userUsername, SolRole solRole, String userPassword,
			Set solShopusers) {
		this.userUsername = userUsername;
		this.solRole = solRole;
		this.userPassword = userPassword;
		this.solShopusers = solShopusers;
	}

	// Property accessors

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

	public SolRole getSolRole() {
		return this.solRole;
	}

	public void setSolRole(SolRole solRole) {
		this.solRole = solRole;
	}

	public String getUserPassword() {
		return this.userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Set getSolShopusers() {
		return this.solShopusers;
	}

	public void setSolShopusers(Set solShopusers) {
		this.solShopusers = solShopusers;
	}

}