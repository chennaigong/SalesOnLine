package Service;

import java.util.List;

import Entity.SolInputgoods;
import Entity.SolUsers;

public interface InputGoodsService {
	public SolInputgoods addInputgoods(SolUsers users,String itime,String iremark,String statue,String iprint);
	public SolInputgoods updateInputgoods(int id,String itime,String iremark,String statue,String iprint);
	public List<SolInputgoods> inputGoodsList();
}
