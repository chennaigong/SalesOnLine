package Entity;

/**
 * SolAdmin entity. @author MyEclipse Persistence Tools
 */

public class SolAdmin implements java.io.Serializable {

	// Fields

	private Integer adminId;
	private String adminUsername;
	private String adminPassword;
	private String adminRole;

	// Constructors

	/** default constructor */
	public SolAdmin() {
	}

	/** minimal constructor */
	public SolAdmin(Integer adminId) {
		this.adminId = adminId;
	}

	/** full constructor */
	public SolAdmin(Integer adminId, String adminUsername,
			String adminPassword, String adminRole) {
		this.adminId = adminId;
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.adminRole = adminRole;
	}

	// Property accessors

	public Integer getAdminId() {
		return this.adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public String getAdminUsername() {
		return this.adminUsername;
	}

	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	public String getAdminPassword() {
		return this.adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	public String getAdminRole() {
		return this.adminRole;
	}

	public void setAdminRole(String adminRole) {
		this.adminRole = adminRole;
	}

}