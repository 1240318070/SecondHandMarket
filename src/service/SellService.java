package service;

import java.util.List;

import domain.Sell;

public interface SellService {
	/**
	 * �û������Ʒ����
	 * @param goods
	 * @throws Exception
	 */
	public void SellGoods(Sell goods) throws Exception;
	/**
	 * ��ҳ��Ʒ����
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getGoods() throws Exception;
	/**
	 * ������ҳ��Ʒ����
	 * @param Unumber
	 * @return
	 * @throws Exception
	 */
	public List<Sell> getUserGoods(String Unumber) throws Exception;
	/**
	 * ɾ����Ʒ
	 * @param goodsID
	 * @throws Exception
	 */
	public void delGoods(String goodsID) throws Exception;
}
