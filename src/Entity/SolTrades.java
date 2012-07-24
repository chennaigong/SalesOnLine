package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolTrades entity. @author MyEclipse Persistence Tools
 */

public class SolTrades implements java.io.Serializable {

	// Fields

	private String tradeId;
	private SolUsers solUsers;
	private String tradeStatus;
	private String tradeBuyernick;
	private String tradeCreate;
	private String tradeTotalfee;
	private String tradePaytime;
	private String tradePayment;
	private String tradeModified;
	private Set solRateses = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolTrades() {
	}

	/** minimal constructor */
	public SolTrades(String tradeId) {
		this.tradeId = tradeId;
	}

	/** full constructor */
	public SolTrades(String tradeId, SolUsers solUsers, String tradeStatus,
			String tradeBuyernick, String tradeCreate, String tradeTotalfee,
			String tradePaytime, String tradePayment, String tradeModified,
			Set solRateses) {
		this.tradeId = tradeId;
		this.solUsers = solUsers;
		this.tradeStatus = tradeStatus;
		this.tradeBuyernick = tradeBuyernick;
		this.tradeCreate = tradeCreate;
		this.tradeTotalfee = tradeTotalfee;
		this.tradePaytime = tradePaytime;
		this.tradePayment = tradePayment;
		this.tradeModified = tradeModified;
		this.solRateses = solRateses;
	}

	// Property accessors

	public String getTradeId() {
		return this.tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}

	public SolUsers getSolUsers() {
		return this.solUsers;
	}

	public void setSolUsers(SolUsers solUsers) {
		this.solUsers = solUsers;
	}

	public String getTradeStatus() {
		return this.tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getTradeBuyernick() {
		return this.tradeBuyernick;
	}

	public void setTradeBuyernick(String tradeBuyernick) {
		this.tradeBuyernick = tradeBuyernick;
	}

	public String getTradeCreate() {
		return this.tradeCreate;
	}

	public void setTradeCreate(String tradeCreate) {
		this.tradeCreate = tradeCreate;
	}

	public String getTradeTotalfee() {
		return this.tradeTotalfee;
	}

	public void setTradeTotalfee(String tradeTotalfee) {
		this.tradeTotalfee = tradeTotalfee;
	}

	public String getTradePaytime() {
		return this.tradePaytime;
	}

	public void setTradePaytime(String tradePaytime) {
		this.tradePaytime = tradePaytime;
	}

	public String getTradePayment() {
		return this.tradePayment;
	}

	public void setTradePayment(String tradePayment) {
		this.tradePayment = tradePayment;
	}

	public String getTradeModified() {
		return this.tradeModified;
	}

	public void setTradeModified(String tradeModified) {
		this.tradeModified = tradeModified;
	}

	public Set getSolRateses() {
		return this.solRateses;
	}

	public void setSolRateses(Set solRateses) {
		this.solRateses = solRateses;
	}

}