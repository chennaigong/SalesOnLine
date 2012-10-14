package Entity;

/**
 * SolPurchasedetail entity. @author MyEclipse Persistence Tools
 */

public class SolPurchasedetail implements java.io.Serializable {

	// Fields

	private Integer purchasedetailId;
	private SolPurchase solPurchase;
	private SolGoods solGoods;
	private Integer purchasedetailQuantity;
	private String purchasedetailCost;

	// Constructors

	/** default constructor */
	public SolPurchasedetail() {
	}

	/** minimal constructor */
	public SolPurchasedetail(Integer purchasedetailId) {
		this.purchasedetailId = purchasedetailId;
	}

	/** full constructor */
	public SolPurchasedetail(Integer purchasedetailId, SolPurchase solPurchase,
			SolGoods solGoods, Integer purchasedetailQuantity,
			String purchasedetailCost) {
		this.purchasedetailId = purchasedetailId;
		this.solPurchase = solPurchase;
		this.solGoods = solGoods;
		this.purchasedetailQuantity = purchasedetailQuantity;
		this.purchasedetailCost = purchasedetailCost;
	}

	// Property accessors

	public Integer getPurchasedetailId() {
		return this.purchasedetailId;
	}

	public void setPurchasedetailId(Integer purchasedetailId) {
		this.purchasedetailId = purchasedetailId;
	}

	public SolPurchase getSolPurchase() {
		return this.solPurchase;
	}

	public void setSolPurchase(SolPurchase solPurchase) {
		this.solPurchase = solPurchase;
	}

	public SolGoods getSolGoods() {
		return this.solGoods;
	}

	public void setSolGoods(SolGoods solGoods) {
		this.solGoods = solGoods;
	}

	public Integer getPurchasedetailQuantity() {
		return this.purchasedetailQuantity;
	}

	public void setPurchasedetailQuantity(Integer purchasedetailQuantity) {
		this.purchasedetailQuantity = purchasedetailQuantity;
	}

	public String getPurchasedetailCost() {
		return this.purchasedetailCost;
	}

	public void setPurchasedetailCost(String purchasedetailCost) {
		this.purchasedetailCost = purchasedetailCost;
	}

}