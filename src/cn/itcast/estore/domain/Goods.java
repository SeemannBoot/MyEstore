package cn.itcast.estore.domain;

public class Goods {
	private Integer id;
	private String name;
	private Double marketprice;
	private Double estoreprice;
	private String category;
	private Integer num;
	private String imgurl;
	private String description;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getMarketprice() {
		return marketprice;
	}
	public void setMarketprice(Double marketprice) {
		this.marketprice = marketprice;
	}
	public Double getEstoreprice() {
		return estoreprice;
	}
	public void setEstoreprice(Double estoreprice) {
		this.estoreprice = estoreprice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", marketprice="
				+ marketprice + ", estoreprice=" + estoreprice + ", category="
				+ category + ", num=" + num + ", imgurl=" + imgurl
				+ ", description=" + description + "]";
	}
	
}
