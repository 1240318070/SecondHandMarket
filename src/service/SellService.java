package service;

import java.util.List;

import domain.Sell;

public interface SellService {
	/**
	 * 用户添加商品方法
	 * @param goods
	 * @throws Exception
	 */
	public void SellGoods(Sell goods) throws Exception;
	/**
	 * 首页商品加载
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getGoods() throws Exception;
	/**
	 * 个人主页商品加载
	 * @param Unumber
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getUserGoods(String Unumber) throws Exception;
	/**
	 * 删除商品
	 * @param goodsID
	 * @throws Exception
	 */
	public void delGoods(String goodsID) throws Exception;
}
