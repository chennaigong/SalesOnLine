package Entity;

/**
 * SolTradeorder entity. @author MyEclipse Persistence Tools
 */

public class SolTradeorder implements java.io.Serializable {

	// Fields

	private Integer tradeorderId;
	private SolTrades solTrades;
	private String tradeorderOid;
	private String tradeorderNumid;
	private String tradeorderNum;

	// Constructors

	/** default constructor */
	public SolTradeorder() {
	}

	/** minimal constructor */
	public SolTradeorder(Integer tradeorderId) {
		this.tradeorderId = tradeorderId;
	}

	/** full constructor */
	public SolTradeorder(Integer tradeorderId, SolTrades solTrades,
			String tradeorderOid, String tradeorderNumid, String tradeorderNum) {
		this.tradeorderId = tradeorderId;
		this.solTrades = solTrades;
		this.tradeorderOid = tradeorderOid;
		this.tradeorderNumid = tradeorderNumid;
		this.tradeorderNum = tradeorderNum;
	}

	// Property accessors

	public Integer getTradeorderId() {
		return this.tradeorderId;
	}

	public void setTradeorderId(Integer tradeorderId) {
		this.tradeorderId = tradeorderId;
	}

	public SolTrades getSolTrades() {
		return this.solTrades;
	}

	public void setSolTrades(SolTrades solTrades) {
		this.solTrades = solTrades;
	}

	public String getTradeorderOid() {
		return this.tradeorderOid;
	}

	public void setTradeorderOid(String tradeorderOid) {
		this.tradeorderOid = tradeorderOid;
	}

	public String getTradeorderNumid() {
		return this.tradeorderNumid;
	}

	public void setTradeorderNumid(String tradeorderNumid) {
		this.tradeorderNumid = tradeorderNumid;
	}

	public String getTradeorderNum() {
		return this.tradeorderNum;
	}

	public void setTradeorderNum(String tradeorderNum) {
		this.tradeorderNum = tradeorderNum;
	}

}