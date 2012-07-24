package Listener;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolRates;
import Entity.SolTrades;
import Entity.SolUsers;
import Service.RateService;
import Service.UserService;
import Util.Helpers;
import Util.TaoBaoAPI;

public class RateThread extends Thread{
	
	private RateService rateService;
	private UserService userService;
	
	
	public RateThread(RateService rateService,UserService userService)
	{
		this.rateService=rateService;
		this.userService=userService;
	}
	
	public void run()
	{
		while(true)
		{
			
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
						SolRates rate=rateService.findLastRate(user.getUserUsername(),"rateCreate");
						//Ĭ�������û�����޶���������£���ʼʱ��Ϊ2012-01-01 00:00:00 
						String defaultTime="2012-01-01 00:00:00";
						if(rate!=null)
						{
							defaultTime=rate.getRateCreate();
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
							String rateString=TaoBaoAPI.rateString(user.getUserSessionkey(), defaultTime, endTime);
							String jsonCut=Helpers.strCut(rateString);
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
											//��ȡ���ݿ��д��ڵ���������,��Ҫ�鿴tid�Ƿ���ͬ
											List<SolRates> rateList=rateService.rateList(user.getUserUsername());
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
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}