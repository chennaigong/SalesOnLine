package ServiceImpl;

import java.util.List;

import Entity.SolGoods;
import Entity.SolPurchase;
import Entity.SolPurchasedetail;
import Service.PurchaseDetailService;

public class PurchaseDetailServiceImpl extends BaseServiceImpl<SolPurchasedetail> implements PurchaseDetailService{

	@Override
	public void addPurchaseDetail(SolGoods goods, SolPurchase purchase,
			int quantity, String cost) {
		SolPurchasedetail purchasedetail=new SolPurchasedetail();
		purchasedetail.setSolGoods(goods);
		purchasedetail.setSolPurchase(purchase);
		purchasedetail.setPurchasedetailQuantity(quantity);
		purchasedetail.setPurchasedetailCost(cost);
		basedao.save(purchasedetail);
	}

	@Override
	public List<SolPurchasedetail> purchaseDetailList(int id) {
		return basedao.findByProperty(SolPurchasedetail.class, "solPurchase.purchaseId", id);
	}

}
