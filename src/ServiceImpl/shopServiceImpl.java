package ServiceImpl;

import java.util.List;

import Entity.SolShop;
import Entity.SolUsers;
import Service.ShopService;

public class shopServiceImpl extends BaseServiceImpl<SolShop> implements ShopService{

	@Override
	public List<SolShop> shopList() {
		return basedao.findAll(SolShop.class);
	}

	@Override
	public SolShop findSOLShop(int shopId) {
		List<SolShop> userList=basedao.findByPropertys(SolShop.class, new String[]{"shopId"},
				new Integer[]{shopId});
		if (userList.isEmpty()) {
			return null;
		}
		return userList.get(0);
	}

}
