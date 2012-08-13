package Service;

import java.util.List;


import Entity.SolShop;

public interface ShopService {
	public List<SolShop> shopList();
	public SolShop findSOLShop(int shopId);
	public void modifyShop(SolShop shop);
	public void addShop(String sessionkey,String name);
}
