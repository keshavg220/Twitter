package com.example.postgresdemo.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RedirectController {

@RequestMapping("/")
	public ModelAndView index () {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("login");
	    return modelAndView;
	}



@RequestMapping("/index.html")
public ModelAndView dashboard () {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("index");
    return modelAndView;
}

@RequestMapping("/login.html")
public ModelAndView login () {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("login");
    return modelAndView;
}
@RequestMapping("/register.html")
public ModelAndView signUp() {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("register");
    return modelAndView;
}

@RequestMapping("/tweets.html")
public ModelAndView tweets () {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.setViewName("tweets");
    return modelAndView;
}
}