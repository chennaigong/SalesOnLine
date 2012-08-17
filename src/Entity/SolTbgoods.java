package Entity;

/**
 * SolTbgoods entity. @author MyEclipse Persistence Tools
 */

public class SolTbgoods implements java.io.Serializable {

	// Fields

	private Integer tbgoodsId;
	private SolShop solShop;
	private SolGoods solGoods;
	private String tbgoodsNumid;
	private String tbgoodsTitle;
	private String tbgoodsPicurl;
	private String tbgoodsNum;
	private String tbgoodsListtime;
	private String tbgoodsDelisttime;
	private String tbgoodsModifytime;
	private String tbgoodsStatus;
	private String tbgoodsPrice;

	// Constructors

	/** default constructor */
	public SolTbgoods() {
	}

	/** minimal constructor */
	public SolTbgoods(Integer tbgoodsId) {
		this.tbgoodsId = tbgoodsId;
	}

	/** full constructor */
	public SolTbgoods(Integer tbgoodsId, SolShop solShop, SolGoods solGoods,
			String tbgoodsNumid, String tbgoodsTitle, String tbgoodsPicurl,
			String tbgoodsNum, String tbgoodsListtime,
			String tbgoodsDelisttime, String tbgoodsModifytime,
			String tbgoodsStatus, String tbgoodsPrice) {
		this.tbgoodsId = tbgoodsId;
		this.solShop = solShop;
		this.solGoods = solGoods;
		this.tbgoodsNumid = tbgoodsNumid;
		this.tbgoodsTitle = tbgoodsTitle;
		this.tbgoodsPicurl = tbgoodsPicurl;
		this.tbgoodsNum = tbgoodsNum;
		this.tbgoodsListtime = tbgoodsListtime;
		this.tbgoodsDelisttime = tbgoodsDelisttime;
		this.tbgoodsModifytime = tbgoodsModifytime;
		this.tbgoodsStatus = tbgoodsStatus;
		this.tbgoodsPrice = tbgoodsPrice;
	}

	// Property accessors

	public Integer getTbgoodsId() {
		return this.tbgoodsId;
	}

	public void setTbgoodsId(Integer tbgoodsId) {
		this.tbgoodsId = tbgoodsId;
	}

	public SolShop getSolShop() {
		return this.solShop;
	}

	public void setSolShop(SolShop solShop) {
		this.solShop = solShop;
	}

	public SolGoods getSolGoods() {
		return this.solGoods;
	}

	public void setSolGoods(SolGoods solGoods) {
		this.solGoods = solGoods;
	}

	public String getTbgoodsNumid() {
		return this.tbgoodsNumid;
	}

	public void setTbgoodsNumid(String tbgoodsNumid) {
		this.tbgoodsNumid = tbgoodsNumid;
	}

	public String getTbgoodsTitle() {
		return this.tbgoodsTitle;
	}

	public void setTbgoodsTitle(String tbgoodsTitle) {
		this.tbgoodsTitle = tbgoodsTitle;
	}

	public String getTbgoodsPicurl() {
		return this.tbgoodsPicurl;
	}

	public void setTbgoodsPicurl(String tbgoodsPicurl) {
		this.tbgoodsPicurl = tbgoodsPicurl;
	}

	public String getTbgoodsNum() {
		return this.tbgoodsNum;
	}

	public void setTbgoodsNum(String tbgoodsNum) {
		this.tbgoodsNum = tbgoodsNum;
	}

	public String getTbgoodsListtime() {
		return this.tbgoodsListtime;
	}

	public void setTbgoodsListtime(String tbgoodsListtime) {
		this.tbgoodsListtime = tbgoodsListtime;
	}

	public String getTbgoodsDelisttime() {
		return this.tbgoodsDelisttime;
	}

	public void setTbgoodsDelisttime(String tbgoodsDelisttime) {
		this.tbgoodsDelisttime = tbgoodsDelisttime;
	}

	public String getTbgoodsModifytime() {
		return this.tbgoodsModifytime;
	}

	public void setTbgoodsModifytime(String tbgoodsModifytime) {
		this.tbgoodsModifytime = tbgoodsModifytime;
	}

	public String getTbgoodsStatus() {
		return this.tbgoodsStatus;
	}

	public void setTbgoodsStatus(String tbgoodsStatus) {
		this.tbgoodsStatus = tbgoodsStatus;
	}

	public String getTbgoodsPrice() {
		return this.tbgoodsPrice;
	}

	public void setTbgoodsPrice(String tbgoodsPrice) {
		this.tbgoodsPrice = tbgoodsPrice;
	}

}