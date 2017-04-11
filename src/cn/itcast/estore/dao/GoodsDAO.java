package cn.itcast.estore.dao;

import java.util.List;

import cn.itcast.estore.domain.Goods;

public interface GoodsDAO {

	List<Goods> findAll();

	Goods findById(String id);

	void addGood(Goods good);

}
