package service;

import java.util.List;

import domain.Buy;
import domain.Sell;

public interface BuyService {
	/**
	 * 添加想买商品
	 * @param buy
	 * @return
	 * @throws Exception
	 */
	public boolean WantBuy(Buy buy) throws Exception;
	/**
	 * 获得想买商品
	 * @param Unumber
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getWBgoods(String Unumber) throws Exception;
	/**
	 * 搜索商品
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getSelectGoods(String keyword) throws Exception;
}
