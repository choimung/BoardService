<div align="center"">
    <h2>📌 스프링MVC를 활용한 게시판 서비스 만들어보기 </h2>  
</div>

<br>

## 📃 프로젝트 소개

**김영한님의 강의를 수강하고 배운내용을 토대로 스프링 MVC를 활용하여 게시판 서비스를 구현해보자 계획하였습니다.**

- 학습한 스프링의 기본 기능을 최대한 활용하여 게시판 서비스를 구현해보았습니다.

- DB 기술은 메모리부터 시작하여 Jdbc 부터 Jpa 까지 점진적으로 여러 기술을 적용해보았습니다..

<br>

## 🛠 개발 환경
- `Intellij 2023.2.3`
- `java 18`
- `Gradle 8.5`
- `Spring boot 3.2.2`

<br>

## 📁 프로젝트 기능

### [🖥 시스템]
- 필터와 인터셉트
  - 현재는 필터와 인터셉트를 활용하고 있으나, 보다 강력한 보안을 위해 Spring Security로 전환할 예정입니다.
<br>

### [🙋사용자]

- 회원가입
  - 사용자는 회원가입을 할 수 있습니다.
  - 중복된 회원가입을 방지할 수 있는 유효성 검사 기능
  
- 로그인
  - 사용자는 로그인을 할 수 있습니다.
  - 부적절한 사용자의 로그인 방지할 수 있는 중복 및 유효성 검사 기능

- 회원수정
  - 사용자는 회원정보를 수정 할 수 있습니다.

- 포스트 CURD
  - 사용자는 글을 작성, 수정, 조회, 삭제 할 수 있습니다.

<br>

### [✏게시판]

- **CURD 기능**
  - 게시물의 생성(Create), 조회(Read), 수정(Update), 삭제(Delete) 기능 제공

- **검색 기능 개선**
  - 게시물 제목 또는 내용을 기준으로 검색할 수 있습니다.

- **이미지 업로드 및 표시**
  - 게시물에 이미지 업로드를 할 수 있습니다.
  - 업로드된 이미지는 게시물 내에서 썸네일 형태로 표시됩니다.
  
- **조회수 표시**
  - 게시글 조회수를 표시하였습니다.

- **페이징 기능**
  - 페이징 기능을 추가할 예정입니다.

<br>

## 📃 API 명세서

| Description | EndPoint | Path Variable | Method | Controller |
| :--- | :---: | :---: | :---: | :---: |
| 회원가입 페이지 | /users/signup |  | GET | UserController |
| 회원가입 기능| /users/signup |  | POST | UserController |
| 로그인 페이지 | /users/login |  | GET | UserController |
| 로그인 기능| /users/login |  | POST | UserController |
| 프로필 이미지조회 | /user/profile/{filename} | {filename} - 파일 이름 | GET | ImageController |
| 게시물 목록조회 | /posts |  | GET |  |
| 회원수정 페이지 | /users/edit |  | GET |  |
| 회원수정 | /users/edit |  | POST |  |
| 게시물 조회 | /posts/{postId} | {postId} - 조회할 게시물의 id | GET |  |
| 게시물 등록 | /posts/add |  | POST |  |
| 게시물 수정 | /posts/postId/edit | {postId} - 수정할 게시물의 id | PUT |  |
| 게시물 삭제 | /posts/postId/delete | {postId} - 삭제할 게시물의 id | DELETE |  |

<br>

## 💥프로젝트 개발중 발생했던 에러

`java.lang.IllegalStateException: ApplicationContext failure threshold (1) exceeded:`
```
drop table if exists member CASCADE; -> 구절 추가시 오류해결
CREATE TABLE member
(
    id       bigint generated by default as identity,
    loginId  varchar(20),
    password varchar(20),
    name     varchar(20),
    grade    varchar(20),
    image    varchar(255),
    primary key (id)
)
```
```
drop table if exists posts CASCADE;
CREATE TABLE posts
(
    id       bigint generated by default as identity,
    title varchar(20),
    author varchar(20),
    image     varchar(255),
    content    varchar(20),
    views    bigint,
    primary key (id)
)
```
```
`Error creating bean with name 'memberMapper' defined in file `
```
test -> resource -> application.properties
mybatis.type-aliases-package=com.boardservice.domain //추가
```
