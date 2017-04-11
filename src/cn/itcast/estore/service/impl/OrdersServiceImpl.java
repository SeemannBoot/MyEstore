package cn.itcast.estore.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.itcast.estore.dao.CartDAO;
import cn.itcast.estore.dao.GoodsDAO;
import cn.itcast.estore.dao.OrdersDAO;
import cn.itcast.estore.dao.impl.CartDAOImpl;
import cn.itcast.estore.dao.impl.GoodsDAOImpl;
import cn.itcast.estore.dao.impl.OrdersDAOImpl;
import cn.itcast.estore.domain.Goods;
import cn.itcast.estore.domain.Orderitems;
import cn.itcast.estore.domain.Orders;
import cn.itcast.estore.service.OrdersService;
import cn.itcast.estore.utils.DBUtils;

public class OrdersServiceImpl implements OrdersService,Inter {

	@Override
	public void submitOrders(Orders orders, List<Orderitems> oilist) {
		/**
		 * 同时对多张表进行更新操作，需要使用事务缠绕
		 * 事务的特点：
		 * 1. 手动提交
		 * 2. 同一个连接对象来完成
		 * 3. 出错时：回滚事务
		 * 4. 提交事务
		 */
		Connection conn = null;
		try {
			conn = DBUtils.getConnection();
			//设置手动提交数据
			conn.setAutoCommit(false);
			//添加订单  订单详情
			OrdersDAO dao = new OrdersDAOImpl();
			dao.addOrders(conn,orders);
			dao.addOrderitems(conn,oilist);
			//清空购物车
			CartDAO cartDao = new CartDAOImpl();
			cartDao.clearCart(conn,orders.getUid());
		} catch (Exception e) {
			e.printStackTrace();
			if(conn != null){
				try {
					conn.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		} finally {
			if(conn != null){
				try {
					conn.commit();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<Orders> findAllOrders(int id) {
		OrdersDAO dao = new OrdersDAOImpl();
		return dao.findAllOrders(id);
	}

	@Override
	public Orders findOrdersById(String id) {
		//查询订单信息
		OrdersDAO dao = new OrdersDAOImpl();
		Orders orders = dao.findOrdersById(id);
		//查询订单明细信息
		List<Orderitems> oilist = dao.queryOrderitems(id);
		GoodsDAO goodsDao = new GoodsDAOImpl();
		for (Orderitems items : oilist) {
			//查询每条明细对应的商品信息
			Goods good = goodsDao.findById(items.getGid()+"");
			items.setGood(good);
		}
		orders.setList(oilist);
		return orders;
	}

	@Override
	public void cancelOrders(String oid, int uid) {
		// 删除订单明细  删除订单   绑定事务   连接解耦合
		
			OrdersDAO dao = new OrdersDAOImpl();
			// 删除订单明细  和  订单  注意顺序
			dao.delOrderitems(oid,uid);
			dao.delOrders(oid,uid);
		
	}

	@Override
	public void scanOrder() {
		// 1查询未支付的订单   2 修改12个小时之前订单状态为已过期id3
		OrdersDAO dao = new OrdersDAOImpl();
		List<Orders> olist = dao.findUnpayOrders();
		for (Orders orders : olist) {
			long createtime = orders.getCreatetime().getTime();
			long nowtime = System.currentTimeMillis();
			if(nowtime - createtime > 1000*60*60*12){
				dao.changeOrderStatus(orders.getId(),3);
			}
		}
		
		
	}

	@Override
	public void sayHi() {
		
	}

}
interface Inter{
	void sayHi();
}