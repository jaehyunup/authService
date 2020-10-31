package com.jaehyun.authapp.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
* @packageName : com.jaehyun.authapp.security
* @fileName	   : CustomAuthenticationFailureHandler.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.27
* @description : 사용자 인증 실패시 사용되는 핸들러
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.27       parkjaehyun     최초생성
*/ 
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{
    
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        super.onAuthenticationFailure(request, response, exception);
    }
 
        
}


