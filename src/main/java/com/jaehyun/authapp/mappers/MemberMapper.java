package com.jaehyun.authapp.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.GrantedAuthority;

import com.jaehyun.authapp.dto.Member;

/**
* @packageName : com.jaehyun.authapp.mappers
* @fileName	   : MemberMapper.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.21
* @description : Mybatis를 이용한 회원 Mapper 클래스
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.21       parkjaehyun     최초생성
* 2020.10.26       parkjaehyun     getAuthorities 메소드 리턴 타입 변경 List<String> -> List<GrantedAuthority>
*/ 
@Mapper
public interface MemberMapper {
	/**
	 * @methodName  : findByUserName
	 * @author      : jaehyun Park
	 * @date        : 2020.10.21
	 * @description : 회원 조회(아이디기반)
	 * @param username
	 * @return
	 */
	public Member findByUserName(String username);
	/**
	 * @methodName  : joinMember
	 * @author      : jaehyun Park
	 * @date        : 2020.10.21
	 * @description : 회원가입 
	 * @param member
	 * @return
	 */
	public int joinMember(Member member);
	
    /**
     * @methodName  : readAuthority
     * @author      : jaehyun Park
     * @date        : 2020.10.21
     * @description : 해당 유저의 권한을 모두 가져온다.
     * @param username
     * @return
     */
    public List<GrantedAuthority> getAuthorities(String username);

}
