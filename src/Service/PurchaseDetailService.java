package Service;

import java.util.List;

import Entity.SolGoods;
import Entity.SolPurchase;
import Entity.SolPurchasedetail;

public interface PurchaseDetailService {
	public void addPurchaseDetail(SolGoods goods,SolPurchase purchase,int quantity,String cost);
	public List<SolPurchasedetail> purchaseDetailList(int pId);
}
