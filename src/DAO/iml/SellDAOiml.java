package DAO.iml;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DAO.SellDAO;
import domain.Sell;
import utils.C3P0Util;

public class SellDAOiml implements SellDAO {
	Connection conn = null;
	PreparedStatement ps = null;
	@Override
	public void addGoods(Sell goods) throws Exception {
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("INSERT INTO sell(Unumber,Sname,Sprice,Sclassify,Sdescribe,Spicture1,Spicture2,Spicture3,Spicture4) VALUES (?,?,?,?,?,?,?,?,?)");
			ps.setString(1, goods.getUnumber());
			ps.setString(2, goods.getSname());
			ps.setString(3, goods.getSprice());
			ps.setString(4, goods.getSclassify());
			ps.setString(5, goods.getSdescribe());
			ps.setString(6, goods.getSpicture1());
			ps.setString(7, goods.getSpicture2());
			ps.setString(8, goods.getSpicture3());
			ps.setString(9, goods.getSpicture4());
			int i = ps.executeUpdate();//判断是否插入成功
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			throw new RuntimeException("商品上传失败");
		}finally {
			C3P0Util.release(conn, ps, null);
		}
		
	}
	@Override
	public List<Sell> getGoods() throws Exception {
		List<Sell> list = new ArrayList<Sell>();
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("SELECT * FROM sell");
			rs = ps.executeQuery();
			while (rs.next()) {
				Sell goods = new Sell();
				goods.setGoodsID(rs.getString("goodsID"));;
				goods.setUnumber(rs.getString("Unumber")); 
				goods.setSname(rs.getString("Sname")); 
				goods.setSprice(rs.getString("Sprice")); 
				goods.setSclassify(rs.getString("Sclassify")); 
				goods.setSdescribe(rs.getString("Sdescribe")); 
				goods.setSpicture1(rs.getString("Spicture1")); 
				goods.setSpicture2(rs.getString("Spicture2")); 
				goods.setSpicture3(rs.getString("Spicture3")); 
				goods.setSpicture4(rs.getString("Spicture4")); 
				list.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0Util.release(conn, ps, rs);
		}
		return list;
		
	}
	@Override
	public List<Sell> getUserGoods(String Unumber) throws Exception {
		List<Sell> list = new ArrayList<Sell>();
		ResultSet rs = null;
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("SELECT * FROM sell where Unumber=?");
			ps.setString(1, Unumber);
			rs = ps.executeQuery();
			while (rs.next()) {
				Sell goods = new Sell();
				goods.setGoodsID(rs.getString("goodsID"));
				goods.setUnumber(rs.getString("Unumber"));
				goods.setSname(rs.getString("Sname")); 
				goods.setSprice(rs.getString("Sprice")); 
				goods.setSclassify(rs.getString("Sclassify")); 
				goods.setSdescribe(rs.getString("Sdescribe")); 
				goods.setSpicture1(rs.getString("Spicture1")); 
				goods.setSpicture2(rs.getString("Spicture2")); 
				goods.setSpicture3(rs.getString("Spicture3")); 
				goods.setSpicture4(rs.getString("Spicture4")); 
				list.add(goods);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0Util.release(conn, ps, rs);
		}
		return list;
	}
	@Override
	public void delGoods(String goodsID) throws Exception {
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("delete from sell where goodsID=?");
			ps.setString(1, goodsID);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0Util.release(conn, ps, null);
			delWBGoods(goodsID);
		}
		
	}

	@Override
	public void delWBGoods(String goodsID) throws Exception {
		try {
			conn = C3P0Util.getConnection();
			ps = conn.prepareStatement("delete from buy where goodsID=?");
			ps.setString(1, goodsID);
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			C3P0Util.release(conn, ps, null);
		}
	}
}
