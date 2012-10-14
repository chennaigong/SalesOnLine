package ServiceImpl;

import java.util.List;

import Entity.SolGoods;
import Entity.SolGoodstype;
import Service.GoodsService;

public class GoodsServiceImpl extends BaseServiceImpl<SolGoods> implements GoodsService{

	@Override
	public List<SolGoods> goodsListByType(int typeId) {
		return basedao.findByProperty(SolGoods.class, "solGoodstype.goodstypeId", typeId);
	}

	@Override
	public SolGoods goodsListById(int id) {
		return basedao.findById(SolGoods.class, id);
	}

	@Override
	public void modifyGoodsMark(int id, String mark) {
		SolGoods solGoods=goodsListById(id);
		solGoods.setGoodsMark(mark);
		basedao.saveOrUpdate(solGoods);
	}

	@Override
	public void addGoods(int id,int quantity,String... goodArgs) {
		
		SolGoodstype goodstype=new SolGoodstype();
		goodstype.setGoodstypeId(id);
		
		SolGoods goods=new SolGoods();
		goods.setSolGoodstype(goodstype);
		goods.setGoodsName(goodArgs[0]);
		goods.setGoodsMnemonic(goodArgs[1]);
		goods.setGoodsDepart(goodArgs[2]);
		goods.setGoodsFactory(goodArgs[3]);
		goods.setGoodsSellprice(goodArgs[4]);
		goods.setGoodsCostprice(goodArgs[5]);
		goods.setGoodsDurability(goodArgs[6]);
		goods.setGoodsAlarmdays(goodArgs[7]);
		goods.setGoodsRemark(goodArgs[8]);
		goods.setGoodsMark(goodArgs[9]);
		goods.setGoodsNowquantity(quantity);
		basedao.save(goods);
	}

	@Override
	public List<SolGoods> goodsListByType(int typeId, String mark) {
		return basedao.findByPropertys(SolGoods.class, new String[]{"solGoodstype.goodstypeId","goodsMark"}, new Object[]{new Integer(typeId),new String(mark)});
	}

	@Override
	public void modifyGoods(int goodsId,int nowQuantity) {
		SolGoods goods=basedao.findById(SolGoods.class, goodsId);
		goods.setGoodsNowquantity(goods.getGoodsNowquantity()+nowQuantity);
		basedao.saveOrUpdate(goods);
	}
	
	@Override
	public void modifyGoods(int goodsId,String cost) {
		SolGoods goods=basedao.findById(SolGoods.class, goodsId);
		goods.setGoodsCostprice(cost);
		basedao.saveOrUpdate(goods);
	}
	


}
