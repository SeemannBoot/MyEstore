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
public class BuyGoodsServlet extends HttpServlet {

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
		//获取参数
		String gid = request.getParameter("id");
		String num = request.getParameter("num");
		Integer uid = user.getId();
		//调用service
		CartService service = new CartServiceImpl();
		service.addOrUpdate(gid,num,uid);
		//跳转到中间页面  结账or继续购物
		request.getRequestDispatcher("buyorcart.jsp").forward(request, response);
	}

}
