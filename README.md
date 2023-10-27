09PROJECTPLAN
=

## ▶️ 개발 동기

##### 도서관은 지식과 정보를 찾는 핵심 장소이지만, 현재의 도서관 홈페이지와 어플리케이션은 이를 최대한 활용하지 못하고 있습니다. 이런 과제에 도전하고자, 
##### 우리는 도서관을 더 쉽게 이용하고 활성화할 수 있는 홈페이지를 개발하기로 했습니다.-
<br/>

## ▶️ 개발 목표

##### 우리의 주요 목표는 기존 도서 대출 및 반납 프로세스를 온라인으로 혁신하여 이용자들이 더 편리하게 이용할 수 있도록 하는 것입니다. 
##### 더불어, 이용자들 간의 지식 공유와 소통을 촉진하기 위한 커뮤니티 공간을 마련하여, 모든 연령대의 이용자들이 도서관 생태계에 함께 참여할 수 있도록 할 것이다.
##### 우리의 프로젝트는 도서관을 현대화하고, 이용자들에게 더 많은 가치를 제공하며, 더 나은 환경을 만들어 나가기 위한 노력할 것이다.
<br/>

## ▶️ 개발 일정
#### 2023-01-01 ~ 2023-01-05(05Day) : 요구사항분석 / 유스케이스 / 
#### 2023-01-05 ~ 2023-01-15(10Day) : 스타일가이드 / 스토리보드 / ERD / ClassDiagram / Sequence Diagram
#### 2023-01-16 ~ 2023-01-18(02Day) : 개발환경 구축(Github / Git / STS / Mysql / AWS ...)


<br/>

## ▶️ 구성인원 

### 정재원(조장) : BackEnd (웹 기획,API 문서정리 , BackEnd 도서,게시판 CRUD구현)

### 이주현(조원1) : FrontEnd (FN JS, 유스케이스, 요구사항분석 구현)

### 황다은(조원2) : BackEnd (대여, 회원CRUD 페이지 구현 및 FN화면 구현)

<br/>

## ▶️ 개발 환경(플랫폼)

##### OS : WINDOW Server 2022 base
##### CPU SPEC : I7 Intel 
##### RAM SPEC : 16GB SAMSUNG DDR5
##### DISK SPEC : 512GB SSD 

<br/>

## ▶️ IDE 종류

##### IntelliJ IDEA 2023-06
<br/>

## ▶️ Software 목록

### IDE : IntelliJ IDEA 2023.2(Community Edition)
##### SpringBoot 2.7.5
##### Gradle version 8.2.1
##### Git 3.1.1
##### Mysql Server 8.0.33
##### Mysql Workbench 8.0.33
##### DbWeaver 23.1.5
##### ...
<br/>

## ▶️ DevOps 

##### Amazon Web Service EC2(Deploy Server)
##### Amazon Web Service RDS(Remote Datebase Server)
##### Git & Github
##### Docker(Server Image)
##### Swagger(API Document)
##### Adobe XD
<br/>



## ▶️ 사용(or 예정) API

##### Chat GPT API
##### Calendar API
##### Map API

<br/>

## ▶️ 기술스택

![JAVA](https://img.shields.io/badge/html5-%23E34F26.svg?style=for-the-badge&logo=html5&logoColor=white)
![CSS3](https://img.shields.io/badge/css3-%231572B6.svg?style=for-the-badge&logo=css3&logoColor=white)
![JavaScript](https://img.shields.io/badge/javascript-%23323330.svg?style=for-the-badge&logo=javascript&logoColor=%23F7DF1E)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-%23FF9900.svg?style=for-the-badge&logo=amazon-aws&logoColor=white)


[참고 배지 싸이트] <br/>
https://badgen.net/ <br/>
https://shields.io/


<br/>

## ▶️ END POINT 

|END POINT|METHOD|DESCRIPTION|
|------|---|---|
|/book/list|GET| 모든 도서 목록 표시|
|/book/write|POST|신규 도서 등|
|/book/read|GET|도서 1건 보기|
<br/>







