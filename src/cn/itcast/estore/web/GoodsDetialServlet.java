package cn.itcast.estore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Goods;
import cn.itcast.estore.service.GoodsService;
import cn.itcast.estore.service.impl.GoodsServiceImpl;

@SuppressWarnings("all")
public class GoodsDetialServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//获取商品id
		String id = request.getParameter("id");
		//调用service查询商品信息
		GoodsService service = new GoodsServiceImpl();
		Goods good = service.findById(id);
		//保存商品信息并转发
		request.setAttribute("good", good);
		request.getRequestDispatcher("/goods_detail.jsp").forward(request, response);
	}

}
