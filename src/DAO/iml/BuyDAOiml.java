package DAO.iml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.BuyDAO;
import domain.Buy;
import domain.Sell;
import domain.User;
import utils.C3P0Util;

public class BuyDAOiml implements BuyDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	@SuppressWarnings("finally")
	@Override
	public boolean addBuy(Buy buy) throws Exception {
		int i = 0;
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("INSERT INTO buy(Unumber,goodsID,Bconnect) VALUES (?,?,?)");
			ps.setString(1, buy.getUnumber());
			ps.setInt(2, buy.getGoodsID());
			ps.setString(3, buy.getBconnect());
		 	i  = ps.executeUpdate();//�ж��Ƿ����ɹ�
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("��Ʒ�ϴ�ʧ��");
		}finally {
			C3P0Util.release(conn, ps, null);
			if (i != 0) {
				return true;
			}
			return false;
		}
	}
	@Override
	public List<Sell> getWBGoods(String Unumber) throws Exception {
		ResultSet rs = null;
		List<Sell> goods = new ArrayList<Sell>(); 
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("SELECT sell.Unumber,Sname,Sprice,Sdescribe,Spicture1,Spicture2,Spicture3,Spicture4,buy.goodsID FROM buy,sell WHERE(buy.goodsID = sell.goodsID)");
		 	rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getString("Unumber").equals(Unumber)) {
					Sell gd = new Sell();
					gd.setUnumber(rs.getString("Unumber"));
					gd.setSname(rs.getString("Sname"));
					gd.setSprice(rs.getString("sprice"));
					gd.setSdescribe(rs.getString("sdescribe"));
					gd.setSpicture1(rs.getString("Spicture1"));
					gd.setSpicture2(rs.getString("Spicture2"));
					gd.setSpicture3(rs.getString("Spicture3"));
					gd.setSpicture4(rs.getString("Spicture4"));
					gd.setGoodsID(rs.getString("goodsID"));
					goods.add(gd);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("商品添加失败");
		}finally {
			C3P0Util.release(conn, ps, rs);
			}
		return goods;
	}
	@Override
	public List<Sell> SelectGoods(String keyWord) throws Exception {
		ResultSet rs = null;
		List<Sell> goods = new ArrayList<Sell>(); 
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("SELECT * FROM sell WHERE Sname LIKE ? OR Sname LIKE ? OR Sname LIKE ? OR Sdescribe LIKE ? OR Sdescribe LIKE ? OR Sdescribe LIKE ?");
			ps.setString(1, keyWord + "%");
			ps.setString(2, "%" + keyWord);
			ps.setString(3, "%" + keyWord + "%");
			ps.setString(4, keyWord + "%");
			ps.setString(5, "%" + keyWord);
			ps.setString(6, "%" + keyWord + "%");
		 	rs = ps.executeQuery();
			while (rs.next()) {
					Sell gd = new Sell();
					gd.setUnumber(rs.getString("Unumber"));
					gd.setSname(rs.getString("Sname"));
					gd.setSprice(rs.getString("sprice"));
					gd.setSdescribe(rs.getString("sdescribe"));
					gd.setSpicture1(rs.getString("Spicture1"));
					gd.setSpicture2(rs.getString("Spicture2"));
					gd.setSpicture3(rs.getString("Spicture3"));
					gd.setSpicture4(rs.getString("Spicture4"));
					gd.setGoodsID(rs.getString("goodsID"));
					goods.add(gd);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("商品查找失败 ");
		}finally {
			C3P0Util.release(conn, ps, rs);
			}
		return goods;
	}
	

}
