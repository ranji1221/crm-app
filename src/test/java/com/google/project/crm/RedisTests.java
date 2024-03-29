package com.google.project.crm;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.project.crm.entity.Product;
import com.google.project.crm.service.prototype.IProductService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTests {
	
	@Autowired
	private StringRedisTemplate srt;
	
	@Autowired
	private RedisTemplate<Object,Object> rt;
	
	@Autowired
	private IProductService prodService;
	
	@Test
	public void test01(){
		srt.opsForValue().set("javaee","unjf-1903");
	}
	
	@Test
	public void test02(){
		String value = srt.opsForValue().get("javaee");
		System.out.println(value);
	}
	
	@Test
	public void test03(){
		Product p = new Product();
		p.setId(1);
		p.setName("笔记本");
		p.setPrice(5000);
		
		rt.opsForValue().set("aaa", p, Duration.ofSeconds(60));
	}
	
	@Test
	public void test04(){
		Product p = (Product)rt.opsForValue().get("product");
		System.out.println(p);
	}
	
	@Test
	public void test05(){
		List<Product> ps = prodService.getAllProducts();
		for (Product product : ps) {
			System.out.println(product);
		}
	}
}
