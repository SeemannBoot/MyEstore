package cn.itcast.estore.domain;

public class Cart {
	private Integer uid;
	private Integer gid;
	private Integer buynum;
	private Goods goods;
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getGid() {
		return gid;
	}
	public void setGid(Integer gid) {
		this.gid = gid;
	}
	public Integer getBuynum() {
		return buynum;
	}
	public void setBuynum(Integer buynum) {
		this.buynum = buynum;
	}
	@Override
	public String toString() {
		return "Cart [uid=" + uid + ", gid=" + gid + ", buynum=" + buynum
				+ ", goods=" + goods + "]";
	}
	
	
}
