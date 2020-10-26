package com.jaehyun.authapp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

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
    public List<String> getAuthorities(String username);

}
