package Listener;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolShop;
import Entity.SolTrades;
import Entity.SolUsers;
import Service.ShopService;
import Service.TradeService;
import Service.UserService;
import Util.Helpers;
import Util.TaoBaoAPI;


public class TradeThread extends Thread
{
	private TradeService tradeService;
	private ShopService shopservice;
	private String defaultTime;
	private String threadInterval;
	
	public TradeThread(TradeService tradeService,ShopService shopservice,String defaultTime,String threadInterval)
	{
		this.tradeService=tradeService;
		this.shopservice=shopservice;
		this.defaultTime=defaultTime;
		this.threadInterval=threadInterval;
	}
	
	public void run()
	{
		while (true) 
		{
			//获取订单数据
			try 
			{
				System.out.println("订单同步线程正在运行...");
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
						SolTrades trade=tradeService.findLastTrade(shop.getShopId(),"tradeCreate");
						//默认设置用户如果无订单的情况下，开始时间为2012-01-01 00:00:00 
						String defaultTime=this.defaultTime;
						if(trade!=null)
						{
							defaultTime=trade.getTradeCreate();
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
							String tradeString=TaoBaoAPI.tradeString(shop.getShopSessionkey(), defaultTime, endTime);
							String jsonCut=Helpers.strCut(tradeString);
							if(jsonCut!=null)
							{
								//如果未授权，则实时修改SOL_users表的user_ispromise为"否"
								if(jsonCut.equals(Helpers.NOPERMIT))
								{
//									user.setUserIspromise("否");
//									userService.modifyUser(user);
								}
								//无数据，则只是实时修改SOL_users表的user_ispromise为"是"
								else if(jsonCut.equals(Helpers.NONEDATA))
								{
//									user.setUserIspromise("是");
//									userService.modifyUser(user);
								}
								else 
								{
//									user.setUserIspromise("是");
//									userService.modifyUser(user);
									
									String data="{'result':"+jsonCut+"}";
									JSONObject jsonObject=new JSONObject(data);
									JSONArray jsonArray=jsonObject.getJSONArray("result");
									for(int j=0;j<jsonArray.length();j++)
									{
										
										String tid=null;
										String status=null;
										String total_fee=null;
										String created=null;
										String pay_time=null;
										String buyer_nick=null;
										String payment=null;
										JSONObject sonjsonObj=jsonArray.getJSONObject(j);
										if(sonjsonObj.has("tid"))
										{
											tid=((Long)jsonArray.getJSONObject(j).get("tid")).toString();
										}
										if(sonjsonObj.has("status"))
										{
											status=jsonArray.getJSONObject(j).getString("status");
										}
										if(sonjsonObj.has("total_fee"))
										{
											total_fee=jsonArray.getJSONObject(j).getString("total_fee");
										}
										if(sonjsonObj.has("created"))
										{
											created=jsonArray.getJSONObject(j).getString("created");
										}
										if(sonjsonObj.has("pay_time"))
										{
											pay_time=jsonArray.getJSONObject(j).getString("pay_time");
										}
										if(sonjsonObj.has("buyer_nick"))
										{
											buyer_nick=jsonArray.getJSONObject(j).getString("buyer_nick");
										}
										if(sonjsonObj.has("payment"))
										{
											payment=jsonArray.getJSONObject(j).getString("payment");
										}
										if(trade==null)
										{
											//有数据则直接写入数据库，因为原先是无数据
											tradeService.addTrade(shop.getShopId(), tid,status,buyer_nick,
													created,total_fee,pay_time,payment,created);
										}
										else 
										{
											//获取数据库中存在的订单数据,主要查看tid是否相同
											List<SolTrades> tradeList=tradeService.tradeList(shop.getShopId());
											boolean isSame=false;
											for(int w=0;w<tradeList.size();w++)
											{
												if(tradeList.get(w).getTradeId().equals(tid))
												{
													isSame=true;
												}
											}
											if(!isSame)
											{
												tradeService.addTrade(shop.getShopId(), tid,status,buyer_nick,
														created,total_fee,pay_time,payment,created);
											}
										}
									}
								}
							}
							
							defaultTime=Helpers.addTimeMonth(defaultTime, 2);
						}
						
						SolTrades modifyTrade=tradeService.findLastTrade(shop.getShopId(),"tradeModified");
						//数据库有订单数据，则开启更新增量(只能获取每天更新数据)
						if(modifyTrade!=null)
						{
							String modifyInitTime=modifyTrade.getTradeModified();
							while (Helpers.compareDate(modifyInitTime, nowTime)==-1)
							{
								String endTime=Helpers.addTimeDay(modifyInitTime, 1);
								if(Helpers.compareDate(endTime, nowTime)>=0)
								{
									endTime=nowTime;
								}
								
								String modifiedTradeString=TaoBaoAPI.modifyTradeString(shop.getShopSessionkey(), modifyInitTime, endTime);
								String jsonCut=Helpers.strCut(modifiedTradeString);
								if(jsonCut!=null)
								{
									//如果未授权，则实时修改SOL_users表的user_ispromise为"否"
									if(jsonCut.equals(Helpers.NOPERMIT))
									{
//										user.setUserIspromise("否");
//										userService.modifyUser(user);
									}
									//无数据，则只是实时修改SOL_users表的user_ispromise为"是"
									else if(jsonCut.equals(Helpers.NONEDATA))
									{
//										user.setUserIspromise("是");
//										userService.modifyUser(user);
									}
									//有数据则直接写入数据库，因为原先是无数据
									else 
									{
//										user.setUserIspromise("是");
//										userService.modifyUser(user);
										
										String data="{'result':"+jsonCut+"}";
										JSONObject jsonObject=new JSONObject(data);
										JSONArray jsonArray=jsonObject.getJSONArray("result");
										for(int j=0;j<jsonArray.length();j++)
										{
											
											String tid=null;
											String status=null;
											String total_fee=null;
											String created=null;
											String pay_time=null;
											String buyer_nick=null;
											String payment=null;
											String modified=null;
											JSONObject sonjsonObj=jsonArray.getJSONObject(j);
											if(sonjsonObj.has("tid"))
											{
												tid=((Long)jsonArray.getJSONObject(j).get("tid")).toString();
											}
											if(sonjsonObj.has("status"))
											{
												status=jsonArray.getJSONObject(j).getString("status");
											}
											if(sonjsonObj.has("total_fee"))
											{
												total_fee=jsonArray.getJSONObject(j).getString("total_fee");
											}
											if(sonjsonObj.has("created"))
											{
												created=jsonArray.getJSONObject(j).getString("created");
											}
											if(sonjsonObj.has("pay_time"))
											{
												pay_time=jsonArray.getJSONObject(j).getString("pay_time");
											}
											if(sonjsonObj.has("buyer_nick"))
											{
												buyer_nick=jsonArray.getJSONObject(j).getString("buyer_nick");
											}
											if(sonjsonObj.has("payment"))
											{
												payment=jsonArray.getJSONObject(j).getString("payment");
											}
											if(sonjsonObj.has("modified"))
											{
												modified=jsonArray.getJSONObject(j).getString("modified");
											}
											tradeService.updateTrade(shop.getShopId(),tid,status,buyer_nick,
													created,total_fee,pay_time,payment,modified);
										}
									}
								}
								
								modifyInitTime=Helpers.addTimeDay(modifyInitTime, 1);
							}
						}
					}
				}
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			try 
			{
				Thread.sleep(Integer.parseInt(this.threadInterval));
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
