package com.jaehyun.authapp.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
* @packageName : com.jaehyun.authapp.dto
* @fileName	   : Member.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.21
* @description : Spring Security UserDetails interface를 구현한 회원 UserDetails 클래스
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.21       parkjaehyun     최초생성
*/
@ToString
@Setter
@Getter
public class Member implements UserDetails{
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String nickname;
    private Collection<? extends GrantedAuthority> authorities;
	private boolean isAccountNonExpired;
	private boolean isAccountNonLocked;
	private boolean isCredentialsNonExpried;
	private boolean isEnabled;
	
	SimpleGrantedAuthority g;
	g.
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	@Override
	public String getUsername() {
		return username;
	}
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpried;
	}
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}
}
