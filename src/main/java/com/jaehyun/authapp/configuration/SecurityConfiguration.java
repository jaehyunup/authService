package com.jaehyun.authapp.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jaehyun.authapp.security.CustomAuthenticationFailureHandler;
import com.jaehyun.authapp.security.CustomAuthenticationFilter;
import com.jaehyun.authapp.security.CustomAuthenticationProvider;
import com.jaehyun.authapp.security.CustomLoginSuccessHandler;

/**
 * @packageName : com.jaehyun.authapp.configuration
 * @fileName : SecurityConfiguration.java
 * @author : parkjaehyun
 * @date : 2020.10.21
 * @description : spring security 설정 파일 ============================================================================ DATE AUTHOR NOTE ---------------------------------------------------------------------------- 2020.10.21 parkjaehyun 최초생성 2020.10.21
 *              parkjaehyun configure(Http) 오버라이딩 메소드 구현 2020.10.22 parkjaehyun configure(WebSecurity) 오버라이딩 메소드 구현 2020.10.22 parkjaehyun passwordEncoder 빈 추가(BCryptPasswordEncoder 사용)
 * 
 */
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService loginService;

	/**
	 * @methodName : configure
	 * @author : jaehyun Park
	 * @date : 2020.10.22
	 * @description : web 요청에대한 Security Configuration
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**").anyRequest(); // resources 이후 디렉토리에대한 접근권한 허가
	}

	/**
	 * @methodName : configure
	 * @author : jaehyun Park
	 * @date : 2020.10.21
	 * @description : http 요청에대한 Security Configuration
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
         .antMatchers("/home/**").authenticated()
         .antMatchers("/admin/**").authenticated()
         .antMatchers("/**").permitAll();

	 http.formLogin()
	         .loginPage("/")
	         .loginProcessingUrl("/login")
	         .defaultSuccessUrl("/home")
	         .permitAll();
	
	 http.logout()
	         .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	         .logoutSuccessUrl("/login")
	         .invalidateHttpSession(true);
	
	 http.exceptionHandling()
	         .accessDeniedPage("/denied");
		}

	/**
	 * @methodName : passwordEncoder
	 * @author : jaehyun Park
	 * @date : 2020.10.26
	 * @description : 비밀번호 암호화 인코더 빈등록
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * @methodName : customAuthenticationFilter
	 * @author : jaehyun Park
	 * @date : 2020.10.26
	 * @description : custom 인증 필터 빈 등록
	 * @return
	 * @throws Exception
	 */
	@Bean
	public CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
		CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManager());
		customAuthenticationFilter.setFilterProcessesUrl("/login");
		customAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
		customAuthenticationFilter.setAuthenticationFailureHandler(curstomAuthenticationFailureHandler());
		customAuthenticationFilter.setAllowSessionCreation(true);
		customAuthenticationFilter.afterPropertiesSet();
		return customAuthenticationFilter;
	}

	@Bean
	public AuthenticationFailureHandler curstomAuthenticationFailureHandler() {
		CustomAuthenticationFailureHandler failureHandler = new CustomAuthenticationFailureHandler();
		failureHandler.setDefaultFailureUrl("/?error=error");
		return failureHandler;
	}

	/**
	 * @methodName : customLoginSuccessHandler
	 * @author : jaehyun Park
	 * @date : 2020.10.26
	 * @description : custom 로그인 성공 핸들러 빈 등록
	 * @return
	 */
	@Bean
	public CustomLoginSuccessHandler customLoginSuccessHandler() {
		return new CustomLoginSuccessHandler();
	}

	/**
	 * @methodName : customAuthenticationProvider
	 * @author : jaehyun Park
	 * @date : 2020.10.26
	 * @description : 인증 프로바이더에 encoder 적용
	 * @return
	 */
	@Bean
	public CustomAuthenticationProvider customAuthenticationProvider() {
		return new CustomAuthenticationProvider(new BCryptPasswordEncoder());
	}

}
