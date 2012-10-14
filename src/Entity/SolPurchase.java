package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolPurchase entity. @author MyEclipse Persistence Tools
 */

public class SolPurchase implements java.io.Serializable {

	// Fields

	private Integer purchaseId;
	private String purchaseName;
	private String purchaseTime;
	private String purchasePrice;
	private String printId;
	private Set solPurchasedetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolPurchase() {
	}

	/** minimal constructor */
	public SolPurchase(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	/** full constructor */
	public SolPurchase(Integer purchaseId, String purchaseName,
			String purchaseTime, String purchasePrice, String printId,
			Set solPurchasedetails) {
		this.purchaseId = purchaseId;
		this.purchaseName = purchaseName;
		this.purchaseTime = purchaseTime;
		this.purchasePrice = purchasePrice;
		this.printId = printId;
		this.solPurchasedetails = solPurchasedetails;
	}

	// Property accessors

	public Integer getPurchaseId() {
		return this.purchaseId;
	}

	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseName() {
		return this.purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}

	public String getPurchaseTime() {
		return this.purchaseTime;
	}

	public void setPurchaseTime(String purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public String getPurchasePrice() {
		return this.purchasePrice;
	}

	public void setPurchasePrice(String purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public String getPrintId() {
		return this.printId;
	}

	public void setPrintId(String printId) {
		this.printId = printId;
	}

	public Set getSolPurchasedetails() {
		return this.solPurchasedetails;
	}

	public void setSolPurchasedetails(Set solPurchasedetails) {
		this.solPurchasedetails = solPurchasedetails;
	}

}