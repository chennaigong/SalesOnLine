package Entity;

/**
 * SolRates entity. @author MyEclipse Persistence Tools
 */

public class SolRates implements java.io.Serializable {

	// Fields

	private Integer rateId;
	private SolTrades solTrades;
	private String rateResult;
	private String rateCreate;
	private String rateItemtitle;
	private String rateItemprice;
	private String rateContent;
	private String rateReply;
	private String rateNumiid;

	// Constructors

	/** default constructor */
	public SolRates() {
	}

	/** minimal constructor */
	public SolRates(Integer rateId) {
		this.rateId = rateId;
	}

	/** full constructor */
	public SolRates(Integer rateId, SolTrades solTrades, String rateResult,
			String rateCreate, String rateItemtitle, String rateItemprice,
			String rateContent, String rateReply, String rateNumiid) {
		this.rateId = rateId;
		this.solTrades = solTrades;
		this.rateResult = rateResult;
		this.rateCreate = rateCreate;
		this.rateItemtitle = rateItemtitle;
		this.rateItemprice = rateItemprice;
		this.rateContent = rateContent;
		this.rateReply = rateReply;
		this.rateNumiid = rateNumiid;
	}

	// Property accessors

	public Integer getRateId() {
		return this.rateId;
	}

	public void setRateId(Integer rateId) {
		this.rateId = rateId;
	}

	public SolTrades getSolTrades() {
		return this.solTrades;
	}

	public void setSolTrades(SolTrades solTrades) {
		this.solTrades = solTrades;
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

}