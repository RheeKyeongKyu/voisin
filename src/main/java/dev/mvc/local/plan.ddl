DROP TABLE plan CASCADE CONSTRAINTS;
DROP TABLE groupMember CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE local_code CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 지역 코드 */
/**********************************/
CREATE TABLE local_code(
		tAddress                      		VARCHAR2(50)		 NOT NULL,
		mAddress                      		VARCHAR2(50)		 NOT NULL,
		local_code                    		VARCHAR2(10)		 NOT NULL		 PRIMARY KEY,
		grpcnt                        		NUMBER(4)		 DEFAULT 0		 NULL 
);

select * from local_code; 
28
INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 도봉구', 'SEODBG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 노원구', 'SEONWG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 강북구', 'SEOGBG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 중량구', 'SEOJYG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 성북구', 'SEOSBG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 은평구', 'SEOWPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 종로구', 'SEOJRG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 동대문구', 'SEODDMG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 광진구', 'SEOGJG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 성동구', 'SEOSDG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 중구', 'SEOJG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 용산구', 'SEOYSG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 마포구', 'SEOMPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 서대문구', 'SEOSDMG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 강서구', 'SEOGSG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 영등포구', 'SEOYDPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 구로구', 'SEOGRG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 동작구', 'SEODJG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 금천구', 'SEOGCG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 관악구', 'SEOGAG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 서초구', 'SEOSCG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 강남구', 'SEOGNG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 송파구', 'SEOSPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 강동구', 'SEOGDG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '서울특별시 양천구', 'SEOYCG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('2', '경기도 가평군', 'GGDGPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('2', '경기도 고양시', 'GGDGYS');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('2', '경기도 과천시', 'GGDGCS');

DELETE FROM local_code WHERE local_code = 'SEOKBG';

delete from local_code;

    SELECT tAddress, mAddress, local_code
    FROM local_code
    WHERE mAddress='서울특별시 도봉구';

COMMENT ON TABLE local_code is '지역 코드';
COMMENT ON COLUMN local_code.tAddress is '광역시/도';
COMMENT ON COLUMN local_code.mAddress is '시/군/구';
COMMENT ON COLUMN local_code.local_code is '지역코드';
COMMENT ON COLUMN local_code.grpcnt is '동호회수';


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
		mi                            		NUMBER(6)		 DEFAULT 0		 NOT NULL,
		local_code                    		VARCHAR2(10)		 NULL ,
  FOREIGN KEY (local_code) REFERENCES local_code (local_code)
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
COMMENT ON COLUMN member.local_code is '지역코드';


/**********************************/
/* Table Name: 동호회 가입인원목록 */
/**********************************/
CREATE TABLE groupMember(
		membershipno                  		NUMBER(7)		 NULL 		 PRIMARY KEY,
		mno                           		NUMBER(6)		 NULL ,
  FOREIGN KEY (mno       ) REFERENCES member (mno       )
);

COMMENT ON TABLE groupMember is '동호회 가입인원목록';
COMMENT ON COLUMN groupMember.membershipno is '가입번호';
COMMENT ON COLUMN groupMember.mno        is '회원번호';


/**********************************/
/* Table Name: 일정표 */
/**********************************/
CREATE TABLE plan(
		planno                        		NUMBER(6)		 NOT NULL		 PRIMARY KEY,
		title                         		VARCHAR2(200)		 NOT NULL,
		content                       		VARCHAR2(4000)		 NOT NULL,
		pass_p                        		VARCHAR2(15)		 NOT NULL,
		name_p                        		VARCHAR2(20)		 NOT NULL,
		cnt                           		NUMBER(6)		 DEFAULT 0		 NULL ,
		file1_p                       		VARCHAR2(100)		 NULL ,
		size1_p                       		NUMBER(9)		 NULL ,
		mdate_p                       		DATE		 NOT NULL,
		mno                           		NUMBER(6)		 NULL ,
		clubno                        		NUMBER(6)		 NULL ,
		membershipno                  		NUMBER(7)		 NULL ,
  FOREIGN KEY (mno       ) REFERENCES member (mno       ),
  FOREIGN KEY (membershipno) REFERENCES groupMember (membershipno)
);

COMMENT ON TABLE plan is '일정표';
COMMENT ON COLUMN plan.planno is '일정번호';
COMMENT ON COLUMN plan.title is '일정제목';
COMMENT ON COLUMN plan.content is '내용';
COMMENT ON COLUMN plan.pass_p is '비밀번호';
COMMENT ON COLUMN plan.name_p is '글쓴이';
COMMENT ON COLUMN plan.cnt is '조회수';
COMMENT ON COLUMN plan.file1_p is '첨부파일';
COMMENT ON COLUMN plan.size1_p is '파일크기';
COMMENT ON COLUMN plan.mdate_p is '등록일';
COMMENT ON COLUMN plan.mno        is '회원번호';
COMMENT ON COLUMN plan.clubno is '동호회 번호';
COMMENT ON COLUMN plan.membershipno is '가입번호';


