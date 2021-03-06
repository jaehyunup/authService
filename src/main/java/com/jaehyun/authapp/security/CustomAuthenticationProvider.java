package com.jaehyun.authapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;

import com.jaehyun.authapp.dto.Member;

import lombok.RequiredArgsConstructor;

/**
 * @packageName : com.jaehyun.authapp.security
 * @fileName : CustomAuthenticationProvider.java
 * @author : parkjaehyun
 * @date : 2020.10.26
 * @description : 인증 로직을 담당하는 프로바이더 ============================================================================ DATE AUTHOR NOTE ---------------------------------------------------------------------------- 2020.10.26 parkjaehyun 최초생성
 * 
 */
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsService loginService;

	@NonNull
	private BCryptPasswordEncoder passwordEncoder;

	/**
	 * @methodName : authenticate
	 * @author : jaehyun Park
	 * @date : 2020.10.26
	 * @description : AuthenticaionFilter에서 생성된 토큰과, UserDetailService를 이용하여 조회된 내용을확인하고,유저를 인증한다
	 * @param authentication
	 * @return
	 * @throws AuthenticationException
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		if (ObjectUtils.isEmpty(authentication.getDetails())) {
			throw new BadCredentialsException("인증 되지 않은 사용자 입니다.");
		}
		if (authentication.isAuthenticated()) {
			return authentication;

		} else {
			UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
			String userEmail = (String) token.getName();
			String userPassword = (String) token.getCredentials();
			UserDetails member = (Member) loginService.loadUserByUsername(userEmail);
			if (!passwordEncoder.matches(userPassword, member.getPassword())) {
				throw new BadCredentialsException(member.getUsername() + "Invalid password");
			}
			return new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword(),
					member.getAuthorities());
		}
	}

	/**
	 * @methodName : supports
	 * @author : jaehyun Park
	 * @date : 2020.10.26
	 * @description : authenticate 메소드 수행 전 먼저 수행되는 메소드
	 * @param authentication
	 * @return
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}