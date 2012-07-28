package Service;

import java.util.List;

import Entity.SolTrades;


public interface TradeService {
	public void addTrade(int shopId,String... tradeArgs);
	public void updateTrade(int shopId,String... tradeArgs);
	public List<SolTrades> tradeList(int shopId);
	public SolTrades findLastTrade(int shopId,String orderName);
	public List<SolTrades> tradeList();
	public List<SolTrades> tradeList(String tradeStatus);
	public List<SolTrades> tradeList(int shopId,String tradeStatus);
}
