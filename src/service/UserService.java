package service;

import domain.User;

public interface UserService {
	/**
	 * 用户注册方法
	 * @param user
	 * @return 
	 * @throws Exception
	 */
	public void register(User user) throws Exception;
	/**
	 * 用户登录
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public boolean login(User user) throws Exception;
	/**
	 * 获取用户名、性别
	 * @param Unumber
	 * @return
	 * @throws Exception
	 */
	public User getUserMsg(String Unumber) throws Exception;
}
