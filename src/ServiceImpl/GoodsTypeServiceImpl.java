package ServiceImpl;

import java.util.List;

import Entity.SolGoodstype;
import Service.GoodsTypeService;

public class GoodsTypeServiceImpl extends BaseServiceImpl<SolGoodstype> implements GoodsTypeService{


	@Override
	public List<SolGoodstype> goodsTypeList() {
		return basedao.findAll(SolGoodstype.class);
	}
	
	@Override
	public List<SolGoodstype> goodsTypeList(String mark) {
		return basedao.findByProperty(SolGoodstype.class, "goodstypeMark", mark);
	}

	@Override
	public List<SolGoodstype> goodsTypeList(int PId) {
		return basedao.findByProperty(SolGoodstype.class, "goodstypeParent", String.valueOf(PId));
	}

	@Override
	public void modifyGoodsType(int id,String mark) {
		SolGoodstype goodstype=basedao.findById(SolGoodstype.class, id);
		goodstype.setGoodstypeMark(mark);
		basedao.save(goodstype);
	}

	@Override
	public SolGoodstype findGoodstypeFather(int id) {
		SolGoodstype goodstype=basedao.findById(SolGoodstype.class, id);
		SolGoodstype goodstypeF=basedao.findById(SolGoodstype.class, Integer.parseInt(goodstype.getGoodstypeParent()));
		return goodstypeF;
	}

	@Override
	public SolGoodstype findGoodstypeById(int id) {
		return basedao.findById(SolGoodstype.class, id);
	}

	
	@Override
	public void addSGoodsType(String name, String pId, String isLeaf, String mark) {
		SolGoodstype goodstype=new SolGoodstype();
		goodstype.setGoodstypeName(name);
		goodstype.setGoodstypeParent(pId);
		goodstype.setGoodstypeIsleaf(isLeaf);
		goodstype.setGoodstypeMark(mark);
		basedao.save(goodstype);
	}

	
}
