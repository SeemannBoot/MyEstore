package cn.itcast.estore.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.estore.dao.UserDAO;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.utils.DBUtils;

public class UserDAOImpl implements UserDAO {

	@Override
	public boolean checkName(String username) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select count(*) from user where username=?";
		try {
			Long count = qr.query(sql, new ScalarHandler<Long>(), username);
			return count > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("ajax验证用户名重复失败!");
		}
	}

	@Override
	public int register(User user) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "insert into user values(null,?,?,?,null)";
		try {
			return qr.update(sql, user.getNickname(), user.getUsername(), user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("注册失败!");
		}
	}

	@Override
	public User login(String username, String password) {
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from user where username=? and password=?";
		try {
			return qr.query(sql, new BeanHandler<User>(User.class), username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("登录失败!");
		}
	}

}
