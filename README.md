# book-finder

##실행하기
1. terminal에서 실행하기

   ```
   mvn spring-boot:run
   
   # 디버깅을 위한 상세한 로그 정보와 함께 실행하고자 하는 경우
   mvn -X spring-boot:run
   ```
   
2. IntelliJ 에서 실행하기
   1. Main Class 실행
       main 메소드를 포함한 클래스 파일을 열고, 왼쪽 녹색 화살표를 눌러서 실행(spring boot)

3. DB 확인하기
    1. h2설정은 기본 설정을 사용
    
    ```
    #    jpa:
    #        database: H2
    #    datasource:
    #        url: jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    #        username: sa
    #        password: 
    #        driverClassName: org.h2.Driver
    ```
    2. h2 console (http://localhost:8080/console)
    
4. Test
    1. curl 사용
    ```
    curl -i -X POST -H "Content-Type:application/json" -d '{"username":"rachel", "password":1234}' localhost:8080/user/signup
    ```
    
##추가기능
1. sign up
    - login을 하기 위한 user정보를 생성하기 위한 기능