package ServiceImpl;

import java.util.List;

import Entity.SolTrades;
import Entity.SolUsers;
import Service.TradeService;

public class TradeServiceImpl extends BaseServiceImpl<SolTrades> implements TradeService{
	
	@Override
	public void addTrade(String username,String... tradeArgs) {
		
		SolUsers user=new SolUsers();
		user.setUserUsername(username);
		
		SolTrades trade=new SolTrades();
		trade.setSolUsers(user);
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
	public List<SolTrades> tradeList(String username) {
		return basedao.findByProperty(SolTrades.class, "solUsers.userUsername", username);
	}
	
	@Override
	public List<SolTrades> tradeList() {
		return basedao.findAll(SolTrades.class);
	}
	
	@Override
	public SolTrades findLastTrade(String username,String orderName)
	{
		List<SolTrades> solTradesList=basedao.findByPage("select model from SolTrades as model where model.solUsers.userUsername='"+username+"' order by model."+orderName+" DESC", 0, 1);
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
	public void updateTrade(String username, String... tradeArgs) 
	{
		SolUsers user=new SolUsers();
		user.setUserUsername(username);
		
		SolTrades trade=new SolTrades();
		trade.setSolUsers(user);
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

	@Override
	public List tradeBuyerList() {
		return basedao.findByDifferent(SolTrades.class, "tradeBuyernick");
	}
	

}
