package cn.itcast.estore.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.UserService;
import cn.itcast.estore.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2调用service
		UserService service = new UserServiceImpl();
		User user = service.login(username,password);
		//3处理返回结果   成功 失败
		if(user == null) {
			request.setAttribute("msg", "用户名或密码错误!");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//把对象保存到session
		request.getSession().setAttribute("loginUser", user);
		//记住用户名
		String remember = request.getParameter("remember");
		if(remember != null){
			//设置编码  解决cookie不能保存中文的问题
			username = URLEncoder.encode(username, "utf-8");
			Cookie c = new Cookie("username", username);
			c.setPath("/");
			c.setMaxAge(60*60*24*5);
			response.addCookie(c);
		} else {
			Cookie c = new Cookie("username", "");
			c.setPath("/");
			c.setMaxAge(0);
			response.addCookie(c);
		}
		request.getRequestDispatcher("/index.jsp").forward(request, response);
		return;
		
	}

}
