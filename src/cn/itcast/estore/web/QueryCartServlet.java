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
public class QueryCartServlet extends HttpServlet {

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
		//根据用户id查询cart表
		CartService service = new CartServiceImpl();
		List<Cart> clist = service.queryCart(user.getId());
		//保存查询到的数据
		request.setAttribute("clist", clist);
		//跳转到cart.jsp页面
		request.getRequestDispatcher("/cart.jsp").forward(request, response);
	}

}
