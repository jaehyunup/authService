## 1. 시나리오
1. 사용자가 회원가입을 한다 
    - 사용자의 회원가입 요청이 들어오면, 서버는 DB에 접근하여 추가할수있는 회원정보인지 확인한다.
    - 회원가입이 불가능한 정보라면 실패를 반환한다.
    - 회원가입이 가능하다면 회원을 추가한다
2. 사용자가 로그인을 한다
    - 로그인서버에 유저가 입력한 id,password과 함께 로그인 요청이 전달된다.
    - 로그인이 정상적으로 허용되었다면 인증서버에 토큰발급을 요청한다
    - 인증서버는 해당 사용자에 대한 public,private token를 생성하고 private token는 database에 저장하며, public token는 요청한 사용자에게 다시 전달한다.(public token 전달은 쿠키를 이용한다)
    
    
    
# 1.mybatis 적용
# 2.spring security
	- 1. provider security 기반 로그인