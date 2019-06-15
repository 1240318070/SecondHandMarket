package DAO;

import java.util.List;

import domain.Sell;

public interface SellDAO {
	public void addGoods(Sell goods) throws Exception; 
	public List<Sell> getGoods() throws Exception;
	public List<Sell> getUserGoods(String Unumber) throws Exception;
	public void delGoods(String goodsID)throws Exception;
	public void delWBGoods(String goodsID) throws Exception;
}
