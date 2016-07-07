1)테이블 생성과 삭제
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: 회원 */
/**********************************/
CREATE TABLE member(
    mno                               NUMBER(6)    NOT NULL    PRIMARY KEY,
    email                             VARCHAR2(50)     NOT NULL,
    passwd                            VARCHAR2(20)     NOT NULL,
    name                              VARCHAR2(20)     NOT NULL,
    auth                              VARCHAR2(20)     NOT NULL,
    tel                               VARCHAR2(14)     NOT NULL,
    zipcode                           VARCHAR2(9)    NULL ,
    tAddress                          VARCHAR2(50)     NOT NULL,
    mAddress                          VARCHAR2(50)     NOT NULL,
    bAddress                          VARCHAR2(80)     NOT NULL,
    hobby                             VARCHAR2(30)     NULL ,
    birth                             VARCHAR2(12)     NOT NULL,
    gen                               VARCHAR2(5)    NOT NULL,
    push                              CHAR(1)    DEFAULT 'Y'     NOT NULL,
    confirm                           CHAR(1)    DEFAULT 'N'     NOT NULL,
    master                            CHAR(1)    DEFAULT 'N'     NOT NULL,
    file1                             VARCHAR2(100)    NULL ,
    size1                             NUMBER(9)    DEFAULT 0     NULL ,
    mdate                             DATE     NOT NULL,
    sound                             NUMBER(6)    DEFAULT 0     NOT NULL,
    mi                                NUMBER(6)    DEFAULT 0     NOT NULL,
    local_code                        VARCHAR2(10)     NULL ,
  FOREIGN KEY (local_code) REFERENCES local_code (local_code)
);

/**master의 단계: M: 마스터, Y: 로그인 가능, N: 로그인 불가, H: 인증 대기*/

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

2)이메일 중복 확인
SELECT COUNT(email) as cnt 
FROM member
WHERE email='test1@mail.com';

2-2) MASTER 계정의 조회
SELECT COUNT(mno) as cnt
FROM member
WHERE master = 'M';

3)계정 생성
INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test11111@mail.com', '1234', '왕눈이', 'AXD0123456789012', '000-0000-0000', '482845', '서울특별시', '광진구',
'상세주소', '축구', '1990-00-00', '남', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test122222@mail.com', '1234', '왕눈이', 'AXD0123456789012', '000-0000-0000', '482845', '서울특별시', '광진구',
'상세주소', '축구', '1990-00-00', '남', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test133333@mail.com', '1234', '왕눈이', 'AXD0123456789012', '000-0000-0000', '482845', '서울특별시', '광진구',
'상세주소', '축구', '1990-00-00', '남', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test144444@mail.com', '1234', '왕눈이', 'AXD0123456789012', '000-0000-0000', '482845', '서울특별시', '광진구',
'상세주소', '축구', '1990-00-00', '남', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test15555@mail.com', '1234', '왕눈이', 'AXD0123456789012', '000-0000-0000', '482845', '서울특별시', '광진구',
'상세주소', '축구', '1990-00-00', '남', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');



SELECT * FROM member;

4)계정 삭제
DELETE FROM member
WHERE mno=4;

5)계정 수정
UPDATE member
SET tel='111-1111-1111'
WHERE mno=1;

6)이메일 인증
UPDATE member
SET confirm = 'Y'
WHERE email='test1@mail.com' AND auth='AXD0123456789012';

7)회원 목록
SELECT mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
            birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code
FROM member
ORDER BY mno DESC;

8)권한 변경
UPDATE member 
SET  master='Y'
WHERE mno=1;

9)회원 정보 보기 
SELECT mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
            birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code
FROM member
WHERE mno = 1;

10)패스워드 변경

1.기존 패스워드 검사
SELECT count(*) as cnt
FROM member
WHERE mno = 1 AND passwd='1234';

2.패스워드 변경
UPDATE member
SET passwd=''
WHERE mno=1;

11)인증 확인
UPDATE member 
SET  confirm='Y'
WHERE email='gudgjs1245@naver.com' AND auth='YLZ1466922177827';

select confirm
from member
where email='gudgjs1245@naver.com';

12)로그인 시 마일리지 적립
UPDATE member
SET mi=mi+1
WHERE email='gudgjs1245@naver.com'

13)회원 정보 변경
UPDATE member
SET name='change', tel='000-0000-0000', zipcode='78945', 
      tAddress='5', mAddress='강원도', bAddress='상세주소', hobby='변경', birth='1111-11-11',
      gen='중', push='?', confirm='?', master='?', file1='변경', size1='0', mi='123', local_code='test'
WHERE mno='6';

14)패스워드 변경
UPDATE member
SET passwd='12345'
WHERE mno=1;

15)패스워드 일치 검사
SELECT COUNT(mno) as cnt
FROM member
WHERE mno=1 AND passwd='12345';

16)삭제 시 관리자 처리
SELECT COUNT(email) as cnt
FROM member
WHERE email = 'test1@mail.com';

SELECT * FROM member;

17)검색(관리자용 회원 목록)
SELECT mno, email, name, tel, mAddress, push, confirm, master, sound, mdate, r 
FROM(
        SELECT mno, email, name, tel, mAddress, push, confirm, master, sound, mdate, rownum as r
        FROM(
            SELECT mno, email, name, tel, mAddress, push, confirm, master, sound, mdate
            FROM member
            WHERE name LIKE '%홍길동%'
            ORDER BY mno DESC
         )
 )
WHERE r >= 1 AND r <= 3;

18)검색 갯수
SELECT COUNT(*) as cnt
FROM member    
WHERE email LIKE '%test1@mail.com%'; 

    
    
    