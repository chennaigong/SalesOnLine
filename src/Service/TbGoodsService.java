package Service;

import java.util.List;

import Entity.SolTbgoods;

public interface TbGoodsService {
	public SolTbgoods findLastTbGoods(int shopId,String orderName);
	public void addTbGoods(int goodsId,int shopId,String... tbGoodsArgs);
	public List<SolTbgoods> tbGoodsList(int shopId);
	public void updateTbGoods(SolTbgoods tbgoods);
	public List<SolTbgoods> tbGoodsList();
	public SolTbgoods findTbGoodsById(int id);
}
