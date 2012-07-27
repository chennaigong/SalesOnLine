package Listener;


import java.util.List;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import Entity.SolWebconfig;
import Service.RateService;
import Service.TradeService;
import Service.UserService;
import Service.WebconfigService;

public class ApplicationListenerImpl implements ApplicationListener {

	private TradeService tradeservice;
	private UserService userservice;
	private RateService rateservice;
	private WebconfigService webconfigservice;

	public WebconfigService getWebconfigservice() {
		return webconfigservice;
	}

	public void setWebconfigservice(WebconfigService webconfigservice) {
		this.webconfigservice = webconfigservice;
	}

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
		
		//插入网站配置数据
		List<SolWebconfig> webconfigList=webconfigservice.webconfigList();
		if(webconfigList.isEmpty())
		{
			webconfigservice.addWebconfig("defaultTime","2012-01-01 00:00:00","threadInterval","5000");
		}
		webconfigList=webconfigservice.webconfigList();
		String defaultTime=webconfigList.get(0).getWebconfigValue();
		String threadInterval=webconfigList.get(1).getWebconfigValue();
		//订单线程
		TradeThread tradeThread=new TradeThread(tradeservice,userservice,defaultTime,threadInterval);
		tradeThread.start();
		//评价线程
		RateThread rateThread=new RateThread(rateservice,userservice,defaultTime,threadInterval);
		rateThread.start();
	}

}
