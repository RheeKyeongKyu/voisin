DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
		mno                           		NUMBER(6)		 NOT NULL		 PRIMARY KEY,
		email                         		VARCHAR2(50)		 NOT NULL,
		passwd                        		VARCHAR2(20)		 NOT NULL,
		name                          		VARCHAR2(20)		 NOT NULL,
		auth                          		VARCHAR2(20)		 NOT NULL,
		tel                           		VARCHAR2(14)		 NOT NULL,
		zipcode                       		VARCHAR2(9)		 NULL ,
		tAddress                      		VARCHAR2(50)		 NOT NULL,
		mAddress                      		VARCHAR2(50)		 NOT NULL,
		bAddress                      		VARCHAR2(80)		 NOT NULL,
		hobby                         		VARCHAR2(30)		 NULL ,
		birth                         		VARCHAR2(12)		 NOT NULL,
		gen                           		VARCHAR2(5)		 NOT NULL,
		push                          		CHAR(1)		 DEFAULT 'Y'		 NOT NULL,
		confirm                       		CHAR(1)		 DEFAULT 'N'		 NOT NULL,
		master                        		CHAR(1)		 DEFAULT 'N'		 NOT NULL,
		file1                         		VARCHAR2(100)		 NULL ,
		size1                         		NUMBER(9)		 DEFAULT 0		 NULL ,
		mdate                         		DATE		 NOT NULL,
		sound                         		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		mi                            		NUMBER(6)		 DEFAULT 0		 NOT NULL
);

COMMENT ON TABLE member is '회원';
COMMENT ON COLUMN member.mno        is '회원번호';
COMMENT ON COLUMN member.email is '이메일';
COMMENT ON COLUMN member.passwd is '비밀번호';
COMMENT ON COLUMN member.name is '성명';
COMMENT ON COLUMN member.auth    is '가입키';
COMMENT ON COLUMN member.tel is '전화번호';
COMMENT ON COLUMN member.zipcode is '우편번호';
COMMENT ON COLUMN member.tAddress is '시 주소';
COMMENT ON COLUMN member.mAddress is '구 주소';
COMMENT ON COLUMN member.bAddress is '상세 주소';
COMMENT ON COLUMN member.hobby is '취미';
COMMENT ON COLUMN member.birth is '생년 월일';
COMMENT ON COLUMN member.gen is '성별';
COMMENT ON COLUMN member.push is '푸쉬';
COMMENT ON COLUMN member.confirm is '이메일 확인';
COMMENT ON COLUMN member.master is '관리자';
COMMENT ON COLUMN member.file1 is '이미지';
COMMENT ON COLUMN member.size1 is '사이즈';
COMMENT ON COLUMN member.mdate is '가입일';
COMMENT ON COLUMN member.sound is '소음량';
COMMENT ON COLUMN member.mi is '마일리지';


