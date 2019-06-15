package service;

import java.util.List;

import domain.Buy;
import domain.Sell;

public interface BuyService {
	/**
	 * ���������Ʒ
	 * @param buy
	 * @return
	 * @throws Exception
	 */
	public boolean WantBuy(Buy buy) throws Exception;
	/**
	 * ���������Ʒ
	 * @param Unumber
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getWBgoods(String Unumber) throws Exception;
	/**
	 * ������Ʒ
	 * @param keyword
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getSelectGoods(String keyword) throws Exception;
}
