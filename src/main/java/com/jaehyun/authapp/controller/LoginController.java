package com.jaehyun.authapp.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	@GetMapping("/")
	public String loginPage(Map<String,Object> model) {
		return "loginView";
	}

}
