package cn.itcast.estore.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Cart;
import cn.itcast.estore.domain.Orderitems;
import cn.itcast.estore.domain.Orders;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.CartService;
import cn.itcast.estore.service.OrdersService;
import cn.itcast.estore.service.impl.CartServiceImpl;
import cn.itcast.estore.service.impl.OrdersServiceImpl;
import cn.itcast.estore.utils.UUIDUtils;

@SuppressWarnings("serial")
public class SubmitOrdersServlet extends HttpServlet {

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
		//封装订单   计算价格  拼接地址  获取订单号
		CartService cartService = new CartServiceImpl();
		List<Cart> clist = cartService.queryCart(user.getId());
		// 1计算价格
		Double totalprice = 0D;
		for (Cart cart : clist) {
			totalprice = totalprice + cart.getGoods().getEstoreprice() * cart.getBuynum();
		}
		// 2拼接地址
		String ppp = request.getParameter("province");
		String ccc = request.getParameter("city");
		String ddd = request.getParameter("district");
		
		String dAddress = request.getParameter("dAddress");
		String zipcode = request.getParameter("zipcode");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String address = ppp+ccc+ddd+dAddress+" 邮编:"+zipcode+"  收货人:"+name+"  电话:"+tel;
		String oid = UUIDUtils.getUUID();
		Integer status = 1;
		// 组装订单
		Orders orders = new Orders(oid,user.getId(),totalprice,address,status,new Date());
		
		//封装订单明细
		List<Orderitems> oilist = new ArrayList<Orderitems>();
		for (Cart cart : clist) {
			Orderitems oi = new Orderitems();
			oi.setOid(oid);
			oi.setGid(cart.getGid());
			oi.setBuynum(cart.getBuynum());
			oilist.add(oi);
		}
		//调用service
		OrdersService service = new OrdersServiceImpl();
		service.submitOrders(orders,oilist);
		//跳转到orders.jsp页面
		request.getRequestDispatcher("/queryOrdersServlet").forward(request, response);
	}

}
