package Service;

import java.util.List;

import Entity.SolGoods;
import Entity.SolPurchase;

public interface PurchaseService {
	public SolPurchase addPurchase(String ptime,String pbussiness,String pprice,String pprint);
	public List<SolPurchase> purchaseList();
}
