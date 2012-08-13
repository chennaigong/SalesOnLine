package Entity;

/**
 * SolGoods entity. @author MyEclipse Persistence Tools
 */

public class SolGoods implements java.io.Serializable {

	// Fields

	private Integer goodsId;
	private SolGoodstype solGoodstype;
	private String goodsName;
	private String goodsMnemonic;
	private String goodsDepart;
	private String goodsFactory;
	private String goodsSellprice;
	private String goodsCostprice;
	private String goodsDurability;
	private String goodsAlarmdays;
	private String goodsRemark;
	private String goodsMark;

	// Constructors

	/** default constructor */
	public SolGoods() {
	}

	/** minimal constructor */
	public SolGoods(Integer goodsId) {
		this.goodsId = goodsId;
	}

	/** full constructor */
	public SolGoods(Integer goodsId, SolGoodstype solGoodstype,
			String goodsName, String goodsMnemonic, String goodsDepart,
			String goodsFactory, String goodsSellprice, String goodsCostprice,
			String goodsDurability, String goodsAlarmdays, String goodsRemark,
			String goodsMark) {
		this.goodsId = goodsId;
		this.solGoodstype = solGoodstype;
		this.goodsName = goodsName;
		this.goodsMnemonic = goodsMnemonic;
		this.goodsDepart = goodsDepart;
		this.goodsFactory = goodsFactory;
		this.goodsSellprice = goodsSellprice;
		this.goodsCostprice = goodsCostprice;
		this.goodsDurability = goodsDurability;
		this.goodsAlarmdays = goodsAlarmdays;
		this.goodsRemark = goodsRemark;
		this.goodsMark = goodsMark;
	}

	// Property accessors

	public Integer getGoodsId() {
		return this.goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public SolGoodstype getSolGoodstype() {
		return this.solGoodstype;
	}

	public void setSolGoodstype(SolGoodstype solGoodstype) {
		this.solGoodstype = solGoodstype;
	}

	public String getGoodsName() {
		return this.goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsMnemonic() {
		return this.goodsMnemonic;
	}

	public void setGoodsMnemonic(String goodsMnemonic) {
		this.goodsMnemonic = goodsMnemonic;
	}

	public String getGoodsDepart() {
		return this.goodsDepart;
	}

	public void setGoodsDepart(String goodsDepart) {
		this.goodsDepart = goodsDepart;
	}

	public String getGoodsFactory() {
		return this.goodsFactory;
	}

	public void setGoodsFactory(String goodsFactory) {
		this.goodsFactory = goodsFactory;
	}

	public String getGoodsSellprice() {
		return this.goodsSellprice;
	}

	public void setGoodsSellprice(String goodsSellprice) {
		this.goodsSellprice = goodsSellprice;
	}

	public String getGoodsCostprice() {
		return this.goodsCostprice;
	}

	public void setGoodsCostprice(String goodsCostprice) {
		this.goodsCostprice = goodsCostprice;
	}

	public String getGoodsDurability() {
		return this.goodsDurability;
	}

	public void setGoodsDurability(String goodsDurability) {
		this.goodsDurability = goodsDurability;
	}

	public String getGoodsAlarmdays() {
		return this.goodsAlarmdays;
	}

	public void setGoodsAlarmdays(String goodsAlarmdays) {
		this.goodsAlarmdays = goodsAlarmdays;
	}

	public String getGoodsRemark() {
		return this.goodsRemark;
	}

	public void setGoodsRemark(String goodsRemark) {
		this.goodsRemark = goodsRemark;
	}

	public String getGoodsMark() {
		return this.goodsMark;
	}

	public void setGoodsMark(String goodsMark) {
		this.goodsMark = goodsMark;
	}

}