package com.jaehyun.authapp.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaehyun.authapp.dto.Member;
import com.jaehyun.authapp.mappers.MemberMapper;

/**
* @packageName : com.jaehyun.authapp.service
* @fileName	   : LoginService.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.21
* @description : 로그인 비즈니스로직 정의 클래스
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.21       parkjaehyun     최초생성
*/ 
@Service
public class LoginService implements UserDetailsService {
	@Autowired
	MemberMapper memberMapper;
	
	/**
	 * @methodName  : loadUserByUsername
	 * @author      : jaehyun Park
	 * @date        : 2020.10.21
	 * @description : 유저이름을 기반으로 DB에서 정보조회
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(memberMapper.toString());
		Member member=memberMapper.findByUserName(username);		
		member.setAuthorities(getAuthorities(username));
		System.out.println(member);
		return member;
	}
	
	  public Collection<GrantedAuthority> getAuthorities(String username) {
	    	 Collection<GrantedAuthority> authorities = memberMapper.getAuthorities(username);
	    	 return authorities;
	  }

}
