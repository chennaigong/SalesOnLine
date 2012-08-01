package ServiceImpl;

import java.util.List;

import Entity.SolShop;
import Entity.SolShopuser;
import Entity.SolUsers;
import Service.ShopUserService;

public class ShopUserServiceImpl extends BaseServiceImpl<SolShopuser> implements ShopUserService{

	@Override
	public List<SolShopuser> findShopUserByUserId(int id) {
		return basedao.findByProperty(SolShopuser.class, "solUsers.userId", id);
	}

	@Override
	public void addShopUser(int userId, int shopId) {
		SolUsers users=new SolUsers();
		users.setUserId(userId);
		
		SolShop shop=new SolShop();
		shop.setShopId(shopId);
		
		SolShopuser shopuser=new SolShopuser();
		shopuser.setSolShop(shop);
		shopuser.setSolUsers(users);
		
		basedao.save(shopuser);
	}

	@Override
	public SolShopuser findShopUserByUIdSId(int userId, int shopId) {
		return basedao.findByPropertys(SolShopuser.class, new String[]{"solShop.shopId","solUsers.userId"}, new Integer[]{shopId,userId}).get(0);
	}

	@Override
	public void deleteShopUser(int userId, int shopId) {
		basedao.delete(findShopUserByUIdSId(userId, shopId));
	}
	
	
	
	
	
}
