package Listener;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolTrades;
import Entity.SolUsers;
import Service.TradeService;
import Service.UserService;
import Util.Helpers;
import Util.TaoBaoAPI;


public class TradeThread extends Thread
{
	private TradeService tradeService;
	private UserService userService;
	
	public TradeThread(TradeService tradeService,UserService userService)
	{
		this.tradeService=tradeService;
		this.userService=userService;
	}
	
	public void run()
	{
		while (true) 
		{
			//��ȡ��������
			try 
			{
				System.out.println("����ͬ���߳���������...");
				//��ѯȫ����Ȩ���û�
				List<SolUsers> userList=userService.userList();
				if(userList.isEmpty())
				{
					System.out.println("��δ���û���Ȩ��");
				}
				else
				{
					//��ѯÿ���û������ʱ��Ķ����������÷���API�Ŀ�ʼʱ��
					for(int i=0;i<userList.size();i++)
					{
						SolUsers user=userList.get(i);
						SolTrades trade=tradeService.findLastTrade(user.getUserUsername(),"tradeCreate");
						//Ĭ�������û�����޶���������£���ʼʱ��Ϊ2012-01-01 00:00:00 
						String defaultTime="2012-01-01 00:00:00";
						if(trade!=null)
						{
							defaultTime=trade.getTradeCreate();
						}
						
						String nowTime=Helpers.nowTime();
						
						//ÿѭ��һ�Σ���ȡ�����µ�����
						while (Helpers.compareDate(defaultTime, nowTime)==-1) 
						{
							String endTime=Helpers.addTimeMonth(defaultTime, 2);
							if(Helpers.compareDate(endTime, nowTime)>=0)
							{
								endTime=nowTime;
							}
							String tradeString=TaoBaoAPI.tradeString(user.getUserSessionkey(), defaultTime, endTime);
							String jsonCut=Helpers.strCut(tradeString);
							if(jsonCut!=null)
							{
								//���δ��Ȩ����ʵʱ�޸�SOL_users����user_ispromiseΪ"��"
								if(jsonCut.equals(Helpers.NOPERMIT))
								{
									user.setUserIspromise("��");
									userService.modifyUser(user);
								}
								//�����ݣ���ֻ��ʵʱ�޸�SOL_users����user_ispromiseΪ"��"
								else if(jsonCut.equals(Helpers.NONEDATA))
								{
									user.setUserIspromise("��");
									userService.modifyUser(user);
								}
								else 
								{
									user.setUserIspromise("��");
									userService.modifyUser(user);
									
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
											//��������ֱ��д�����ݿ⣬��Ϊԭ����������
											tradeService.addTrade(user.getUserUsername(), tid,status,buyer_nick,
													created,total_fee,pay_time,payment,created);
										}
										else 
										{
											//��ȡ���ݿ��д��ڵĶ�������,��Ҫ�鿴tid�Ƿ���ͬ
											List<SolTrades> tradeList=tradeService.tradeList(user.getUserUsername());
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
												tradeService.addTrade(user.getUserUsername(), tid,status,buyer_nick,
														created,total_fee,pay_time,payment,created);
											}
										}
									}
								}
							}
							
							defaultTime=Helpers.addTimeMonth(defaultTime, 2);
						}
						
						SolTrades modifyTrade=tradeService.findLastTrade(user.getUserUsername(),"tradeModified");
						//���ݿ��ж������ݣ�������������(ֻ�ܻ�ȡÿ���������)
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
								
								String modifiedTradeString=TaoBaoAPI.modifyTradeString(user.getUserSessionkey(), modifyInitTime, endTime);
								String jsonCut=Helpers.strCut(modifiedTradeString);
								if(jsonCut!=null)
								{
									//���δ��Ȩ����ʵʱ�޸�SOL_users����user_ispromiseΪ"��"
									if(jsonCut.equals(Helpers.NOPERMIT))
									{
										user.setUserIspromise("��");
										userService.modifyUser(user);
									}
									//�����ݣ���ֻ��ʵʱ�޸�SOL_users����user_ispromiseΪ"��"
									else if(jsonCut.equals(Helpers.NONEDATA))
									{
										user.setUserIspromise("��");
										userService.modifyUser(user);
									}
									//��������ֱ��д�����ݿ⣬��Ϊԭ����������
									else 
									{
										user.setUserIspromise("��");
										userService.modifyUser(user);
										
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
											tradeService.updateTrade(user.getUserUsername(),tid,status,buyer_nick,
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
				Thread.sleep(5000);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}