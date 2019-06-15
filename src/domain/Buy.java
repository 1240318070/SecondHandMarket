package domain;

public class Buy {
	private String Unumber; 
	private int goodsID;
	private String Bconnect;
	public String getUnumber() {
		return Unumber;
	}
	public void setUnumber(String unumber) {
		Unumber = unumber;
	}
	public int getGoodsID() {
		return goodsID;
	}
	public void setGoodsID(int goodsID) {
		this.goodsID = goodsID;
	}
	public String getBconnect() {
		return Bconnect;
	}
	public void setBconnect(String bconnect) {
		Bconnect = bconnect;
	}
}
