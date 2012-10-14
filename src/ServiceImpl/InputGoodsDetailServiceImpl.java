package ServiceImpl;

import java.util.List;

import Entity.SolGoods;
import Entity.SolInputgoods;
import Entity.SolInputgoodsdetail;
import Service.InputGoodsDetailService;

public class InputGoodsDetailServiceImpl extends BaseServiceImpl<SolInputgoodsdetail> implements InputGoodsDetailService{

	@Override
	public void addInputGoodsDetail(SolGoods goods, SolInputgoods inputgoods,
			String warntime, String badtime, int quantity,
			int nowquantity, String cost) {
		SolInputgoodsdetail inputgoodsdetail=new SolInputgoodsdetail();
		inputgoodsdetail.setSolGoods(goods);
		inputgoodsdetail.setSolInputgoods(inputgoods);
		inputgoodsdetail.setInputgoodsdetailWarntime(warntime);
		inputgoodsdetail.setInputgoodsdetailBadtime(badtime);
		inputgoodsdetail.setInputgoodsdetailInquantity(quantity);
		inputgoodsdetail.setInputgoodsdetailNowquantity(nowquantity);
		inputgoodsdetail.setInputgoodsdetailCost(cost);
		basedao.save(inputgoodsdetail);
	}

	@Override
	public List<SolInputgoodsdetail> inputGoodsDetailList(int id) {
		return basedao.findByProperty(SolInputgoodsdetail.class, "solInputgoods.inputgoodsId", id);
	}

	@Override
	public SolInputgoodsdetail updateInputGoodsDetail(int id, String warnTime,
			String badTime, int quantity) {
		SolInputgoodsdetail inputgoodsdetail=basedao.findById(SolInputgoodsdetail.class, id);
		inputgoodsdetail.setInputgoodsdetailWarntime(warnTime);
		inputgoodsdetail.setInputgoodsdetailBadtime(badTime);
		inputgoodsdetail.setInputgoodsdetailInquantity(quantity);
		inputgoodsdetail.setInputgoodsdetailNowquantity(quantity);
		basedao.saveOrUpdate(inputgoodsdetail);
		return null;
	}

	@Override
	public SolInputgoodsdetail inputGoodsDetailListById(int id) {
		return basedao.findById(SolInputgoodsdetail.class, id);
	}

	@Override
	public List<SolInputgoodsdetail> inputGoodsDetailListByGoods(int goodsId) {
		return basedao.findByProperty(SolInputgoodsdetail.class, "solGoods.goodsId", goodsId);
	}


}
