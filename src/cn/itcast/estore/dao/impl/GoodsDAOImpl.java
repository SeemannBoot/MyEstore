package cn.itcast.estore.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.estore.dao.GoodsDAO;
import cn.itcast.estore.domain.Goods;
import cn.itcast.estore.utils.DBUtils;

public class GoodsDAOImpl implements GoodsDAO {

	@Override
	public List<Goods> findAll() {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from goods";
		try {
			return qr.query(sql, new BeanListHandler<Goods>(Goods.class));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询商品列表失败!");
		}
	}

	public Goods findById(String id) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from goods where id=?";
		try {
			return qr.query(sql, new BeanHandler<Goods>(Goods.class), id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询商品详情失败!");
		}
	}

	@Override
	public void addGood(Goods good) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into goods values(null,?,?,?,?,?,?,?)";
		try {
			qr.update(sql,good.getName(),good.getMarketprice(),good.getEstoreprice(),
					good.getCategory(),good.getNum(),good.getImgurl(),good.getDescription());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("添加商品失败!");
		}
	}

}
