package Service;

import java.util.List;

import Entity.SolTrades;


public interface TradeService {
	public void addTrade(String username,String... tradeArgs);
	public void updateTrade(String username,String... tradeArgs);
	public List<SolTrades> tradeList(String username);
	public SolTrades findLastTrade(String username,String orderName);
	public List<SolTrades> tradeList();
	public List tradeBuyerList();
}
