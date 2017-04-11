package cn.itcast.estore.domain;

public class Orderitems {
	private String oid;
	private Integer gid;
	private Integer buynum;
	private Goods good;
	
	public Orderitems() {
		super();
	}
	public Orderitems(String oid, Integer gid, Integer buynum, Goods good) {
		super();
		this.oid = oid;
		this.gid = gid;
		this.buynum = buynum;
		this.good = good;
	}
	public Goods getGood() {
		return good;
	}
	public void setGood(Goods good) {
		this.good = good;
	}
	public String getOid() {
		return oid;
	}
	public void setOid(String oid) {
		this.oid = oid;
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
		return "Orderitems [oid=" + oid + ", gid=" + gid + ", buynum=" + buynum
				+ ", good=" + good + "]";
	}
	
}
