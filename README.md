# 📅 Schedule Management API
Spring Boot와 Spring Data JPA를 활용하여
유저, 일정, 댓글 도메인을 중심으로 CRUD 기능을 구현하고,
Cookie/Session 기반 인증과 JPA 연관관계를 단계적으로 적용한 RESTful API 프로젝트

본 프로젝트는 **3-Layer Architecture** 기반으로 설계되었으며,
**JPA Auditing**을 활용하여 작성일/수정일을 자동 관리합니다.

### 📄 API 명세서
https://documenter.getpostman.com/view/50422128/2sBXVeGYKY

### 🗂 ERD
<img width="789" height="511" alt="image" src="https://github.com/user-attachments/assets/52a76778-31ce-4521-830e-2f2c121e146a" />

### 설계 구조

- **3-Layer Architecture**
  - Controller → Service → Repository 
- Entity와 DTO를 분리하여 설계
- 비즈니스 로직은 Service 계층에서 처리
- Controller는 요청/응답 책임만 가짐

### 주요 기능
#### + 일정 (Schedule)
+ 일정 생성 / 전체 조회 / 단건 조회 / 수정 / 삭제
+ 로그인한 유저만 일정 작성 가능
+ 작성일 / 수정일 JPA Auditing 자동 관리
  
#### + 유저 (User)
+ 회원가입 / 조회 / 수정 / 삭제
+ 비밀번호 8자 이상 30자 이하 제한
+ BCrypt 기반 비밀번호 암호화

#### + 인증 (Authentication)
+ 이메일 + 비밀번호 로그인
+ Cookie / Session 기반 인증
+ 로그인 시 세션에 로그인 유저 정보 저장
+ 로그아웃 시 세션 무효화

#### + 댓글 (Comment)
+ 일정에 댓글 생성 및 조회
+ 댓글 -> 일정 단방향 연관관계
+ 댓글 작성자는 로그인 유저로 자동 지정

### 연관관계
+ Schedule → User : ManyToOne
+ Comment → Schedule : ManyToOne
+ Comment → User : ManyToOne
+ 모든 연관관계는 단방향으로 설계

### 예외처리
+ Bean Validation (@Valid)를 활용한 요청 데이터 검증
+ @RestControllerAdvice를 활용한 공통 예외 처리
+ 잘못된 요청 시 400 Bad Request 반환

### 인증 처리 방식
+ 로그인 성공 시 HttpSession에 로그인 유저 정보 저장
+ 이후 일정/댓글 생성 시 Session에서 유저 정보 추출

### 📂 프로젝트 구조 
```
src/main/java
└─ com.popo2381.schedule
├── auth
│   ├── controller
│   ├── dto
│   └── service
├── user
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── schedule
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── comment
│   ├── controller
│   ├── dto
│   ├── entity
│   ├── repository
│   └── service
├── common
│   └── entity
├── config
├── exception
└── ScheduleApplication
```







 











