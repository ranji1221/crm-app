package com.google.project.crm;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.google.project.crm.dao.ProductDao;
import com.google.project.crm.entity.Product;
import com.google.project.crm.service.prototype.IProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrmApplicationTests {
	
	@Autowired
	private ProductDao prodDao; 
	
	@Autowired
	private StringRedisTemplate srt;
	
	@Autowired
	private IProductService prodService;
	
	@Autowired
	private RedisTemplate<Object, Object> rt;
	
	@Test
	public void insertProduct(){
		for(int i=1;i<=10;i++){
			prodDao.save(new Product("Phone"+i,i*100));
		}
	}
	
	@Test
	public void findProducts() {
		PageHelper.startPage(1, 5);
		List<Product> prods = prodDao.findAll();
		for (Product product : prods) {
			System.out.println(product);
		}
	}
	
	@Test
	public void aaa(){
		String name = srt.opsForValue().get("name");
		System.out.println(name);
		srt.opsForValue().set("hello", "ranji");
	}
	
	@Test
	public void bbb(){
		List<Product> ps = prodService.getAllProducts();
		for (Product product : ps) {
			System.out.println(product);
		}
	}

}
