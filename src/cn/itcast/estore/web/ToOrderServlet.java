package cn.itcast.estore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Cart;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.CartService;
import cn.itcast.estore.service.impl.CartServiceImpl;

@SuppressWarnings("serial")
public class ToOrderServlet extends HttpServlet {

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
		//查询当前用户的购物记录
		Integer uid = user.getId();
		CartService service = new CartServiceImpl();
		List<Cart> clist = service.queryCart(uid);
		//保存数据    跳转到orders_submit.jsp
		request.setAttribute("clist", clist);
		request.getRequestDispatcher("/orders_submit.jsp").forward(request, response);
	}

}
