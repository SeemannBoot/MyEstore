package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.domain.Cart;

public interface CartService {

	void addOrUpdate(String gid, String num, Integer uid);

	List<Cart> queryCart(int id);

	void changeBuynum(String gid, String buynum, Integer uid);

	void delCart(String gid, Integer uid);

}
