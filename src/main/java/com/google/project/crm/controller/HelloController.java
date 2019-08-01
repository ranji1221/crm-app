package com.google.project.crm.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.google.project.crm.entity.Product;
import com.google.project.crm.service.prototype.IProductService;

/**
 * 测试接口
 * @author RanJi
 *
 */
@Controller
public class HelloController {
	
	@Autowired
	private IProductService prodService;
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello(){
		return "hello crm.";
	}
	
	@GetMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView();
		List<Product> products = prodService.getAllProducts();
		mv.addObject("msg", "The thymeleaf3 is very good.");
		mv.addObject("role", "employee");
		mv.addObject("products", products);
		mv.setViewName("index");
		return mv;
	}
	
	@GetMapping("/demo")
	//@RequestMapping(name="/demo",method=RequestMethod.GET)
	public String demo(){
		return "demo";
	}
	
	@GetMapping("/toupload")
	public String toUpload(){
		return "upload";
	}
	
	@PostMapping("/upload")
	@ResponseBody
	public String uploadFile(@RequestParam("file") MultipartFile[] files)
			throws Exception{
		//--System.out.println("文件个数："+files.length);
		for (MultipartFile file : files) {
            file.transferTo(new File("D:/upload/corejava/"+file.getOriginalFilename()));
        }
		return "{'upload':'ok'}";
	}
	
	@GetMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView();
		//-- 获取所有的分类
		File allCat = new File("D:/upload");
		String[] ac = allCat.list();
		mv.addObject("categories", ac);
		mv.setViewName("list");
		return mv;
	}
	
	@GetMapping("/list/{category}")
	@ResponseBody
	public String list(@PathVariable String category){
		//-- 1. 获取该文件目录下的所有文件
		File cf = new File("D:/upload/"+category);
		String[] files = cf.list();
		//-- 2. 返回文件列表JSON字符串
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("files", files);
		return jsonObj.toJSONString();
	}
	
	@GetMapping("/download/{category}/{file}")
	public String download(
			@PathVariable String category, 
			@PathVariable String file, 
			HttpServletResponse response)throws Exception{
		//-- 获取要下载的文件对象
        File f = new File("D:/upload/"+category+"/"+file);
        //-- 设置响应类型
        response.setContentType("application/force-download");
        //-- 设置响应文件名
        response.addHeader("Content-Disposition",
                "attachment;fileName=" + f.getName());
		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		//-- 文件输入流对象
	    fis = new FileInputStream(f);
	    //-- 缓冲流对象
	    bis = new BufferedInputStream(fis);
	    //-- 输出流对象
	    OutputStream os = response.getOutputStream();
	    int len = 0;
	    while ((len=bis.read(buffer))!= -1) {
	        os.write(buffer, 0, len);
	    }
	    bis.close();
	    fis.close();
	    System.out.println("文件下载成功");
	    return null;
	}
	
}
/*
 * 小项目：
 * 	软件工具管理平台
 *  软件分类
 *  上传相应的软件工具
 *  下载某个工具
 * CRM: 
 * 	了解CRM，客户关系管理平台，隶属于ERP的一个子系统
 *  大昌集团：卖汽车  4s店
 *          坐席：
 *  飞鸽传书
 *  
 *  建立分类
 *  给某个分类上传文件
 *  文件下载
 *         
 */

