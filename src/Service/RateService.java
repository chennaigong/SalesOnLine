package Service;

import java.util.List;

import Entity.SolRates;

public interface RateService {
	public List<SolRates> rateList();
	public List<SolRates> rateList(int shopId);
	public SolRates findLastRate(int shopId,String orderName);
	public void addRate(String tradeId,String... rateArgs);
}
