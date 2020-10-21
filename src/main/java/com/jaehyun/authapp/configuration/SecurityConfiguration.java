package com.jaehyun.authapp.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
* @packageName : com.jaehyun.authapp.configuration
* @fileName	   : SecurityConfiguration.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.21
* @description : spring security 설정 파일
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.21       parkjaehyun     최초생성
*/ 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	/**
	 * @methodName  : configure
	 * @author      : jaehyun Park
	 * @date        : 2020.10.21
	 * @description : http security 설정
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
          .authorizeRequests() // 해당 메소드 아래는 각 경로에 따른 권한을 지정할 수 있다.
              .antMatchers("/" , "/login" , "/service" , "/resources/**" , "/create").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
              .antMatchers("/admin").hasRole("ADMIN") // 괄호의 권한을 가진 유저만 접근가능, ROLE_가 붙어서 적용 됨. 즉, 테이블에 ROLE_권한명 으로 저장해야 함.
              .antMatchers("/user").hasRole("USER")
              .antMatchers("/member").hasRole("MEMBER")
              .anyRequest().authenticated()
          .and()
          .formLogin()
              .loginPage("/login") // 로그인페이지
              .loginProcessingUrl("/loginProcess")// 로그인 요청 url
              .defaultSuccessUrl("/loginSuccess") // 성공시
              .failureUrl("/login?error=true") // 실패시
              .permitAll()
          .and()
          .logout()
              .permitAll()
              .logoutUrl("/logout")
              .logoutSuccessUrl("/")
              .and()
          .exceptionHandling()
              .accessDeniedPage("/accessDenied_page"); // 권한이 없는 대상이 접속을시도했을 때
	}

}
