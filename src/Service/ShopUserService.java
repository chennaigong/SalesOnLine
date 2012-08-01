package Service;

import java.util.List;

import Entity.SolShopuser;

public interface ShopUserService {
	public List<SolShopuser> findShopUserByUserId(int id);
	public void addShopUser(int userId,int shopId);
	public SolShopuser findShopUserByUIdSId(int userId,int shopId);
	public void deleteShopUser(int userId,int shopId);
}
