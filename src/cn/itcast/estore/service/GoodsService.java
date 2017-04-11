package cn.itcast.estore.service;

import java.util.List;

import cn.itcast.estore.domain.Goods;

public interface GoodsService {

	List<Goods> findAll();

	Goods findById(String id);

	void addGood(Goods good);

}
