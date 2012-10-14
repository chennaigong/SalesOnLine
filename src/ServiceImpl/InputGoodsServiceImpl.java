package ServiceImpl;

import java.util.List;

import Entity.SolGoods;
import Entity.SolInputgoods;
import Entity.SolUsers;
import Service.InputGoodsService;

public class InputGoodsServiceImpl extends BaseServiceImpl<SolInputgoods> implements InputGoodsService{

	@Override
	public SolInputgoods addInputgoods(SolUsers users, String itime,
			String iremark, String statue, String iprint) {
		SolInputgoods inputgoods=new SolInputgoods();
		inputgoods.setSolUsers(users);
		inputgoods.setInputgoodsTime(itime);
		inputgoods.setInputgoodsMark(iremark);
		inputgoods.setInputgoodsStatue(statue);
		inputgoods.setPrintId(iprint);
		basedao.save(inputgoods);
		return inputgoods;
	}

	@Override
	public List<SolInputgoods> inputGoodsList() {
		return basedao.findAll(SolInputgoods.class,"inputgoodsStatue","ASC");
	}

	@Override
	public SolInputgoods updateInputgoods(int id, String itime,
			String iremark, String statue, String iprint) {
		SolInputgoods inputgoods=basedao.findById(SolInputgoods.class, id);
		inputgoods.setInputgoodsTime(itime);
		inputgoods.setInputgoodsMark(iremark);
		inputgoods.setInputgoodsStatue(statue);
		inputgoods.setPrintId(iprint);
		basedao.saveOrUpdate(inputgoods);
		return inputgoods;

	}

	
	
}
