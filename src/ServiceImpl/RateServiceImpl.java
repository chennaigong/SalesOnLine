package ServiceImpl;

import java.util.List;

import Entity.SolRates;
import Entity.SolTrades;
import Service.RateService;

public class RateServiceImpl extends BaseServiceImpl<SolRates> implements RateService {

	@Override
	public void addRate(String tradeId,String... rateArgs) {
		
		SolTrades trade=new SolTrades();
		trade.setTradeId(tradeId);
		
		SolRates rate=new SolRates();
		rate.setSolTrades(trade);
		rate.setRateResult(rateArgs[0]);
		rate.setRateCreate(rateArgs[1]);
		rate.setRateItemtitle(rateArgs[2]);
		rate.setRateItemprice(rateArgs[3]);
		rate.setRateContent(rateArgs[4]);
		rate.setRateReply(rateArgs[5]);
		rate.setRateNumiid(rateArgs[6]);
		basedao.save(rate);
	}
	
	@Override
	public List<SolRates> rateList() 
	{
		return basedao.findAll(SolRates.class);
	}
	
	@Override
	public SolRates findLastRate(int shopId,String orderName)
	{
		List<SolRates> solRateList=basedao.findByPage("select model from SolRates as model where model.solTrades.solShop.shopId="+shopId+" order by model."+orderName+" DESC", 0, 1);
		if(solRateList.isEmpty())
		{
			return null;
		}
		else 
		{
			return solRateList.get(0);
		}
		
	}

	@Override
	public List<SolRates> rateList(int shopId) {
		return basedao.findByProperty(SolRates.class, "solTrades.solShop.shopId", shopId);
	}
	

}
