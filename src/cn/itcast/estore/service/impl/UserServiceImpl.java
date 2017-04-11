package cn.itcast.estore.service.impl;

import cn.itcast.estore.dao.UserDAO;
import cn.itcast.estore.dao.impl.UserDAOImpl;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.UserService;
import cn.itcast.estore.utils.MD5Utils;

public class UserServiceImpl implements UserService {

	@Override
	public boolean checkName(String username) {
		//调用DAO 
		UserDAO dao = new UserDAOImpl();
		return dao.checkName(username);
	}

	@Override
	public int register(User user) {
		try {
			//调用DAO 
			UserDAO dao = new UserDAOImpl();
			boolean cunzai = dao.checkName(user.getUsername());
			if(cunzai) {
				return 2;
			} else {
				//密码加密
				String pwd = MD5Utils.str2MD5(user.getPassword());
				user.setPassword(pwd);
				dao.register(user);
				return 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return 3;
		}
	}

	@Override
	public User login(String username, String password) {
		password = MD5Utils.str2MD5(password);
		UserDAO dao = new UserDAOImpl();
		return dao.login(username,password);
	}

}
