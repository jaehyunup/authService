package com.jaehyun.authapp.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
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
	public String home(HttpSession session,Principal principal) {
		Object securityContextObject = session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
		if(securityContextObject != null){ 
			SecurityContext securityContext = (SecurityContext)securityContextObject;
			//System.out.println(securityContext);
			}
	
		//번째 방법 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
		System.out.println(auth); 
		// 두번째 방법 
		// 세번째 방법 
		System.out.println(principal);
		return "home";
	}
	
	/**
	 * @methodName  : admin
	 * @author      : jaehyun Park
	 * @date        : 2020.10.27
	 * @description : 로그인된 유저의 권한이 관리자라면 별도의 페이지로 이동
	 * @param session
	 * @param model
	 * @return
	 */
	@GetMapping("/admin")
	public String admin(HttpSession session,@RequestParam Map<String,Object> model) {
		System.out.println(session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY));
		return "admin";
	}
	
}
