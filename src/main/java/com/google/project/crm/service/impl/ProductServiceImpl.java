package com.google.project.crm.service.impl;

import java.time.Duration;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.google.project.crm.dao.ProductDao;
import com.google.project.crm.entity.Product;
import com.google.project.crm.service.prototype.IProductService;
/**
 * 产品业务实现类
 * @author RanJi
 *
 */
@Service
public class ProductServiceImpl implements IProductService{
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public void createProduct(Product product) {
		productDao.save(product);
	}

	@Override   //-- Redis缓存
	@Cacheable(value="products")
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

}
