package ServiceImpl;

import java.util.List;

import Entity.SolOutputgoodsdetail;
import Service.OutputGoodsDetailService;

public class OutputGoodsDetailServiceImpl extends BaseServiceImpl<SolOutputgoodsdetail> implements OutputGoodsDetailService{

	@Override
	public List<SolOutputgoodsdetail> outputGoodsDetailList(int id) {
		return basedao.findByProperty(SolOutputgoodsdetail.class, "solOutputgoods.outputgoodsId", id);
	}

}
