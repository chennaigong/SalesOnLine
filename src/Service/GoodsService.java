package Service;

import java.util.List;

import Entity.SolGoods;

public interface GoodsService {
	public List<SolGoods> goodsListByType(int typeId);
	public List<SolGoods> goodsListByType(int typeId,String mark);
	public SolGoods goodsListById(int id);
	public void modifyGoodsMark(int id,String mark);
	public void addGoods(int id,int quantity,String... goodArgs);
	public void modifyGoods(int goodsId,int nowQuantity);
	public void modifyGoods(int goodsId,String cost);
}
