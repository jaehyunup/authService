package com.jaehyun.authapp.mybatisTypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
* @packageName : com.jaehyun.authapp.mybatisTypeHandler
* @fileName	   : AuthorityTypeHandler.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.26
* @description : Member DTO의 authorities 멤버 변수 맵핑을 위한 Mybatis TypeHandler
* ============================================================================
* DATE       	   AUTHOR  	       NOTE
* ----------------------------------------------------------------------------
* 2020.10.26       parkjaehyun     최초생성
*/ 
@MappedJdbcTypes(JdbcType.VARCHAR)
public class AuthorityTypeHandler extends BaseTypeHandler<SimpleGrantedAuthority> {

     @Override
     public void setNonNullParameter(PreparedStatement ps, int i,
               SimpleGrantedAuthority parameter, JdbcType jdbcType) throws SQLException {
          ps.setString(i, parameter.getAuthority());
     }

     @Override
     public SimpleGrantedAuthority getNullableResult(ResultSet rs, String columnName)
               throws SQLException {
          return new SimpleGrantedAuthority(rs.getString(columnName));
     }

     @Override
     public SimpleGrantedAuthority getNullableResult(ResultSet rs, int columnIndex)
               throws SQLException {
          return new SimpleGrantedAuthority(rs.getString(columnIndex));
     }

     @Override
     public SimpleGrantedAuthority getNullableResult(CallableStatement cs,
               int columnIndex) throws SQLException {
          return new SimpleGrantedAuthority(cs.getString(columnIndex));
     }

	
}