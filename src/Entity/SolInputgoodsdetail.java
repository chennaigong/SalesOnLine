package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolInputgoodsdetail entity. @author MyEclipse Persistence Tools
 */

public class SolInputgoodsdetail implements java.io.Serializable {

	// Fields

	private Integer inputgoodsdetailId;
	private SolInputgoods solInputgoods;
	private SolGoods solGoods;
	private String inputgoodsdetailWarntime;
	private String inputgoodsdetailBadtime;
	private Integer inputgoodsdetailInquantity;
	private Integer inputgoodsdetailNowquantity;
	private String inputgoodsdetailCost;
	private Set solInouts = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolInputgoodsdetail() {
	}

	/** minimal constructor */
	public SolInputgoodsdetail(Integer inputgoodsdetailId) {
		this.inputgoodsdetailId = inputgoodsdetailId;
	}

	/** full constructor */
	public SolInputgoodsdetail(Integer inputgoodsdetailId,
			SolInputgoods solInputgoods, SolGoods solGoods,
			String inputgoodsdetailWarntime, String inputgoodsdetailBadtime,
			Integer inputgoodsdetailInquantity,
			Integer inputgoodsdetailNowquantity, String inputgoodsdetailCost,
			Set solInouts) {
		this.inputgoodsdetailId = inputgoodsdetailId;
		this.solInputgoods = solInputgoods;
		this.solGoods = solGoods;
		this.inputgoodsdetailWarntime = inputgoodsdetailWarntime;
		this.inputgoodsdetailBadtime = inputgoodsdetailBadtime;
		this.inputgoodsdetailInquantity = inputgoodsdetailInquantity;
		this.inputgoodsdetailNowquantity = inputgoodsdetailNowquantity;
		this.inputgoodsdetailCost = inputgoodsdetailCost;
		this.solInouts = solInouts;
	}

	// Property accessors

	public Integer getInputgoodsdetailId() {
		return this.inputgoodsdetailId;
	}

	public void setInputgoodsdetailId(Integer inputgoodsdetailId) {
		this.inputgoodsdetailId = inputgoodsdetailId;
	}

	public SolInputgoods getSolInputgoods() {
		return this.solInputgoods;
	}

	public void setSolInputgoods(SolInputgoods solInputgoods) {
		this.solInputgoods = solInputgoods;
	}

	public SolGoods getSolGoods() {
		return this.solGoods;
	}

	public void setSolGoods(SolGoods solGoods) {
		this.solGoods = solGoods;
	}

	public String getInputgoodsdetailWarntime() {
		return this.inputgoodsdetailWarntime;
	}

	public void setInputgoodsdetailWarntime(String inputgoodsdetailWarntime) {
		this.inputgoodsdetailWarntime = inputgoodsdetailWarntime;
	}

	public String getInputgoodsdetailBadtime() {
		return this.inputgoodsdetailBadtime;
	}

	public void setInputgoodsdetailBadtime(String inputgoodsdetailBadtime) {
		this.inputgoodsdetailBadtime = inputgoodsdetailBadtime;
	}

	public Integer getInputgoodsdetailInquantity() {
		return this.inputgoodsdetailInquantity;
	}

	public void setInputgoodsdetailInquantity(Integer inputgoodsdetailInquantity) {
		this.inputgoodsdetailInquantity = inputgoodsdetailInquantity;
	}

	public Integer getInputgoodsdetailNowquantity() {
		return this.inputgoodsdetailNowquantity;
	}

	public void setInputgoodsdetailNowquantity(
			Integer inputgoodsdetailNowquantity) {
		this.inputgoodsdetailNowquantity = inputgoodsdetailNowquantity;
	}

	public String getInputgoodsdetailCost() {
		return this.inputgoodsdetailCost;
	}

	public void setInputgoodsdetailCost(String inputgoodsdetailCost) {
		this.inputgoodsdetailCost = inputgoodsdetailCost;
	}

	public Set getSolInouts() {
		return this.solInouts;
	}

	public void setSolInouts(Set solInouts) {
		this.solInouts = solInouts;
	}

}