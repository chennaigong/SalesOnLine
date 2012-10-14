package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolInputgoods entity. @author MyEclipse Persistence Tools
 */

public class SolInputgoods implements java.io.Serializable {

	// Fields

	private Integer inputgoodsId;
	private SolUsers solUsers;
	private String inputgoodsTime;
	private String inputgoodsMark;
	private String inputgoodsStatue;
	private String printId;
	private Set solInputgoodsdetails = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolInputgoods() {
	}

	/** minimal constructor */
	public SolInputgoods(Integer inputgoodsId) {
		this.inputgoodsId = inputgoodsId;
	}

	/** full constructor */
	public SolInputgoods(Integer inputgoodsId, SolUsers solUsers,
			String inputgoodsTime, String inputgoodsMark,
			String inputgoodsStatue, String printId, Set solInputgoodsdetails) {
		this.inputgoodsId = inputgoodsId;
		this.solUsers = solUsers;
		this.inputgoodsTime = inputgoodsTime;
		this.inputgoodsMark = inputgoodsMark;
		this.inputgoodsStatue = inputgoodsStatue;
		this.printId = printId;
		this.solInputgoodsdetails = solInputgoodsdetails;
	}

	// Property accessors

	public Integer getInputgoodsId() {
		return this.inputgoodsId;
	}

	public void setInputgoodsId(Integer inputgoodsId) {
		this.inputgoodsId = inputgoodsId;
	}

	public SolUsers getSolUsers() {
		return this.solUsers;
	}

	public void setSolUsers(SolUsers solUsers) {
		this.solUsers = solUsers;
	}

	public String getInputgoodsTime() {
		return this.inputgoodsTime;
	}

	public void setInputgoodsTime(String inputgoodsTime) {
		this.inputgoodsTime = inputgoodsTime;
	}

	public String getInputgoodsMark() {
		return this.inputgoodsMark;
	}

	public void setInputgoodsMark(String inputgoodsMark) {
		this.inputgoodsMark = inputgoodsMark;
	}

	public String getInputgoodsStatue() {
		return this.inputgoodsStatue;
	}

	public void setInputgoodsStatue(String inputgoodsStatue) {
		this.inputgoodsStatue = inputgoodsStatue;
	}

	public String getPrintId() {
		return this.printId;
	}

	public void setPrintId(String printId) {
		this.printId = printId;
	}

	public Set getSolInputgoodsdetails() {
		return this.solInputgoodsdetails;
	}

	public void setSolInputgoodsdetails(Set solInputgoodsdetails) {
		this.solInputgoodsdetails = solInputgoodsdetails;
	}

}