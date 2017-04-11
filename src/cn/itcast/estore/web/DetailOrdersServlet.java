package cn.itcast.estore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Orders;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.OrdersService;
import cn.itcast.estore.service.impl.OrdersServiceImpl;

@SuppressWarnings("serial")
public class DetailOrdersServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断是否登录
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user == null) {
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}
		//根据订单id查询订单信息和订单中商品的信息
		String oid = request.getParameter("id");
		OrdersService service = new OrdersServiceImpl();
		Orders orders = service.findOrdersById(oid);
		//保存数据并转发
		request.setAttribute("orders", orders);
		request.getRequestDispatcher("/orders_detail.jsp").forward(request, response);
	}

}
