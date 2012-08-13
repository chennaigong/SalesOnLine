package Service;

import java.util.List;

import Entity.SolGoods;

public interface GoodsService {
	public List<SolGoods> goodsListByType(int typeId);
	public SolGoods goodsListById(int id);
	public void modifyGoodsMark(int id,String mark);
	public void addGoods(int id,String... goodArgs);
}
