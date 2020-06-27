# money-seed

환경:
- HTTP API 서버 입니다.
 + java, sts + h2 (Spring boot)를 사용했습니다.
- DB는 h2로 런타임시 테이블 및 초기 사용자 데이터를 생성합니다.
- Orm 은 Spring Jpa를 사용했습니다.

- 테스트 유닛은 MoneyShareTest.java 에 작성되어있습니다.


테스트 결과 로그

15:52:52.951 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/10000/5"
15:52:53.000 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [application/json, application/*+json]
15:52:53.013 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/10000/5" resulted in 200 (null)
15:52:53.014 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [class com.extend.api.vo.Token] as "application/json;charset=UTF-8" using [org.springframework.http.converter.json.MappingJackson2HttpMessageConverter@26abb146]
Token(tokenId=182, createDate=Sat Jun 27 15:52:53 KST 2020)
15:52:53.035 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/pay/182"
15:52:53.035 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [text/plain, application/json, application/*+json, */*]
15:52:53.046 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/pay/182" resulted in 200 (null)
15:52:53.046 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [java.lang.String] as "application/json;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@1500b2f3]
{"received_money":3285,"resultCode":"SUCCESS","received_id":"yonghw2"}
15:52:53.087 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/pay/182"
15:52:53.088 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [text/plain, application/json, application/*+json, */*]
15:52:53.097 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/pay/182" resulted in 200 (null)
15:52:53.097 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [java.lang.String] as "application/json;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@f68f0dc]
{"received_money":3285,"resultCode":"SUCCESS","received_id":"yonghw3"}
15:52:53.109 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/pay/182"
15:52:53.109 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [text/plain, application/json, application/*+json, */*]
15:52:53.119 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/pay/182" resulted in 200 (null)
15:52:53.120 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [java.lang.String] as "application/json;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@14bdbc74]
{"received_money":5605,"resultCode":"SUCCESS","received_id":"yonghw4"}
15:52:53.136 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/pay/182"
15:52:53.136 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [text/plain, application/json, application/*+json, */*]
15:52:53.143 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/pay/182" resulted in 200 (null)
15:52:53.144 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [java.lang.String] as "application/json;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@12591ac8]
{"resultCode":"ALREADY_RECIVED"}
15:52:53.163 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/pay/182"
15:52:53.175 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [text/plain, application/json, application/*+json, */*]
15:52:53.183 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/pay/182" resulted in 200 (null)
15:52:53.187 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [java.lang.String] as "application/json;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@5a7fe64f]
{"resultCode":"NOT_RECIVER"}
15:52:53.194 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/result/182"
15:52:53.194 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [text/plain, application/json, application/*+json, */*]
15:52:53.204 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/result/182" resulted in 200 (null)
15:52:53.204 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [java.lang.String] as "application/json;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@38145825]
{"sharedMoneys":[{"key":"182_yonghw2","id":"yonghw2","money":3285,"tokenId":"182"},{"key":"182_yonghw3","id":"yonghw3","money":3285,"tokenId":"182"},{"key":"182_yonghw4","id":"yonghw4","money":5605,"tokenId":"182"}],"shareTime":"2020-06-27T06:52:53.006+0000","resultCode":"SUCCESS","shareMoney":10000}
15:52:53.216 [main] DEBUG org.springframework.web.client.RestTemplate - Created POST request for "http://localhost:8080/money/share/result/182"
15:52:53.216 [main] DEBUG org.springframework.web.client.RestTemplate - Setting request Accept header to [text/plain, application/json, application/*+json, */*]
15:52:53.223 [main] DEBUG org.springframework.web.client.RestTemplate - POST request for "http://localhost:8080/money/share/result/182" resulted in 200 (null)
15:52:53.223 [main] DEBUG org.springframework.web.client.RestTemplate - Reading [java.lang.String] as "application/json;charset=UTF-8" using [org.springframework.http.converter.StringHttpMessageConverter@7dc19a70]
{"resultCode":"NOT_OWNER"}



감사합니다.
