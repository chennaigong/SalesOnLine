package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolOutputgoods entity. @author MyEclipse Persistence Tools
 */

public class SolOutputgoods implements java.io.Serializable {

	// Fields

	private Integer outputgoodsId;
	private SolUsers solUsers;
	private String outputgoodsTime;
	private String outputgoodsTowhere;
	private String outputgoodsMark;
	private String outputgoodsStatue;
	private String printId;
	private Set solOutputgoodsdetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolOutputgoods() {
	}

	/** minimal constructor */
	public SolOutputgoods(Integer outputgoodsId) {
		this.outputgoodsId = outputgoodsId;
	}

	/** full constructor */
	public SolOutputgoods(Integer outputgoodsId, SolUsers solUsers,
			String outputgoodsTime, String outputgoodsTowhere,
			String outputgoodsMark, String outputgoodsStatue, String printId,
			Set solOutputgoodsdetails) {
		this.outputgoodsId = outputgoodsId;
		this.solUsers = solUsers;
		this.outputgoodsTime = outputgoodsTime;
		this.outputgoodsTowhere = outputgoodsTowhere;
		this.outputgoodsMark = outputgoodsMark;
		this.outputgoodsStatue = outputgoodsStatue;
		this.printId = printId;
		this.solOutputgoodsdetails = solOutputgoodsdetails;
	}

	// Property accessors

	public Integer getOutputgoodsId() {
		return this.outputgoodsId;
	}

	public void setOutputgoodsId(Integer outputgoodsId) {
		this.outputgoodsId = outputgoodsId;
	}

	public SolUsers getSolUsers() {
		return this.solUsers;
	}

	public void setSolUsers(SolUsers solUsers) {
		this.solUsers = solUsers;
	}

	public String getOutputgoodsTime() {
		return this.outputgoodsTime;
	}

	public void setOutputgoodsTime(String outputgoodsTime) {
		this.outputgoodsTime = outputgoodsTime;
	}

	public String getOutputgoodsTowhere() {
		return this.outputgoodsTowhere;
	}

	public void setOutputgoodsTowhere(String outputgoodsTowhere) {
		this.outputgoodsTowhere = outputgoodsTowhere;
	}

	public String getOutputgoodsMark() {
		return this.outputgoodsMark;
	}

	public void setOutputgoodsMark(String outputgoodsMark) {
		this.outputgoodsMark = outputgoodsMark;
	}

	public String getOutputgoodsStatue() {
		return this.outputgoodsStatue;
	}

	public void setOutputgoodsStatue(String outputgoodsStatue) {
		this.outputgoodsStatue = outputgoodsStatue;
	}

	public String getPrintId() {
		return this.printId;
	}

	public void setPrintId(String printId) {
		this.printId = printId;
	}

	public Set getSolOutputgoodsdetails() {
		return this.solOutputgoodsdetails;
	}

	public void setSolOutputgoodsdetails(Set solOutputgoodsdetails) {
		this.solOutputgoodsdetails = solOutputgoodsdetails;
	}

}