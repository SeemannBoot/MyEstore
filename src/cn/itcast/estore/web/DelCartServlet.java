package cn.itcast.estore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.CartService;
import cn.itcast.estore.service.impl.CartServiceImpl;

@SuppressWarnings("serial")
public class DelCartServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断登录
		User user = (User)request.getSession().getAttribute("loginUser");
		if(user == null){
			request.getRequestDispatcher("/login.jsp").forward(request, response);
			return;
		}
		//调用service 根据gid,uid删除购物记录
		String gid = request.getParameter("gid");
		Integer uid = user.getId();
		CartService service = new CartServiceImpl();
		service.delCart(gid,uid);
		//跳转到queryCartServlet
		request.getRequestDispatcher("/queryCartServlet").forward(request, response);
	}

}
