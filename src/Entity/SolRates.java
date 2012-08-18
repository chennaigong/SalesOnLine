package Entity;

/**
 * SolRates entity. @author MyEclipse Persistence Tools
 */

public class SolRates implements java.io.Serializable {

	// Fields

	private Integer rateId;
	private String rateResult;
	private String rateCreate;
	private String rateItemtitle;
	private String rateItemprice;
	private String rateContent;
	private String rateReply;
	private String rateNumiid;
	private String tradeId;
	private Integer shopId;

	// Constructors

	/** default constructor */
	public SolRates() {
	}

	/** minimal constructor */
	public SolRates(Integer rateId) {
		this.rateId = rateId;
	}

	/** full constructor */
	public SolRates(Integer rateId, String rateResult, String rateCreate,
			String rateItemtitle, String rateItemprice, String rateContent,
			String rateReply, String rateNumiid, String tradeId, Integer shopId) {
		this.rateId = rateId;
		this.rateResult = rateResult;
		this.rateCreate = rateCreate;
		this.rateItemtitle = rateItemtitle;
		this.rateItemprice = rateItemprice;
		this.rateContent = rateContent;
		this.rateReply = rateReply;
		this.rateNumiid = rateNumiid;
		this.tradeId = tradeId;
		this.shopId = shopId;
	}

	// Property accessors

	public Integer getRateId() {
		return this.rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public String getRateResult() {
		return this.rateResult;
	}

	public void setRateResult(String rateResult) {
		this.rateResult = rateResult;
	}

	public String getRateCreate() {
		return this.rateCreate;
	}

	public void setRateCreate(String rateCreate) {
		this.rateCreate = rateCreate;
	}

	public String getRateItemtitle() {
		return this.rateItemtitle;
	}

	public void setRateItemtitle(String rateItemtitle) {
		this.rateItemtitle = rateItemtitle;
	}

	public String getRateItemprice() {
		return this.rateItemprice;
	}

	public void setRateItemprice(String rateItemprice) {
		this.rateItemprice = rateItemprice;
	}

	public String getRateContent() {
		return this.rateContent;
	}

	public void setRateContent(String rateContent) {
		this.rateContent = rateContent;
	}

	public String getRateReply() {
		return this.rateReply;
	}

	public void setRateReply(String rateReply) {
		this.rateReply = rateReply;
	}

	public String getRateNumiid() {
		return this.rateNumiid;
	}

	public void setRateNumiid(String rateNumiid) {
		this.rateNumiid = rateNumiid;
	}

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public Integer getShopId() {
		return this.shopId;
	}

	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}

}