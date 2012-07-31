package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolRole entity. @author MyEclipse Persistence Tools
 */

public class SolRole implements java.io.Serializable {

	// Fields

	private Integer roleId;
	private String roleName;
	private String roleMark;
	private Set solFunctionroles = new HashSet(0);
	private Set solUserses = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolRole() {
	}

	/** minimal constructor */
	public SolRole(Integer roleId) {
		this.roleId = roleId;
	}

	/** full constructor */
	public SolRole(Integer roleId, String roleName, String roleMark,
			Set solFunctionroles, Set solUserses) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.roleMark = roleMark;
		this.solFunctionroles = solFunctionroles;
		this.solUserses = solUserses;
	}

	// Property accessors

	public Integer getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleMark() {
		return this.roleMark;
	}

	public void setRoleMark(String roleMark) {
		this.roleMark = roleMark;
	}

	public Set getSolFunctionroles() {
		return this.solFunctionroles;
	}

	public void setSolFunctionroles(Set solFunctionroles) {
		this.solFunctionroles = solFunctionroles;
	}

	public Set getSolUserses() {
		return this.solUserses;
	}

	public void setSolUserses(Set solUserses) {
		this.solUserses = solUserses;
	}

}