package ServiceImpl;

import java.util.List;

import Entity.SolOutputgoods;
import Entity.SolUsers;
import Service.OutputGoodsService;

public class OutputGoodsServiceImpl extends BaseServiceImpl<SolOutputgoods> implements OutputGoodsService{

	@Override
	public List<SolOutputgoods> outputGoodsList() {
		return basedao.findAll(SolOutputgoods.class, "outputgoodsStatue", "ASC");
	}

	
	

}
