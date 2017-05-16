package com.jasper.demo.springmvc.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jasper.demo.springmvc.bean.LoginBean;
import com.jasper.demo.springmvc.bean.Result;
import com.jasper.demo.springmvc.bean.UserInfo;
import com.jasper.demo.springmvc.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	@Value("${uploadPath}")
	private String uploadPath;

	public UserController() {
		System.out.println("UserController");
		System.out.println("userService=" + userService);
		System.out.println("uploadPath=" + uploadPath);
	}

	@PostConstruct
	public void init() {
		System.out.println("UserController init=======");
		System.out.println("userService=" + userService);
		System.out.println("uploadPath=" + uploadPath);}

	// 处理乱码，加上produces = {"text/json;charset=UTF-8"}
	@RequestMapping(value = "/string/login", produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public Object loginForString(String username, String password) {
		return userService.login(username, password);
	}
	
	// 自动包箱
	@RequestMapping(value = "/login")
	@ResponseBody
	public Object login(LoginBean loginBean) {
		return userService.login(loginBean.getUsername(), loginBean.getPassword());
	}

	// 如果要返回对象，则需要导入jackson相关jar包，这里没有乱码问题
	// 返回对象，也可以用response.getWriter().print(jsonStr);然后方法返回值为void
	@RequestMapping("/json/login")
	@ResponseBody
	public Result loginForJson(String username, String password) {
		Result result = new Result();
		result.setCode(1);
		result.setMsg(userService.login(username, password));
		return result;
	}

	@RequestMapping("/hello")
	public String hello() {
		return "user/hello";
	}
	
	//the parameter was converted in initBinder
	@RequestMapping(value = "/date", produces = { "text/json;charset=UTF-8" })
	@ResponseBody
	public String date(Date date){
	    return "日期：" + date.toString();
	}
	    
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
	    binder.registerCustomEditor(Date.class, 
	    		new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	// 传递参数到前端
	@RequestMapping("/result")
	public String result(Map<String, Object> map) {
		map.put("msg", "HelloWorld");
		return "user/result";
	}
	
	// 重定向
	@RequestMapping("/redirect")
	public String redirect(){
	    return "redirect:hello";
	}
	
	@RequestMapping(value="/{id}")
	@ResponseBody
    public String get(@PathVariable("id") Integer id){
        return "param:id=" + id;
    }
	
	@RequestMapping(value="uploadfileUI")
	public String uploadfileUI(){
		return "user/uploadfile";
	}
	
	// 文件上传
	@RequestMapping(value="/uploadfile")
	public String upload(MultipartFile file, HttpServletRequest req, 
			Map<String, Object> result) throws Exception{
	    String fileName = file.getOriginalFilename();
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	    String savePath = uploadPath + sdf.format(new Date()) + 
	            fileName.substring(fileName.lastIndexOf('.'));
	    File path = new File(uploadPath);
	    if (! path.exists()) {
	    	path.mkdirs();
	    }
	    FileOutputStream fos = new FileOutputStream(savePath);
	    fos.write(file.getBytes());
	    fos.flush();
	    fos.close();
	    
	    result.put("msg", "path:" + savePath);
	    return "user/result";
	}
	
	// 局部异常
	@ExceptionHandler
	public ModelAndView exceptionHandler(Exception ex){
	    ModelAndView mv = new ModelAndView("error");
	    mv.addObject("msg", ex);
	    return mv;
	}
	    
	@RequestMapping("/error")
	public String error(){
	    int i = 5/0;
	    System.out.println(i);
	    return "hello";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public Object add(UserInfo userInfo) {
		Result result = new Result();
		result.setCode(1);
		result.setData(userInfo);
		return result;
	}

}
