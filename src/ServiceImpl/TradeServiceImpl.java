package ServiceImpl;

import java.util.List;

import Entity.SolShop;
import Entity.SolTrades;
import Entity.SolUsers;
import Service.TradeService;

public class TradeServiceImpl extends BaseServiceImpl<SolTrades> implements TradeService{
	
	@Override
	public void addTrade(int shopId,String... tradeArgs) {
		
		SolShop shop=new SolShop();
		shop.setShopId(shopId);
		
		SolTrades trade=new SolTrades();
		trade.setSolShop(shop);
		trade.setTradeId(tradeArgs[0]);
		trade.setTradeStatus(tradeArgs[1]);
		trade.setTradeBuyernick(tradeArgs[2]);
		trade.setTradeCreate(tradeArgs[3]);
		trade.setTradeTotalfee(tradeArgs[4]);
		trade.setTradePaytime(tradeArgs[5]);
		trade.setTradePayment(tradeArgs[6]);
		trade.setTradeModified(tradeArgs[7]);
		basedao.save(trade);
	}

	@Override
	public List<SolTrades> tradeList(int shopId) {
		return basedao.findByProperty(SolTrades.class, "solShop.shopId", shopId);
	}
	
	
	
	@Override
	public List<SolTrades> tradeList() {
		return basedao.findAll(SolTrades.class);
	}
	
	@Override
	public List<SolTrades> tradeList(String tradeStatus) {
		return basedao.findByProperty(SolTrades.class, "tradeStatus", tradeStatus);
	}
	
	@Override
	public List<SolTrades> tradeList(int shopId,String tradeStatus) {
		return basedao.findByPropertys(SolTrades.class, new String[]{"solShop.shopId","tradeStatus"}, 
				new Object[]{shopId,tradeStatus});
	}
	
	@Override
	public SolTrades findLastTrade(int shopId,String orderName)
	{
		List<SolTrades> solTradesList=basedao.findByPage("select model from SolTrades as model where model.solShop.shopId="+shopId+" order by model."+orderName+" DESC", 0, 1);
		if(solTradesList.isEmpty())
		{
			return null;
		}
		else 
		{
			return solTradesList.get(0);
		}
		
	}

	@Override
	public void updateTrade(int shopId, String... tradeArgs) 
	{
		SolShop shop=new SolShop();
		shop.setShopId(shopId);
		
		SolTrades trade=new SolTrades();
		trade.setSolShop(shop);
		
		trade.setTradeId(tradeArgs[0]);
		trade.setTradeStatus(tradeArgs[1]);
		trade.setTradeBuyernick(tradeArgs[2]);
		trade.setTradeCreate(tradeArgs[3]);
		trade.setTradeTotalfee(tradeArgs[4]);
		trade.setTradePaytime(tradeArgs[5]);
		trade.setTradePayment(tradeArgs[6]);
		trade.setTradeModified(tradeArgs[7]);
		basedao.saveOrUpdate(trade);
	}


}
