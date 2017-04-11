package cn.itcast.estore.service.impl;

import java.util.List;

import cn.itcast.estore.dao.GoodsDAO;
import cn.itcast.estore.dao.impl.GoodsDAOImpl;
import cn.itcast.estore.domain.Goods;
import cn.itcast.estore.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {

	@Override
	public List<Goods> findAll() {
		GoodsDAO dao = new GoodsDAOImpl();
		return dao.findAll();
	}

	@Override
	public Goods findById(String id) {
		GoodsDAO dao = new GoodsDAOImpl();
		return dao.findById(id);
	}

	@Override
	public void addGood(Goods good) {
		GoodsDAO dao = new GoodsDAOImpl();
		dao.addGood(good);
	}

}
