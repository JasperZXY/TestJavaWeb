package com.jasper.demo.springmvc.controller.handler;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class MyControllerAdvice {
	@InitBinder
	public void initDate(WebDataBinder binder) {
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
//		dateFormat.setLenient(false);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
		binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd")); // Spring 4.2之后的写法
	}

	// @ExceptionHandler
	// public ModelAndView exceptionHandler(Exception ex){
	// ModelAndView mv = new ModelAndView("error");
	// mv.addObject("msg", ex);
	// return mv;
	// }
}
