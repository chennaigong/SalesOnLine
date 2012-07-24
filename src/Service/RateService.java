package Service;

import java.util.List;

import Entity.SolRates;

public interface RateService {
	public List<SolRates> rateList();
	public List<SolRates> rateList(String username);
	public SolRates findLastRate(String username,String orderName);
	public void addRate(String tradeId,String... rateArgs);
}
