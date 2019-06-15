package service.iml;

import DAO.UserDAO;
import DAO.iml.UserDAOiml;
import domain.User;
import service.UserService;

public class UserServiceIml implements UserService {
	
	UserDAO userDao = new UserDAOiml();
	
	@Override
	public void register(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.adduser(user);
	}

	@Override
	public boolean login(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.login(user);
	}

	@Override
	public User getUserMsg(String Unumber) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUserMsg(Unumber);
	}

}
