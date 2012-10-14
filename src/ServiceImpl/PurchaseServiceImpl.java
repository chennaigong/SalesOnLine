package ServiceImpl;

import java.util.List;

import Entity.SolGoods;
import Entity.SolPurchase;
import Service.PurchaseService;

public class PurchaseServiceImpl extends BaseServiceImpl<SolPurchase> implements PurchaseService{

	@Override
	public SolPurchase addPurchase(String ptime, String pbussiness,
			String pprice, String pprint) {
		SolPurchase purchase=new SolPurchase();
		purchase.setPrintId(pprint);
		purchase.setPurchaseName(pbussiness);
		purchase.setPurchaseTime(ptime);
		purchase.setPurchasePrice(pprice);
		basedao.save(purchase);
		return purchase;
	}

	@Override
	public List<SolPurchase> purchaseList() {
		return basedao.findAll(SolPurchase.class);
	}


}
