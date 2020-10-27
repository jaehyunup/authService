package com.jaehyun.authapp;


import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Iterator;
import java.util.List;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.jaehyun.authapp.mappers.MemberMapper;

@SpringBootTest
@DisplayName("사용자 권한(DB에 명시된)이 리스트형태로 잘 불러와지는지")
public class AuthoritiesMappingTest {
    private static final Logger logger = LoggerFactory.getLogger(Assert.class);

	 @Autowired
	 MemberMapper memberMapper;
     
     @Test
     public void Mybatis_타입핸들러를_통해Auth테이블에서_권한이리스트로_가져와지는지() {
         List<GrantedAuthority> authorities = memberMapper.getAuthorities("admin");
         Iterator<GrantedAuthority> it = authorities.iterator();
         
         while (it.hasNext()) {
              GrantedAuthority authority = it.next();
              assertThat(authorities, hasItem(new SimpleGrantedAuthority(authority.getAuthority())));
              logger.info(authority.getAuthority());
         }
    }
}
