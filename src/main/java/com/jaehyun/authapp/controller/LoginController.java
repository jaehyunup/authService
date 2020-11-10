package com.jaehyun.authapp.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @packageName : com.jaehyun.authapp.controller
* @fileName	   : LoginController.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.26
* @description : 로그인 컨트롤러
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.26       parkjaehyun     최초생성
*/ 
@Controller
public class LoginController {
	/**
	 * @methodName  : loginPage
	 * @author      : jaehyun Park
	 * @date        : 2020.10.26
	 * @description : 로그인 페이지 뷰 전달 
	 * @param model
	 * @return
	 */
	@GetMapping("/")
	public String loginPage(@RequestParam Map<String,Object> model,HttpSession session) {
		if(session!=null) {
			session.invalidate();
		}
//		System.out.println(session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY));
		return "loginView";
	}
	
	
}
