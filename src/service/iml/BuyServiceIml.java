package service.iml;

import java.util.List;

import DAO.BuyDAO;
import DAO.iml.BuyDAOiml;
import domain.Buy;
import domain.Sell;
import service.BuyService;

public class BuyServiceIml implements BuyService {
	
	BuyDAO buyDAO = new BuyDAOiml();
	@Override
	public boolean WantBuy(Buy buy) throws Exception {
		// TODO Auto-generated method stub
		return buyDAO.addBuy(buy);
	}
	@Override
	public List<Sell> getWBgoods(String Unumber) throws Exception {
		// TODO Auto-generated method stub
		return buyDAO.getWBGoods(Unumber);
	}
	@Override
	public List<Sell> getSelectGoods(String keyword) throws Exception {
		// TODO Auto-generated method stub
		return buyDAO.SelectGoods(keyword);
	}


}
