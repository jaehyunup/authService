package com.jaehyun.authapp.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.jsonwebtoken.lang.Collections;

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
	public String home(HttpSession session,@AuthenticationPrincipal Principal member) {
		Object securityContextObject = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if(securityContextObject != null){ 
			SecurityContext securityContext = (SecurityContext)securityContextObject;
			System.out.println(securityContext);
			}
	
		return "home";
	}
	
	@GetMapping("/admin")
	public String admin(HttpSession session,@RequestParam Map<String,Object> model) {
		System.out.println(session);
		System.out.println(session.getId());
		Object securityContextObject = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if(securityContextObject != null){ 
			SecurityContext securityContext = (SecurityContext)securityContextObject;
			System.out.println(securityContext);
		}
		return "admin";
	}
	
}
