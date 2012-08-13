package Service;

import java.util.List;

import Entity.SolGoodstype;

public interface GoodsTypeService {
	public List<SolGoodstype> goodsTypeList();
	public List<SolGoodstype> goodsTypeList(String mark);
	public List<SolGoodstype> goodsTypeList(int PId);
	public void modifyGoodsType(int id,String mark);
	public SolGoodstype findGoodstypeFather(int id);
	public SolGoodstype findGoodstypeById(int id);
	public void addSGoodsType(String name,String pId,String isLeaf,String mark);
}
