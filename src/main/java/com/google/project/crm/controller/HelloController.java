package com.google.project.crm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试接口
 * @author RanJi
 *
 */
@Controller
public class HelloController {
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello crm.";
	}
}
