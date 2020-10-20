package com.jaehyun.authapp;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
* @packageName : com.jaehyun.authapp
* @fileName	   : AuthappApplication.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.20
* @description : 
* ============================================================================
* DATE       AUTHOR         NOTE
* ----------------------------------------------------------------------------
* 2020.10.20       parkjaehyun           최초생성
*/ 
@SpringBootApplication
public class AuthappApplication {

	public static void main(String[] args) {
		/*TEST update*/
		SpringApplication.run(AuthappApplication.class, args);
	}

}
