# WharfProject

> #### 야적장 예약 신청, 조회 시스템.


##### 기간: 2020.09 ~ 
##### 언어 : JAVA, IntelliJ, Gradle
##### 기능 : 회사의 야적장 예약 신청, 조회, 승인을 API

------------
------------
## 데이터 베이스 스키마
<img src="https://user-images.githubusercontent.com/57051773/101279950-624ce600-3809-11eb-8c78-94370476fae8.jpg" width="100%">

### Entity
> ##### Wharf: 야적장에 대한 정보(야적장 이름, 종류[본항/신항], 면적, 제외면적)
> ##### Company: 회사에 대한 정보(회사 코드, 회사 이름, 경고 횟수)
> ##### Orders: 야적장에 대한 회사의 예약 신청 정보(야적장 종류, 승인상태, 시작날짜, 종료날짜, 기타 주요 정보)
> ##### Usages: 야적장에 대한 회사의 실사용내역 정보(시작날짜, 종료날짜, 면적, 사용회사, 사용야적장)
> ##### Fee: 야적장 종류별 사용에 대한 요금정보(요금, 야적장 종류)
> ##### Info: 공지사항에 대한 정보(공지사항 제목, 공지사항 내용)

------------
------------
## API
> ### GetMethod(ex)
> ##### [Get]http://localhost:8080/usage 
> ###### Response: 
<img src="https://user-images.githubusercontent.com/57051773/101280677-c5407c00-380d-11eb-8a14-cbb7fe4e578d.JPG" width="60%">

> ##### [Get]http://localhost:8080/usage/2 
> ###### Response: 
<img src="https://user-images.githubusercontent.com/57051773/101280684-cec9e400-380d-11eb-930e-1cd0172571e0.JPG" width="60%">

------------
> ### PostMethod(ex)
> ##### [Post]http://localhost:8080/usage 
> ###### Request body: 
> `{
	"start_date": "2020-11-06",
 	"end_date": "2020-11-08",
 	 "area": 3512,
  	"company_id": 5,
 	 "wharf_id": 4
}`
> ###### Response: 
<img src="https://user-images.githubusercontent.com/57051773/101280686-d8ebe280-380d-11eb-8bfb-0552db3bb9fc.JPG" width="60%">

------------
> ### PutMethod(ex)
> ##### [Put]http://localhost:8080/usage 
> ###### Request body:
> `{
    "id": 8,
	"start_date": "2020-11-06",
 	"end_date": "2020-11-08",
 	 "area": 3512,
  	"company_id": 5,
 	 "wharf_id": 4
}`
> ###### Response: 
<img src="https://user-images.githubusercontent.com/57051773/101280693-e6a16800-380d-11eb-86b3-25b784c4afe2.JPG" width="60%">

------------
> ### DeleteMethod(ex)
> ##### [Delete]http://localhost:8080/usage 
> ###### Response: 
<img src="https://user-images.githubusercontent.com/57051773/101280707-f325c080-380d-11eb-8810-0764f989a6bb.JPG" width="60%">

------------
------------
## Screenshot(미완성)
<img src="https://user-images.githubusercontent.com/57051773/101280915-48ae9d00-380f-11eb-8af5-e81d133a4bde.JPG">
<img src="https://user-images.githubusercontent.com/57051773/101280918-4ba98d80-380f-11eb-8e5a-1d39bc15fb0c.JPG">

