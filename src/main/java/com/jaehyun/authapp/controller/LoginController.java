package com.jaehyun.authapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@Autowired
	UserDetailsService userDetailService; 
	
	@GetMapping("/")
	public String loginPage(@RequestParam Map<String,Object> model) {
		
		return "loginView";
	}
	
	
}
