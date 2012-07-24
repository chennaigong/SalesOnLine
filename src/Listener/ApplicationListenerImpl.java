package Listener;


import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import Service.RateService;
import Service.TradeService;
import Service.UserService;

public class ApplicationListenerImpl implements ApplicationListener {

	private TradeService tradeservice;
	private UserService userservice;
	private RateService rateservice;


	public RateService getRateservice() {
		return rateservice;
	}

	public void setRateservice(RateService rateservice) {
		this.rateservice = rateservice;
	}

	public UserService getUserservice() {
		return userservice;
	}

	public void setUserservice(UserService userservice) {
		this.userservice = userservice;
	}

	public TradeService getTradeservice() {
		return tradeservice;
	}

	public void setTradeservice(TradeService tradeservice) {
		this.tradeservice = tradeservice;
	}

	@Override
	public void onApplicationEvent(ApplicationEvent arg0) {
		//¶©µ¥Ïß³Ì
//		TradeThread tradeThread=new TradeThread(tradeservice,userservice);
//		tradeThread.start();
//		
//		RateThread rateThread=new RateThread(rateservice,userservice);
//		rateThread.start();
	}

}
