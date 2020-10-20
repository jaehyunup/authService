package com.jaehyun.authapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@RequestMapping("/auth")
	public String auth() {
		return "hello world";
	}
}
