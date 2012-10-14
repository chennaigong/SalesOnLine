package Service;

import java.util.List;

import Entity.SolGoods;
import Entity.SolInputgoods;
import Entity.SolInputgoodsdetail;

public interface InputGoodsDetailService {
	public void addInputGoodsDetail(SolGoods goods,SolInputgoods inputgoods,String warntime,String badtime,
			int quantity,int nowquantity,String cost);
	public List<SolInputgoodsdetail> inputGoodsDetailList(int iId);
	public List<SolInputgoodsdetail> inputGoodsDetailListByGoods(int goodsId);
	public SolInputgoodsdetail inputGoodsDetailListById(int id);
	public SolInputgoodsdetail updateInputGoodsDetail(int id,String warnTime,String badTime,int quantity);
}
