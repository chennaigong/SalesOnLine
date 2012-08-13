package Entity;

import java.util.HashSet;
import java.util.Set;

/**
 * SolGoodstype entity. @author MyEclipse Persistence Tools
 */

public class SolGoodstype implements java.io.Serializable {

	// Fields

	private Integer goodstypeId;
	private String goodstypeName;
	private String goodstypeParent;
	private String goodstypeIsleaf;
	private String goodstypeMark;
	private Set solGoodses = new HashSet(0);

	// Constructors

	/** default constructor */
	public SolGoodstype() {
	}

	/** minimal constructor */
	public SolGoodstype(Integer goodstypeId) {
		this.goodstypeId = goodstypeId;
	}

	/** full constructor */
	public SolGoodstype(Integer goodstypeId, String goodstypeName,
			String goodstypeParent, String goodstypeIsleaf,
			String goodstypeMark, Set solGoodses) {
		this.goodstypeId = goodstypeId;
		this.goodstypeName = goodstypeName;
		this.goodstypeParent = goodstypeParent;
		this.goodstypeIsleaf = goodstypeIsleaf;
		this.goodstypeMark = goodstypeMark;
		this.solGoodses = solGoodses;
	}

	// Property accessors

	public Integer getGoodstypeId() {
		return this.goodstypeId;
	}

	public void setGoodstypeId(Integer goodstypeId) {
		this.goodstypeId = goodstypeId;
	}

	public String getGoodstypeName() {
		return this.goodstypeName;
	}

	public void setGoodstypeName(String goodstypeName) {
		this.goodstypeName = goodstypeName;
	}

	public String getGoodstypeParent() {
		return this.goodstypeParent;
	}

	public void setGoodstypeParent(String goodstypeParent) {
		this.goodstypeParent = goodstypeParent;
	}

	public String getGoodstypeIsleaf() {
		return this.goodstypeIsleaf;
	}

	public void setGoodstypeIsleaf(String goodstypeIsleaf) {
		this.goodstypeIsleaf = goodstypeIsleaf;
	}

	public String getGoodstypeMark() {
		return this.goodstypeMark;
	}

	public void setGoodstypeMark(String goodstypeMark) {
		this.goodstypeMark = goodstypeMark;
	}

	public Set getSolGoodses() {
		return this.solGoodses;
	}

	public void setSolGoodses(Set solGoodses) {
		this.solGoodses = solGoodses;
	}

}