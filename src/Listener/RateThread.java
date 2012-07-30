package Listener;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolRates;
import Entity.SolShop;
import Entity.SolUsers;
import Service.RateService;
import Service.ShopService;
import Service.UserService;
import Util.Helpers;
import Util.TaoBaoAPI;

public class RateThread extends Thread{
	
	private RateService rateService;
	private ShopService shopservice;
	private String defaultTime;
	private String threadInterval;
	
	public RateThread(RateService rateService,ShopService shopservice,String defaultTime,String threadInterval)
	{
		this.rateService=rateService;
		this.shopservice=shopservice;
		this.defaultTime=defaultTime;
		this.threadInterval=threadInterval;
	}
	
	public void run()
	{
		while(true)
		{
			
			try 
			{
				
				System.out.println("评价同步线程正在运行...");
				//查询全部授权的用户
				List<SolShop> shopList=shopservice.shopList();
				if(shopList.isEmpty())
				{
					System.out.println("还未有店铺授权。");
				}
				else
				{
					//查询每个用户的最后时间的订单，来设置访问API的开始时间
					for(int i=0;i<shopList.size();i++)
					{
						SolShop shop=shopList.get(i);
						SolRates rate=rateService.findLastRate(shop.getShopId(),"rateCreate");
						//默认设置用户如果无订单的情况下，开始时间为2012-01-01 00:00:00 
						String defaultTime=this.defaultTime;
						if(rate!=null)
						{
							defaultTime=rate.getRateCreate();
						}
						String nowTime=Helpers.nowTime();
						
						//每循环一次，获取两个月的数据
						while (Helpers.compareDate(defaultTime, nowTime)==-1) 
						{
							String endTime=Helpers.addTimeMonth(defaultTime, 2);
							if(Helpers.compareDate(endTime, nowTime)>=0)
							{
								endTime=nowTime;
							}
							String rateString=TaoBaoAPI.rateString(shop.getShopSessionkey(), defaultTime, endTime);
							String jsonCut=Helpers.strCut(rateString);
							if(jsonCut!=null)
							{
								//如果未授权，则实时修改SOL_users表的user_ispromise为"否"
								if(jsonCut.equals(Helpers.NOPERMIT))
								{
									shop.setShopIspromise("否");
									shopservice.modifyShop(shop);
								}
								//无数据，则只是实时修改SOL_users表的user_ispromise为"是"
								else if(jsonCut.equals(Helpers.NONEDATA))
								{
									shop.setShopIspromise("是");
									shopservice.modifyShop(shop);
								}
								//有数据则直接写入数据库，因为原先是无数据
								else 
								{
									shop.setShopIspromise("是");
									shopservice.modifyShop(shop);
									
									String data="{'result':"+jsonCut+"}";
									JSONObject jsonObject=new JSONObject(data);
									JSONArray jsonArray=jsonObject.getJSONArray("result");
									for(int j=0;j<jsonArray.length();j++)
									{
										
										String tid=null;
										String result=null;
										String created=null;
										String item_title=null;
										String item_price=null;
										String content=null;
										String reply=null;
										String num_iid=null;
										JSONObject sonjsonObj=jsonArray.getJSONObject(j);
										if(sonjsonObj.has("tid"))
										{
											tid=((Long)jsonArray.getJSONObject(j).get("tid")).toString();
										}
										if(sonjsonObj.has("result"))
										{
											result=jsonArray.getJSONObject(j).getString("result");
										}
										if(sonjsonObj.has("created"))
										{
											created=jsonArray.getJSONObject(j).getString("created");
										}
										if(sonjsonObj.has("item_title"))
										{
											item_title=jsonArray.getJSONObject(j).getString("item_title");
										}
										if(sonjsonObj.has("item_price"))
										{
											item_price=jsonArray.getJSONObject(j).getString("item_price");
										}
										if(sonjsonObj.has("content"))
										{
											content=jsonArray.getJSONObject(j).getString("content");
										}
										if(sonjsonObj.has("reply"))
										{
											reply=jsonArray.getJSONObject(j).getString("reply");
										}
										if(sonjsonObj.has("num_iid"))
										{
											num_iid=((Long)jsonArray.getJSONObject(j).get("num_iid")).toString();
										}
										
										if(rate==null)
										{
											rateService.addRate(tid,result,created,item_title,item_price,content,reply,num_iid);
										}
										else 
										{
											//获取数据库中存在的评价数据,主要查看tid是否相同
											List<SolRates> rateList=rateService.rateList(shop.getShopId());
											boolean isSame=false;
											for(int w=0;w<rateList.size();w++)
											{
												if(rateList.get(w).getSolTrades().getTradeId().equals(tid)&&rateList.get(w).getRateNumiid().equals(num_iid))
												{
													isSame=true;
												}
											}
											if(!isSame)
											{
												rateService.addRate(tid,result,created,item_title,item_price,content,reply,num_iid);
											}
										}
									}
								}
							}
							
							defaultTime=Helpers.addTimeMonth(defaultTime, 2);
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try {
				Thread.sleep(Integer.parseInt(this.threadInterval));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
