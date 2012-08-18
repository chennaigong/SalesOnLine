package ServiceImpl;

import java.util.List;

import Entity.SolTradeorder;
import Entity.SolTrades;
import Service.TradeOrderService;

public class TradeOrderServiceImpl extends BaseServiceImpl<SolTradeorder> implements TradeOrderService{

	@Override
	public void addTradeOrder(String tradeId, String orderId, String numId,
			String num) {
		SolTradeorder tradeorder=new SolTradeorder();
		
		SolTrades trades=new SolTrades();
		trades.setTradeId(tradeId);
		
		tradeorder.setSolTrades(trades);
		tradeorder.setTradeorderOid(orderId);
		tradeorder.setTradeorderNumid(numId);
		tradeorder.setTradeorderNum(num);
		
		basedao.save(tradeorder);
	}

	@Override
	public List<SolTradeorder> tradeOrderList(String tid) {
		return basedao.findByProperty(SolTradeorder.class, "solTrades.tradeId", tid);
	}

}
