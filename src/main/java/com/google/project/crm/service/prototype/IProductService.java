package com.google.project.crm.service.prototype;

import java.util.List;

import com.google.project.crm.entity.Product;

/**
 * 产品业务接口
 * @author RanJi
 *
 */
public interface IProductService {
	void createProduct(Product product);	//-- 生产产品
	List<Product> getAllProducts();			//-- 获取所有产品
}
