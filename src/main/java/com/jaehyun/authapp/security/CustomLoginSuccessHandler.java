package com.jaehyun.authapp.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

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
*/ 
public class CustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * @methodName  : onAuthenticationSuccess
     * @author      : jaehyun Park
     * @date        : 2020.10.26
     * @description : 인증 성공시 Security Context에 인증 정보를 저장
     * @param request
     * @param response
     * @param authentication
     * @throws IOException
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        response.sendRedirect("/home");
    }

}