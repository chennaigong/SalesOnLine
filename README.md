SalesOnLine
1,	里面的发货action无论是用户点击发货还是管理员点击发货，都会被导向admin的action目录下，
	所以admin配置过滤器应该忽略这个action
2,	adaddshop.ftl页面里是授权时要跳转的页面，包括应用的sessionkey
3,	添加店铺先注销当前用户，然后在跳转adaddshop.ftl->adacquiresession.ftl

4,	ShopAction里的方法,备份
	//检查店铺是否授权
	public String examShop()
	{
		List<SolShop> shopList=shopservice.shopList();
		if(shopList.isEmpty())
		{
			responseMsg="1";
		}
		else 
		{
			String unAuthShop="";
			for(int i=0;i<shopList.size();i++)
			{
				SolShop shop=shopList.get(i);
				String sessionKey=shop.getShopSessionkey();
				String result=null;
				//限制由于网络问题，造成一直访问
				int count=0;
				while(result==null&&count<=8)
				{
					result=TaoBaoAPI.userInfoString(sessionKey);
					count++;
				}
				
				//如果网络问题，则默认为未授权
				if(result==null)
				{
					unAuthShop+=shop.getShopId();
				}
				else 
				{
					if(result.indexOf("user_id")==-1)
					{
						unAuthShop+=shop.getShopId();
						shop.setShopIspromise("否");
						shopservice.modifyShop(shop);
					}
				}
			}
			if(unAuthShop.equals(""))
			{
				responseMsg="1";
			}
			else 
			{
				responseMsg="0";
			}
		}
		return SUCCESS;
	}
	