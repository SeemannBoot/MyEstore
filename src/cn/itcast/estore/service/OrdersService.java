package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.domain.Orderitems;
import cn.itcast.estore.domain.Orders;

public interface OrdersService{

	void submitOrders(Orders orders, List<Orderitems> oilist);

	List<Orders> findAllOrders(int id);

	Orders findOrdersById(String id);

	void cancelOrders(String oid, int uid);

	void scanOrder();

}
