package com.jaehyun.authapp.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jaehyun.authapp.dto.Member;

/**
* @packageName : com.jaehyun.authapp.controller
* @fileName	   : HomeController.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.27
* @description : 홈 컨트롤러
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.27       parkjaehyun     최초생성
*/ 
@Controller
public class HomeController {
	/**
	 * @methodName  : home
	 * @author      : jaehyun Park
	 * @date        : 2020.10.27
	 * @description : 인증 완료된 사용자만 요청할 수 있는 get Request 
	 * @param model
	 * @return
	 */
	@GetMapping("/home")
	public String home(HttpServletRequest request,HttpServletResponse response) {
    	  System.out.println("컨트롤러 써큐리티컨텍스트:"+SecurityContextHolder.getContext().getAuthentication());
    	  System.out.println("컨트롤러 써큐리티컨텍스트:"+SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication());
		return "home";
	}
	
	@GetMapping("/admin")
	public String admin(@RequestParam Map<String,Object> model) {
		return "admin";
	}
	
}
