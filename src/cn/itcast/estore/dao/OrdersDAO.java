package cn.itcast.estore.dao;

import java.sql.Connection;
import java.util.List;

import cn.itcast.estore.domain.Orderitems;
import cn.itcast.estore.domain.Orders;

public interface OrdersDAO {

	void addOrders(Connection conn, Orders orders);

	void addOrderitems(Connection conn, List<Orderitems> oilist);

	List<Orders> findAllOrders(int id);

	Orders findOrdersById(String id);

	List<Orderitems> queryOrderitems(String id);

	void delOrderitems(String oid, int uid);

	void delOrders(String oid, int uid);

	List<Orders> findUnpayOrders();

	void changeOrderStatus(String id, int i);

}
