package Entity;

/**
 * SolShopuser entity. @author MyEclipse Persistence Tools
 */

public class SolShopuser implements java.io.Serializable {

	// Fields

	private Integer shopuserId;
	private SolShop solShop;
	private SolUsers solUsers;
	private String shopuserMark;

	// Constructors

	/** default constructor */
	public SolShopuser() {
	}

	/** minimal constructor */
	public SolShopuser(Integer shopuserId) {
		this.shopuserId = shopuserId;
	}

	/** full constructor */
	public SolShopuser(Integer shopuserId, SolShop solShop, SolUsers solUsers,
			String shopuserMark) {
		this.shopuserId = shopuserId;
		this.solShop = solShop;
		this.solUsers = solUsers;
		this.shopuserMark = shopuserMark;
	}

	// Property accessors

	public Integer getShopuserId() {
		return this.shopuserId;
	}

	public void setShopuserId(Integer shopuserId) {
		this.shopuserId = shopuserId;
	}

	public SolShop getSolShop() {
		return this.solShop;
	}

	public void setSolShop(SolShop solShop) {
		this.solShop = solShop;
	}

	public SolUsers getSolUsers() {
		return this.solUsers;
	}

	public void setSolUsers(SolUsers solUsers) {
		this.solUsers = solUsers;
	}

	public String getShopuserMark() {
		return this.shopuserMark;
	}

	public void setShopuserMark(String shopuserMark) {
		this.shopuserMark = shopuserMark;
	}

}