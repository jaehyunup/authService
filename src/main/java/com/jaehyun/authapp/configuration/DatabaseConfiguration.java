package com.jaehyun.authapp.configuration;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
* @packageName : com.jaehyun.authapp.configuration
* @fileName	   : DatabaseConfiguration.java
* @author 	   : parkjaehyun
* @date 	   : 2020.10.20
* @description : 데이터베이스 설정 클래스 
* ============================================================================
* DATE		       AUTHOR        	 NOTE
* ----------------------------------------------------------------------------
* 2020.10.20       parkjaehyun      최초생성
* 2020.10.20	   parkjaehyun      sqlSessionFactory 생성 및 application Context에 등록부 추가
*/ 

@Configuration
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {
	@Autowired
	ApplicationContext applicationContext; 

	/**
	     * @methodName  : hikariConfig
	     * @author      : jaehyun Park
	     * @date        : 2020.10.20
	     * @description : hikari Connection pool option을 properties에서 가져와 등록.
	     * @return
	     */	
	    @Bean
	    @ConfigurationProperties(prefix="spring.datasource.hikari")
	    public HikariConfig hikariConfig() {
	        return new HikariConfig();
	    }

	/**
	 * @methodName : dataSource
	 * @author : jaehyun Park
	 * @date : 2020.10.20
	 * @description : hikari Conntion poll을 이용한 데이터베이스 리소스 객체생성
	 * @return
	 * @throws Exception
	 */
	@Bean
	public DataSource dataSource() throws Exception {
		DataSource dataSource = new HikariDataSource(hikariConfig());
		System.out.println(dataSource.toString());
		return dataSource;
	}
	
	/**
	 * @methodName  : sqlSessionFactory
	 * @author      : jaehyun Park
	 * @date        : 2020.10.20
	 * @description : Mybatis가 사용할 sqlSessionFactory을 생성한다.
	 * @param dataSource
	 * @return
	 * @throws Exception
	 */
	@Bean
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mappers/**/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	/**
	 * @methodName  : sqlSessionTemplate
	 * @author      : jaehyun Park
	 * @date        : 2020.10.20
	 * @description : mybatis가 사용할 sessionTemplate의 factory에 sessionFactory를 적용한다.
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
