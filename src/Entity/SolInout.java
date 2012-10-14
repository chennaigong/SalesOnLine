package Entity;

/**
 * SolInout entity. @author MyEclipse Persistence Tools
 */

public class SolInout implements java.io.Serializable {

	// Fields

	private Integer inoutId;
	private SolOutputgoodsdetail solOutputgoodsdetail;
	private SolInputgoodsdetail solInputgoodsdetail;
	private Integer outputgoodsQuantity;

	// Constructors

	/** default constructor */
	public SolInout() {
	}

	/** minimal constructor */
	public SolInout(Integer inoutId) {
		this.inoutId = inoutId;
	}

	/** full constructor */
	public SolInout(Integer inoutId, SolOutputgoodsdetail solOutputgoodsdetail,
			SolInputgoodsdetail solInputgoodsdetail, Integer outputgoodsQuantity) {
		this.inoutId = inoutId;
		this.solOutputgoodsdetail = solOutputgoodsdetail;
		this.solInputgoodsdetail = solInputgoodsdetail;
		this.outputgoodsQuantity = outputgoodsQuantity;
	}

	// Property accessors

	public Integer getInoutId() {
		return this.inoutId;
	}

	public void setInoutId(Integer inoutId) {
		this.inoutId = inoutId;
	}

	public SolOutputgoodsdetail getSolOutputgoodsdetail() {
		return this.solOutputgoodsdetail;
	}

	public void setSolOutputgoodsdetail(
			SolOutputgoodsdetail solOutputgoodsdetail) {
		this.solOutputgoodsdetail = solOutputgoodsdetail;
	}

	public SolInputgoodsdetail getSolInputgoodsdetail() {
		return this.solInputgoodsdetail;
	}

	public void setSolInputgoodsdetail(SolInputgoodsdetail solInputgoodsdetail) {
		this.solInputgoodsdetail = solInputgoodsdetail;
	}

	public Integer getOutputgoodsQuantity() {
		return this.outputgoodsQuantity;
	}

	public void setOutputgoodsQuantity(Integer outputgoodsQuantity) {
		this.outputgoodsQuantity = outputgoodsQuantity;
	}

}