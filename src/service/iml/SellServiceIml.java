package service.iml;

import java.util.List;

import DAO.SellDAO;
import DAO.iml.SellDAOiml;
import domain.Sell;
import service.SellService;

public class SellServiceIml implements SellService {
	SellDAO sellDAO = new SellDAOiml();
	@Override
	public void SellGoods(Sell goods) throws Exception {
		sellDAO.addGoods(goods);
	}
	@Override
	public List<Sell> getGoods() throws Exception {
		return sellDAO.getGoods();
	}
	@Override
	public List<Sell> getUserGoods(String Unumber) throws Exception {
		// TODO Auto-generated method stub
		return sellDAO.getUserGoods(Unumber);
	}
	@Override
	public void delGoods(String goodsID) throws Exception {
		sellDAO.delGoods(goodsID);
	}

}
