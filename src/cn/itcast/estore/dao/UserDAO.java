package cn.itcast.estore.dao;

import cn.itcast.estore.domain.User;

public interface UserDAO {

	boolean checkName(String username);

	int register(User user);

	User login(String username, String password);

}
