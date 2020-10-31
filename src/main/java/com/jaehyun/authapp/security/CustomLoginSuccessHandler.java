package com.jaehyun.authapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

/**
* @packageName : com.jaehyun.authapp.security
* @fileName	   : CustomLoginSuccessHandler.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.26
* @description : 로그인 성공시 페이지 핸들링용 핸들러
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.26       parkjaehyun     최초생성
* 2020.10.27 	   parkjaehyun     페이지 핸들링 과정에서 세션에 유저정보담는 코드 추가
*/ 
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * @methodName  : onAuthenticationSuccess
     * @author      : jaehyun Park
     * @date        : 2020.10.26
     * @description : 인증성공시 수행되는 메서드
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     * @throws ServletException 
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        
  	  	if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
  	  		getRedirectStrategy().sendRedirect(request, response, "admin");
        }else {
        	getRedirectStrategy().sendRedirect(request, response, "home");
        }
        
       


        
    }

}