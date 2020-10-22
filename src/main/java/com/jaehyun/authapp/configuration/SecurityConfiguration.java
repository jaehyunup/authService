package com.jaehyun.authapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
* 2020.10.21       parkjaehyun     configure(Http) 오버라이딩 메소드 구현 
* 2020.10.22 	   parkjaehyun     configure(WebSecurity) 오버라이딩 메소드 구현
* 2020.10.22 	   parkjaehyun     passwordEncoder 빈 추가(BCryptPasswordEncoder 사용)
* 
*/ 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Qualifier("loginService")
	@Autowired
	UserDetailsService loginService;
	/**
	 * @methodName  : configure
	 * @author      : jaehyun Park
	 * @date        : 2020.10.22
	 * @description : web 요청에대한 Security Configuration
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**").anyRequest(); // resources 이후 디렉토리에대한 접근권한 허가
	}

	
	/**
	 * @methodName  : configure
	 * @author      : jaehyun Park
	 * @date        : 2020.10.21
	 * @description : http 요청에대한 Security Configuration
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		  http
          .authorizeRequests() // 해당 메소드 아래는 각 경로에 따른 권한을 지정할 수 있다.
              .antMatchers("/" , "/login" , "/service" , "/resources/**", "/create").permitAll() // 로그인 권한은 누구나, resources파일도 모든권한
              .antMatchers("/*.js").permitAll()
              .antMatchers("/*.css").permitAll()
              .antMatchers("/admin").hasRole("ADMIN") // 괄호의 권한을 가진 유저만 접근가능, ROLE_가 붙어서 적용 됨. 즉, 테이블에 ROLE_권한명 으로 저장해야 함.
              .antMatchers("/user").hasRole("USER")
              .antMatchers("/member").hasRole("MEMBER")
              .anyRequest().authenticated()
          .and()
          .formLogin()
              .loginPage("/") // 로그인페이지
              .loginProcessingUrl("/login")// 로그인 요청 url
              .usernameParameter("username")
              .passwordParameter("password")
              .failureUrl("/login?error=true") // 실패시
              .defaultSuccessUrl("/loginSuccess",true) // 성공시
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
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginService)
		.and()
		.authenticationProvider(authenticationProvider());
	}
	
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loginService); 
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

}
