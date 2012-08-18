package Service;

import java.util.List;

import Entity.SolTradeorder;

public interface TradeOrderService {
	public void addTradeOrder(String tradeId,String orderId,String numId,String num);
	public List<SolTradeorder> tradeOrderList(String tid);
}
