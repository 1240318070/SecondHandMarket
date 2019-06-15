package DAO.iml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.UserDAO;
import domain.User;
import utils.C3P0Util;

public class UserDAOiml implements UserDAO{

	Connection conn = null;
	PreparedStatement ps = null;
	@Override
	public void adduser(User user) throws Exception {
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("INSERT INTO users(Unumber,Upassword,Uhead,Unickname,sex) VALUES (?,?,?,?,?)");
			ps.setString(1, user.getUnumber());
			ps.setString(2, user.getUPassword());
			if (user.getUsex().equals("1")) {
				ps.setString(3, "images/man.jpg");
				ps.setString(5, "ƒ–");
			}
			else {
				ps.setString(3, "images/woman.jpg");
				ps.setString(5, "≈Æ");
			}
			ps.setString(4, user.getUnickname());
			int i = ps.executeUpdate();//≈–∂œ «∑Ò≤Â»Î≥…π¶
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("◊¢≤· ß∞‹");
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}

	@Override
	public boolean checkuser(String Unumber) throws Exception {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			ps =conn.prepareStatement("select * from users where Unumber= ?");
			ps.setString(1, Unumber);
			rs = ps.executeQuery();
			if (rs.next()) 
				return true;
			else 
				return false;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("≤È’“ ß∞‹");
		}finally {
			C3P0Util.release(conn, ps, rs);
		}
		
	}

	@Override
	public boolean login(User user) throws SQLException {

		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("select * from users where Unumber=? and Upassword=?");
			ps.setString(1, user.getUnumber());
			ps.setString(2, user.getUPassword());
			rs = ps.executeQuery();
			if (rs.next()) 
				return true;
			else 
				return false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("≤È’“ ß∞‹");
		}finally {
			C3P0Util.release(conn, ps, rs);
		}
	}

	@Override
	public User getUserMsg(String Unumber) throws Exception {
		User user = new User();
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("select Uhead,Unickname,sex from users where Unumber=?");
			ps.setString(1, Unumber);
			rs = ps.executeQuery();
			if (rs.next()) {
				user.setUhead(rs.getString(1));
				user.setUnickname(rs.getString(2));
				user.setUsex(rs.getString(3));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("≤È’“ ß∞‹");
		}finally {
			C3P0Util.release(conn, ps, rs);
		}
		return user;
	}
	
}
