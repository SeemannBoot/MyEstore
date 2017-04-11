package cn.itcast.estore.domain;

public class PCD {
	private Integer pid;
	private Integer id;
	private String name;
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
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
	@Override
	public String toString() {
		return "PCD [pid=" + pid + ", id=" + id + ", name=" + name + "]";
	}
	
}
