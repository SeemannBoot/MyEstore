package cn.itcast.estore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Orders;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.OrdersService;
import cn.itcast.estore.service.impl.OrdersServiceImpl;

@SuppressWarnings("serial")
public class QueryOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断是否登录
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user == null) {
			//没有登录  跳转到登录
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}
		//调用service查询所有订单  根据用户id
		OrdersService service = new OrdersServiceImpl();
		List<Orders> olist = service.findAllOrders(user.getId());
		//保存数据    跳转到orders_detail.jsp
		request.setAttribute("olist", olist);
		request.getRequestDispatcher("/orders.jsp").forward(request, response);
	}

}
