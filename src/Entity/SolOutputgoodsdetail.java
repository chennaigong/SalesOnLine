package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolOutputgoodsdetail entity. @author MyEclipse Persistence Tools
 */

public class SolOutputgoodsdetail implements java.io.Serializable {

	// Fields

	private Integer outputgoodsdetailId;
	private SolOutputgoods solOutputgoods;
	private String outputgoodsdetailQuantity;
	private String outputgoodsdetailPrice;
	private String goodsId;
	private Set solInouts = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolOutputgoodsdetail() {
	}

	/** minimal constructor */
	public SolOutputgoodsdetail(Integer outputgoodsdetailId) {
		this.outputgoodsdetailId = outputgoodsdetailId;
	}

	/** full constructor */
	public SolOutputgoodsdetail(Integer outputgoodsdetailId,
			SolOutputgoods solOutputgoods, String outputgoodsdetailQuantity,
			String outputgoodsdetailPrice, String goodsId, Set solInouts) {
		this.outputgoodsdetailId = outputgoodsdetailId;
		this.solOutputgoods = solOutputgoods;
		this.outputgoodsdetailQuantity = outputgoodsdetailQuantity;
		this.outputgoodsdetailPrice = outputgoodsdetailPrice;
		this.goodsId = goodsId;
		this.solInouts = solInouts;
	}

	// Property accessors

	public Integer getOutputgoodsdetailId() {
		return this.outputgoodsdetailId;
	}

	public void setOutputgoodsdetailId(Integer outputgoodsdetailId) {
		this.outputgoodsdetailId = outputgoodsdetailId;
	}

	public SolOutputgoods getSolOutputgoods() {
		return this.solOutputgoods;
	}

	public void setSolOutputgoods(SolOutputgoods solOutputgoods) {
		this.solOutputgoods = solOutputgoods;
	}

	public String getOutputgoodsdetailQuantity() {
		return this.outputgoodsdetailQuantity;
	}

	public void setOutputgoodsdetailQuantity(String outputgoodsdetailQuantity) {
		this.outputgoodsdetailQuantity = outputgoodsdetailQuantity;
	}

	public String getOutputgoodsdetailPrice() {
		return this.outputgoodsdetailPrice;
	}

	public void setOutputgoodsdetailPrice(String outputgoodsdetailPrice) {
		this.outputgoodsdetailPrice = outputgoodsdetailPrice;
	}

	public String getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public Set getSolInouts() {
		return this.solInouts;
	}

	public void setSolInouts(Set solInouts) {
		this.solInouts = solInouts;
	}

}