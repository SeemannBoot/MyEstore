package cn.itcast.estore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.OrdersService;
import cn.itcast.estore.service.OrdersServiceProxy;

@SuppressWarnings("all")
public class CancelOrdersServlet extends HttpServlet {

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
		//根据oid和uid删除订单  物理删除
		String oid = request.getParameter("id");
		int uid = user.getId();
		OrdersService service = new OrdersServiceProxy().getOrdersServiceProxy();
		service.cancelOrders(oid,uid);
		//跳转到查询订单的servlet
		request.getRequestDispatcher("/queryOrdersServlet").forward(request, response);
	}

}
