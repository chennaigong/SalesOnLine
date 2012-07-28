package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolShop entity. @author MyEclipse Persistence Tools
 */

public class SolShop implements java.io.Serializable {

	// Fields

	private Integer shopId;
	private String shopSessionkey;
	private String shopIspromise;
	private Set solTradeses = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolShop() {
	}

	/** minimal constructor */
	public SolShop(Integer shopId) {
		this.shopId = shopId;
	}

	/** full constructor */
	public SolShop(Integer shopId, String shopSessionkey, String shopIspromise,
			Set solTradeses) {
		this.shopId = shopId;
		this.shopSessionkey = shopSessionkey;
		this.shopIspromise = shopIspromise;
		this.solTradeses = solTradeses;
	}

	// Property accessors

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

	public String getShopSessionkey() {
		return this.shopSessionkey;
	}

	public void setShopSessionkey(String shopSessionkey) {
		this.shopSessionkey = shopSessionkey;
	}

	public String getShopIspromise() {
		return this.shopIspromise;
	}

	public void setShopIspromise(String shopIspromise) {
		this.shopIspromise = shopIspromise;
	}

	public Set getSolTradeses() {
		return this.solTradeses;
	}

	public void setSolTradeses(Set solTradeses) {
		this.solTradeses = solTradeses;
	}

}