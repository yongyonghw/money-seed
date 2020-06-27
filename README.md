# money-seed

환경:
- HTTP API 서버 입니다.
 + java, sts + h2 (Spring boot)를 사용했습니다.
- DB는 h2로 런타임시 테이블 및 초기 사용자 데이터를 생성합니다.
- Orm 은 Spring Jpa를 사용했습니다.

- 테스트 유닛은 MoneyShareTest.java 에 작성되어있습니다.


테스트 결과 로그
- [토큰생성 뿌리기]
- Token(tokenId=182, createDate=Sat Jun 27 15:52:53 KST 2020)

- [줍기]
- {"received_money":3285,"resultCode":"SUCCESS","received_id":"yonghw2"}
- [줍기2]
- {"received_money":3285,"resultCode":"SUCCESS","received_id":"yonghw3"}
- [줍기3]
- {"received_money":5605,"resultCode":"SUCCESS","received_id":"yonghw4"}
- [줍기4, 이미 있는 경우]
- {"resultCode":"ALREADY_RECIVED"}
- [줍기5, 뿌린자가 주우려 하는 경우]
- {"resultCode":"NOT_RECIVER"}

- [줍기, 조회결과]
- {"sharedMoneys":[{"key":"182_yonghw2","id":"yonghw2","money":3285,"tokenId":"182"},{"key":"182_yonghw3","id":"yonghw3","money":3285,"tokenId":"182"},{"key":"182_yonghw4","id":"yonghw4","money":5605,"tokenId":"182"}],"shareTime":"2020-06-27T06:52:53.006+0000","resultCode":"SUCCESS","shareMoney":10000}

- [뿌린자가 아닌 유저가 조회할 경우]
- {"resultCode":"NOT_OWNER"}



감사합니다.
