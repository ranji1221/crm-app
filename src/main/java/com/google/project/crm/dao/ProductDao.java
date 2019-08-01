package com.google.project.crm.dao;

import java.util.List;
import org.apache.ibatis.annotations.Select;
import com.google.project.crm.entity.Product;

/**
 * 产品Dao
 * @author RanJi
 *
 */
public interface ProductDao {
	@Select("select * from t_product")
	List<Product> findAll();
	void save(Product product);
}
