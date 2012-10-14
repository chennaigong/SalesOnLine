package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolUsers entity. @author MyEclipse Persistence Tools
 */

public class SolUsers implements java.io.Serializable {

	// Fields

	private Integer userId;
	private SolRole solRole;
	private String userUsername;
	private String userPassword;
	private Set solInputgoodses = new HashSet(0);
	private Set solOutputgoodses = new HashSet(0);
	private Set solShopusers = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolUsers() {
	}

	/** minimal constructor */
	public SolUsers(Integer userId) {
		this.userId = userId;
	}

	/** full constructor */
	public SolUsers(Integer userId, SolRole solRole, String userUsername,
			String userPassword, Set solInputgoodses, Set solOutputgoodses,
			Set solShopusers) {
		this.userId = userId;
		this.solRole = solRole;
		this.userUsername = userUsername;
		this.userPassword = userPassword;
		this.solInputgoodses = solInputgoodses;
		this.solOutputgoodses = solOutputgoodses;
		this.solShopusers = solShopusers;
	}

	// Property accessors

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public SolRole getSolRole() {
		return this.solRole;
	}

	public void setSolRole(SolRole solRole) {
		this.solRole = solRole;
	}

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

	public Set getSolInputgoodses() {
		return this.solInputgoodses;
	}

	public void setSolInputgoodses(Set solInputgoodses) {
		this.solInputgoodses = solInputgoodses;
	}

	public Set getSolOutputgoodses() {
		return this.solOutputgoodses;
	}

	public void setSolOutputgoodses(Set solOutputgoodses) {
		this.solOutputgoodses = solOutputgoodses;
	}

	public Set getSolShopusers() {
		return this.solShopusers;
	}

	public void setSolShopusers(Set solShopusers) {
		this.solShopusers = solShopusers;
	}

}