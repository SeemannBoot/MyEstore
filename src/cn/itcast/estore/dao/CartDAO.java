package cn.itcast.estore.dao;

import java.sql.Connection;
import java.util.List;

import cn.itcast.estore.domain.Cart;

public interface CartDAO {

	Cart isBuy(String gid, Integer uid);

	void add(String gid, String num, Integer uid);

	void update(Cart cart);

	List<Cart> queryCart(int id);

	void delCart(String gid, Integer uid);

	void clearCart(Connection conn, Integer uid);

}
