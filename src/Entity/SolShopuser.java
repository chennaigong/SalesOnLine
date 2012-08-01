package Entity;

/**
 * SolShopuser entity. @author MyEclipse Persistence Tools
 */

public class SolShopuser implements java.io.Serializable {

	// Fields

	private Integer shopuserId;
	private SolShop solShop;
	private SolUsers solUsers;

	// Constructors

	/** default constructor */
	public SolShopuser() {
	}

	/** minimal constructor */
	public SolShopuser(Integer shopuserId) {
		this.shopuserId = shopuserId;
	}

	/** full constructor */
	public SolShopuser(Integer shopuserId, SolShop solShop, SolUsers solUsers) {
		this.shopuserId = shopuserId;
		this.solShop = solShop;
		this.solUsers = solUsers;
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

}