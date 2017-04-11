package cn.itcast.estore.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.Goods;
import cn.itcast.estore.service.GoodsService;
import cn.itcast.estore.service.impl.GoodsServiceImpl;

@SuppressWarnings("serial")
public class QueryGoodsServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//调用service查询商品信息
		GoodsService service = new GoodsServiceImpl();
		List<Goods> glist = service.findAll();
		//保存商品信息
		request.setAttribute("glist", glist);
		//转发到商品列表的jsp
		request.getRequestDispatcher("/goods.jsp").forward(request, response);
	}

}
