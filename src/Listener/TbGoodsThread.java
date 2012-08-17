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
				System.out.println("店铺商品同步线程正在运行...");
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
						SolTbgoods tbGoods=tbGoodsService.findLastTbGoods(shop.getShopId(),"tbgoodsModifytime");
						//默认设置用户如果无商品的情况下，开始时间为2012-01-01 00:00:00 
						String defaultTime=this.defaultTime;
						if(tbGoods!=null)
						{
							defaultTime=tbGoods.getTbgoodsModifytime();
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
							
							String onSalesString=TaoBaoAPI.onSaleGoodsString(shop.getShopSessionkey(), defaultTime, endTime);
							String jsonCut=Helpers.strCut(onSalesString);
							deal(jsonCut, tbGoods, shop, "出售");
							
							String inventoryString=TaoBaoAPI.inventoryGoodsString(shop.getShopSessionkey(), defaultTime, endTime);
							String jsonCut1=Helpers.strCut(inventoryString);
							deal(jsonCut1, tbGoods, shop, "仓库");
							
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
			//如果未授权，则实时修改SOL_users表的user_ispromise为"否"
			if(jsonCut.equals(Helpers.NOPERMIT))
			{
//								shop.setShopIspromise("否");
//								shopservice.modifyShop(shop);
			}
			//无数据，则只是实时修改SOL_users表的user_ispromise为"是"
			else if(jsonCut.equals(Helpers.NONEDATA))
			{
//								shop.setShopIspromise("是");
//								shopservice.modifyShop(shop);
			}
			
			else 
			{
//								shop.setShopIspromise("是");
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
						//获取数据库中存在的评价数据,主要查看tid是否相同
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
