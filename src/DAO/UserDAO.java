package DAO;

import domain.User;

public interface UserDAO {
	
	public void adduser(User user) throws Exception;
	public boolean checkuser(String Unumber) throws Exception;
	public boolean login(User user) throws Exception;
	public User getUserMsg(String Unumber) throws Exception;
}
