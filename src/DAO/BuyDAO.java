package DAO;

import java.util.List;

import domain.Buy;
import domain.Sell;

public interface BuyDAO {
	public boolean addBuy(Buy buy) throws Exception;
	public List<Sell> getWBGoods(String Unumber) throws Exception;
	public List<Sell> SelectGoods(String keyWord) throws Exception;
	
}
