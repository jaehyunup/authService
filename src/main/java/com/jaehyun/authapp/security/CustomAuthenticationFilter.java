package com.jaehyun.authapp.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
* @packageName : com.jaehyun.authapp.security
* @fileName	   : CustomAuthenticationFilter.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.26
* @description : 뷰에서 전달된 로그인요청은 해당 필터를 먼저 거치고,Token화 되어 AuthenticationManager에게 전달되어 본 인증 과정을 수행한다.
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.26       parkjaehyun     최초생성
*/ 
public class CustomAuthenticationFilter  extends UsernamePasswordAuthenticationFilter{
	public CustomAuthenticationFilter(AuthenticationManager authenticationManager) {
        super.setAuthenticationManager(authenticationManager);
    }

    /**
     * @methodName  : attemptAuthentication
     * @author      : jaehyun Park
     * @date        : 2020.10.26
     * @description : 해당 필터에서 인증 프로세스 이전에 요청에서 사용자 정보를 가져와서 Authentication 객체를 인증 프로세스 객체에게 전달하는 역할 
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(request.getParameter("username"), request.getParameter("password")); 
        setDetails(request, token);
        return this.getAuthenticationManager().authenticate(token);
    }
    

}
