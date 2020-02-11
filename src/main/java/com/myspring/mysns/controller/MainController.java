package com.myspring.mysns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	@RequestMapping("/")
	// ftl의 user에 accesstoken을 값으로 넣어줌
	// 출처: https://yoonka.tistory.com/459
	public ModelAndView index(@CookieValue(value = "accesstoken", required = false) String accesstoken) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("user", accesstoken);
		return mav;
	}

	@RequestMapping("/signup")
	public ModelAndView signup() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("signup");
		return mav;
	}

	@RequestMapping("/login")
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping("/post/detail/{id}")
	public ModelAndView detail(@PathVariable("id") Long id) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("detail");
		return mav;
	}
}