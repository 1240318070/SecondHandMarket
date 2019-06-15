package service;

import domain.User;

public interface UserService {
	/**
	 * �û�ע�᷽��
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void register(User user) throws Exception;
	/**
	 * �û���¼
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean login(User user) throws Exception;
	/**
	 * ��ȡ�û������Ա�
	 * @param Unumber
	 * @return
	 * @throws Exception
	 */
	public User getUserMsg(String Unumber) throws Exception;
}
