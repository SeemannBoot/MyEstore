package cn.itcast.estore.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.estore.dao.OrdersDAO;
import cn.itcast.estore.domain.Orderitems;
import cn.itcast.estore.domain.Orders;
import cn.itcast.estore.utils.DBUtils;

public class OrdersDAOImpl implements OrdersDAO {

	@Override
	public void addOrders(Connection conn, Orders orders) {
		//使用事务  不再使用连接池  保证连接的唯一性
		QueryRunner qr = new QueryRunner();
		String sql = "insert into orders values(?,?,?,?,?,?)";
		try {
			qr.update(conn, sql, orders.getId(),orders.getUid(),orders.getTotalprice(),orders.getAddress(),orders.getStatus(),orders.getCreatetime());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加订单失败!");
		}

	}

	@Override
	public void addOrderitems(Connection conn, List<Orderitems> oilist) {
		//使用事务  不再使用连接池  保证连接的唯一性
		QueryRunner qr = new QueryRunner();
		StringBuffer sql = new StringBuffer("insert into orderitems values");
		for (Orderitems oi : oilist) {
			sql.append("('").append(oi.getOid()).append("',");
			sql.append(oi.getGid()).append(",").append(oi.getBuynum()).append("),");
		}
		String truesql = sql.toString().substring(0, sql.length() - 1);
		try {
			qr.update(conn, truesql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加订单明细失败!");
		}

	}

	@Override
	public List<Orders> findAllOrders(int id) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from Orders where uid=?";
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询订单失败!");
		}
		
	}

	@Override
	public Orders findOrdersById(String id) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from Orders where id=?";
		try {
			return qr.query(sql, new BeanHandler<Orders>(Orders.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据订单id查询订单失败!");
		}
	}

	@Override
	public List<Orderitems> queryOrderitems(String id) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from orderitems where oid=?";
		try {
			return qr.query(sql, new BeanListHandler<Orderitems>(Orderitems.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("根据订单id查询订单明细失败!");
		}
	}

	@Override
	public void delOrderitems(String oid, int uid) {
		// 删除订单明细
		QueryRunner qr = new QueryRunner();
		String sql = "delete from Orderitems where oid=(select id from orders where uid=? and id=?)";
		try {
			qr.update(DBUtils.getConnection(), sql, uid,oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除订单明细失败!");
		}
	}

	@Override
	public void delOrders(String oid, int uid) {
		// 删除订单
		QueryRunner qr = new QueryRunner();
		String sql = "delete from orders where uid=? and id=?";
		try {
			qr.update(DBUtils.getConnection(), sql, uid,oid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除订单失败!");
		}
		
	}

	@Override
	public List<Orders> findUnpayOrders() {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from Orders where status=1";
		try {
			return qr.query(sql, new BeanListHandler<Orders>(Orders.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询未支付的订单失败!");
		}
	}

	@Override
	public void changeOrderStatus(String id, int i) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "update orders set status=? where id=?";
		try {
			qr.update(sql, 3, id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更改订单状态失败!");
		}
	}

}
