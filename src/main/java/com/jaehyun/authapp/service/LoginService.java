package com.jaehyun.authapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jaehyun.authapp.dao.MemberMapper;
import com.jaehyun.authapp.dto.Member;

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
		System.out.println(member);
		if(member==null) {
			throw new UsernameNotFoundException(username);
		}
		return member;
	}

}
