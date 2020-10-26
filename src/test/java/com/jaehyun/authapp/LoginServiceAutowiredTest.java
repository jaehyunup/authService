package com.jaehyun.authapp;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.jaehyun.authapp.dto.Member;

@SpringBootTest
class LoginServiceAutowiredTest {
    private static final Logger logger = LoggerFactory.getLogger(Assert.class);
	@Autowired
	UserDetailsService loginService;
	
	@Test
	@DisplayName("로그인서비스객체_DI_테스트")
	void 로그인서비스_Autowired_DI_잘작동하는지() {
		 
		 UserDetails member=loginService.loadUserByUsername("admin");
		 logger.info(member.toString());
		 assertTrue("admin".equals(member.getUsername()));
		 assertTrue("admin".equals(member.getPassword()));
         }

}
