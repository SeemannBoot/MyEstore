package cn.itcast.estore.service;

import cn.itcast.estore.domain.User;

public interface UserService {

	boolean checkName(String username);

	int register(User user);

	User login(String username, String password);
}
