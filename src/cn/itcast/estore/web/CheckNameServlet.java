package cn.itcast.estore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.service.UserService;
import cn.itcast.estore.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
public class CheckNameServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取用户名
		String username = request.getParameter("username");
		//调用service层
		UserService service = new UserServiceImpl();
		boolean cunzai = service.checkName(username);
		//处理结果
		if(cunzai) {
			response.getWriter().write("1");
		} else {
			response.getWriter().write("0");
		}
	}

}
