package Listener;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import Entity.SolShop;
import Entity.SolTbgoods;
import Service.ShopService;
import Service.TbGoodsService;
import Util.Helpers;
import Util.TaoBaoAPI;

public class TbGoodsThread extends Thread{
	
	private TbGoodsService tbGoodsService;
	private ShopService shopservice;
	private String defaultTime;
	private String threadInterval;
	
	public TbGoodsThread(TbGoodsService tbGoodsService,ShopService shopservice,String defaultTime,String threadInterval)
	{
		this.tbGoodsService=tbGoodsService;
		this.shopservice=shopservice;
		this.defaultTime=defaultTime;
		this.threadInterval=threadInterval;
	}
	
	public void run()
	{
		while (true) 
		{
			try
			{
				System.out.println("������Ʒͬ���߳���������...");
				//��ѯȫ����Ȩ���û�
				List<SolShop> shopList=shopservice.shopList();
				if(shopList.isEmpty())
				{
					System.out.println("��δ�е�����Ȩ��");
				}
				else
				{
					//��ѯÿ���û������ʱ��Ķ����������÷���API�Ŀ�ʼʱ��
					for(int i=0;i<shopList.size();i++)
					{
						SolShop shop=shopList.get(i);
						SolTbgoods tbGoods=tbGoodsService.findLastTbGoods(shop.getShopId(),"tbgoodsModifytime");
						//Ĭ�������û��������Ʒ������£���ʼʱ��Ϊ2012-01-01 00:00:00 
						String defaultTime=this.defaultTime;
						if(tbGoods!=null)
						{
							defaultTime=tbGoods.getTbgoodsModifytime();
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
							
							String onSalesString=TaoBaoAPI.onSaleGoodsString(shop.getShopSessionkey(), defaultTime, endTime);
							String jsonCut=Helpers.strCut(onSalesString);
							deal(jsonCut, tbGoods, shop, "����");
							
							String inventoryString=TaoBaoAPI.inventoryGoodsString(shop.getShopSessionkey(), defaultTime, endTime);
							String jsonCut1=Helpers.strCut(inventoryString);
							deal(jsonCut1, tbGoods, shop, "�ֿ�");
							
							defaultTime=Helpers.addTimeMonth(defaultTime, 2);
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
	public void deal(String jsonCut,SolTbgoods tbGoods,SolShop shop,String statu) throws Exception
	{
		if(jsonCut!=null)
		{
			//���δ��Ȩ����ʵʱ�޸�SOL_users���user_ispromiseΪ"��"
			if(jsonCut.equals(Helpers.NOPERMIT))
			{
//								shop.setShopIspromise("��");
//								shopservice.modifyShop(shop);
			}
			//�����ݣ���ֻ��ʵʱ�޸�SOL_users���user_ispromiseΪ"��"
			else if(jsonCut.equals(Helpers.NONEDATA))
			{
//								shop.setShopIspromise("��");
//								shopservice.modifyShop(shop);
			}
			
			else 
			{
//								shop.setShopIspromise("��");
//								shopservice.modifyShop(shop);
				
				String data="{'result':"+jsonCut+"}";
				JSONObject jsonObject=new JSONObject(data);
				JSONArray jsonArray=jsonObject.getJSONArray("result");
				for(int j=0;j<jsonArray.length();j++)
				{
					
					String num_iid=null;
					String title=null;
					String pic_url=null;
					String num=null;
					String list_time=null;
					String delist_time=null;
					String modified=null;
					String price=null;
					JSONObject sonjsonObj=jsonArray.getJSONObject(j);
					if(sonjsonObj.has("num_iid"))
					{
						num_iid=((Long)jsonArray.getJSONObject(j).get("num_iid")).toString();
					}
					if(sonjsonObj.has("title"))
					{
						title=jsonArray.getJSONObject(j).getString("title");
					}
					if(sonjsonObj.has("pic_url"))
					{
						pic_url=jsonArray.getJSONObject(j).getString("pic_url");
					}
					if(sonjsonObj.has("num"))
					{
						num=String.valueOf(jsonArray.getJSONObject(j).get("num"));
					}
					if(sonjsonObj.has("list_time"))
					{
						list_time=jsonArray.getJSONObject(j).getString("list_time");
					}
					if(sonjsonObj.has("delist_time"))
					{
						delist_time=jsonArray.getJSONObject(j).getString("delist_time");
					}
					if(sonjsonObj.has("modified"))
					{
						modified=jsonArray.getJSONObject(j).getString("modified");
					}
					if(sonjsonObj.has("price"))
					{
						price=jsonArray.getJSONObject(j).getString("price");
					}
					
					if(tbGoods==null)
					{
						tbGoodsService.addTbGoods(-1, shop.getShopId(),num_iid,title,pic_url,num,list_time,list_time,modified,statu,price);
					}
					else 
					{
						//��ȡ���ݿ��д��ڵ���������,��Ҫ�鿴tid�Ƿ���ͬ
						List<SolTbgoods> tbGoodsList=tbGoodsService.tbGoodsList(shop.getShopId());
						for(int w=0;w<tbGoodsList.size();w++)
						{
							if(tbGoodsList.get(w).getTbgoodsNumid().equals(num_iid))
							{
								SolTbgoods sameSolTbgoods=tbGoodsList.get(w);
								sameSolTbgoods.setTbgoodsNumid(num_iid);
								sameSolTbgoods.setTbgoodsTitle(title);
								sameSolTbgoods.setTbgoodsPicurl(pic_url);
								sameSolTbgoods.setTbgoodsNum(num);
								sameSolTbgoods.setTbgoodsListtime(list_time);
								sameSolTbgoods.setTbgoodsDelisttime(delist_time);
								sameSolTbgoods.setTbgoodsModifytime(modified);
								sameSolTbgoods.setTbgoodsPrice(price);
								sameSolTbgoods.setTbgoodsStatus(statu);
								tbGoodsService.updateTbGoods(sameSolTbgoods);
							}
						}
					}
				}
			}
		}
	}
}
