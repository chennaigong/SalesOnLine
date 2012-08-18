package ServiceImpl;

import java.util.List;

import Entity.SolGoods;
import Entity.SolShop;
import Entity.SolTbgoods;
import Service.TbGoodsService;

public class TbGoodsServiceImpl extends BaseServiceImpl<SolTbgoods> implements TbGoodsService{
	
	@Override
	public SolTbgoods findLastTbGoods(int shopId,String orderName)
	{
		List<SolTbgoods> solTbGooddsList=basedao.findByPage("select model from SolTbgoods as model where model.solShop.shopId="+shopId+" order by model."+orderName+" DESC", 0, 1);
		if(solTbGooddsList.isEmpty())
		{
			return null;
		}
		else 
		{
			return solTbGooddsList.get(0);
		}
		
	}

	@Override
	public void addTbGoods(int goodsId, int shopId, String... tbGoodsArgs) {
		
		SolTbgoods tbGoods=new SolTbgoods();
		SolGoods goods=new SolGoods();
		if(goodsId!=-1)
		{
			tbGoods.setSolGoods(goods);
		}
		SolShop shop=new SolShop();
		shop.setShopId(shopId);
		tbGoods.setSolShop(shop);
		tbGoods.setTbgoodsNumid(tbGoodsArgs[0]);
		tbGoods.setTbgoodsTitle(tbGoodsArgs[1]);
		tbGoods.setTbgoodsPicurl(tbGoodsArgs[2]);
		tbGoods.setTbgoodsNum(tbGoodsArgs[3]);
		tbGoods.setTbgoodsListtime(tbGoodsArgs[4]);
		tbGoods.setTbgoodsDelisttime(tbGoodsArgs[5]);
		tbGoods.setTbgoodsModifytime(tbGoodsArgs[6]);
		tbGoods.setTbgoodsStatus(tbGoodsArgs[7]);
		tbGoods.setTbgoodsPrice(tbGoodsArgs[8]);
		
		basedao.save(tbGoods);
	}

	@Override
	public List<SolTbgoods> tbGoodsList(int shopId) {
		return basedao.findByProperty(SolTbgoods.class, "solShop.shopId", shopId);
	}

	@Override
	public void updateTbGoods(SolTbgoods tbgoods) {
		basedao.saveOrUpdate(tbgoods);
		
	}

	@Override
	public List<SolTbgoods> tbGoodsList() {
		return basedao.findAll(SolTbgoods.class);
	}

	@Override
	public SolTbgoods findTbGoodsById(int id) {
		return basedao.findById(SolTbgoods.class, id);
	}

	@Override
	public SolTbgoods findTbGoodsByNumId(String numId) {
		List<SolTbgoods> tbgoodList=basedao.findByProperty(SolTbgoods.class, "tbgoodsNumid", numId);
		return tbgoodList.isEmpty()?null:tbgoodList.get(0);
	}

	@Override
	public List<SolTbgoods> tbGoodsListByGoods(int goodsId) {
		return basedao.findByProperty(SolTbgoods.class, "solGoods.goodsId", goodsId);
	}

}
