package cn.itcast.estore.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import logger.Log4jDemo;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cn.itcast.estore.dao.CartDAO;
import cn.itcast.estore.domain.Cart;
import cn.itcast.estore.utils.DBUtils;

public class CartDAOImpl implements CartDAO {
	// 创建日志记录器
	Logger logger = LogManager.getLogger(Log4jDemo.class);
	@Override
	public Cart isBuy(String gid, Integer uid) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from cart where gid=? and uid=?";
		try {
			return qr.query(sql, new BeanHandler<Cart>(Cart.class), gid, uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询商品是否购买过失败!");
		}
	}

	@Override
	public void add(String gid, String num, Integer uid) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into cart values(?,?,?)";
		try {
			qr.update(sql, uid , gid, num);
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error("商品添加购物车失败!...error");
			throw new RuntimeException("商品添加购物车失败!");
		}
		logger.info("商品添加购物车成功!...success");
	}

	@Override
	public void update(Cart cart) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "update cart set buynum=? where uid=? and gid=?";
		try {
			qr.update(sql, cart.getBuynum(), cart.getUid() , cart.getGid());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("更新购物车商品数量失败!");
		}
		
	}

	@Override
	public List<Cart> queryCart(int id) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from cart where uid=?";
		try {
			return qr.query(sql, new BeanListHandler<Cart>(Cart.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询购物车失败!");
		}
	}

	@Override
	public void delCart(String gid,Integer uid) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "delete from cart where uid=? and gid=?";
		try {
			qr.update(sql, uid, gid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("删除购物记录失败!");
		}
		
	}

	@Override
	public void clearCart(Connection conn, Integer uid) {
		QueryRunner qr = new QueryRunner();
		String sql = "delete from cart where uid=?";
		try {
			qr.update(conn, sql, uid);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("清空购物车失败!");
		}
	}

}
