-- DB 생성
CREATE DATABASE myportal;

-- DB 사용
USE myportal;

-- 테이블 생성
CREATE TABLE guestbook (
	no int primary key auto_increment,
    name varchar(20) NOT NULL,
    password varchar(20) NOT NULL,
    content varchar(255) NOT NULL,
    regdate datetime NOT NULL DEFAULT now()
);

-- 샘플 데이터 입력
insert into guestbook (name, password, content)
values ('방문자', 'test', '테스트 방명록입니다.');

-- 목록 쿼리
SELECT no, name, password, content FROM guestbook ORDER BY regdate DESC;

-- 사용자 생성
CREATE USER 'webdb'@'localhost' IDENTIFIED BY 'webdb';

-- 권한 부여
GRANT ALL PRIVILEGES ON myportal.* 
	TO 'webdb'@'localhost';