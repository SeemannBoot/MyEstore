package cn.itcast.estore.service.impl;

import java.util.List;

import cn.itcast.estore.dao.CartDAO;
import cn.itcast.estore.dao.GoodsDAO;
import cn.itcast.estore.dao.impl.CartDAOImpl;
import cn.itcast.estore.dao.impl.GoodsDAOImpl;
import cn.itcast.estore.domain.Cart;
import cn.itcast.estore.domain.Goods;
import cn.itcast.estore.service.CartService;

public class CartServiceImpl implements CartService {

	@Override
	public void addOrUpdate(String gid, String num, Integer uid) {
		// 是否为第一次购买该商品
		CartDAO dao = new CartDAOImpl();
		Cart cart = dao.isBuy(gid,uid);
		if(cart == null) {
			//第一次买该商品 添加记录
			dao.add(gid,num,uid);
		} else {
			//已经存在购物记录  修改购买数量即可
			Integer buynum = cart.getBuynum() + Integer.parseInt(num);
			cart.setBuynum(buynum);
			dao.update(cart);
		}
	}

	@Override
	public List<Cart> queryCart(int id) {
		CartDAO dao = new CartDAOImpl();
		List<Cart> clist = dao.queryCart(id);
		//System.out.println(clist);
		
		//给cart添加商品属性
		GoodsDAO goodsDao = new GoodsDAOImpl();
		for (Cart cart : clist) {
			Goods good = goodsDao.findById(cart.getGid()+"");
			cart.setGoods(good);
		}
		//System.out.println(clist);
		return clist;
	}

	@Override
	public void changeBuynum(String gid, String buynum, Integer uid) {
		CartDAO dao = new CartDAOImpl();
		Cart cart = new Cart();
		cart.setUid(uid);
		cart.setBuynum(Integer.parseInt(buynum));
		cart.setGid(Integer.parseInt(gid));
		dao.update(cart);
	}

	@Override
	public void delCart(String gid, Integer uid) {
		CartDAO dao = new CartDAOImpl();
		dao.delCart(gid,uid);
	}

}
