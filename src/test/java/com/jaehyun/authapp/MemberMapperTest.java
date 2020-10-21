package com.jaehyun.authapp;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jaehyun.authapp.dao.MemberMapper;
import com.jaehyun.authapp.dto.Member;

@SpringBootTest
class MemberMapperTest {
    private static final Logger logger = LoggerFactory.getLogger(Assert.class);
	@Autowired
	MemberMapper mapper;
	@Test
	@DisplayName("맵퍼 회원이름 조회 테스트케이스")
	void 회원이름으로_조회_잘되는지() {
		 mapper.findByUserName("admin");
		 
		 Member member = mapper.findByUserName("admin");
		 logger.info(member.toString());
		 assertTrue("admin".equals(member.getUsername()));
		 assertTrue("admin".equals(member.getNickname()));
		 assertTrue("admin".equals(member.getPassword()));
         }

}
