1)���̺� ������ ����
DROP TABLE member CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ȸ�� */
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

/**master�� �ܰ�: M: ������, Y: �α��� ����, N: �α��� �Ұ�, H: ���� ���*/

COMMENT ON TABLE member is 'ȸ��';
COMMENT ON COLUMN member.mno        is 'ȸ����ȣ';
COMMENT ON COLUMN member.email is '�̸���';
COMMENT ON COLUMN member.passwd is '��й�ȣ';
COMMENT ON COLUMN member.name is '����';
COMMENT ON COLUMN member.auth    is '����Ű';
COMMENT ON COLUMN member.tel is '��ȭ��ȣ';
COMMENT ON COLUMN member.zipcode is '�����ȣ';
COMMENT ON COLUMN member.tAddress is '�� �ּ�';
COMMENT ON COLUMN member.mAddress is '�� �ּ�';
COMMENT ON COLUMN member.bAddress is '�� �ּ�';
COMMENT ON COLUMN member.hobby is '���';
COMMENT ON COLUMN member.birth is '���� ����';
COMMENT ON COLUMN member.gen is '����';
COMMENT ON COLUMN member.push is 'Ǫ��';
COMMENT ON COLUMN member.confirm is '�̸��� Ȯ��';
COMMENT ON COLUMN member.master is '������';
COMMENT ON COLUMN member.file1 is '�̹���';
COMMENT ON COLUMN member.size1 is '������';
COMMENT ON COLUMN member.mdate is '������';
COMMENT ON COLUMN member.sound is '������';
COMMENT ON COLUMN member.mi is '���ϸ���';
COMMENT ON COLUMN member.local_code is '�����ڵ�';

2)�̸��� �ߺ� Ȯ��
SELECT COUNT(email) as cnt 
FROM member
WHERE email='test1@mail.com';

2-2) MASTER ������ ��ȸ
SELECT COUNT(mno) as cnt
FROM member
WHERE master = 'M';

3)���� ����
INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test11111@mail.com', '1234', '�մ���', 'AXD0123456789012', '000-0000-0000', '482845', '����Ư����', '������',
'���ּ�', '�౸', '1990-00-00', '��', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test122222@mail.com', '1234', '�մ���', 'AXD0123456789012', '000-0000-0000', '482845', '����Ư����', '������',
'���ּ�', '�౸', '1990-00-00', '��', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test133333@mail.com', '1234', '�մ���', 'AXD0123456789012', '000-0000-0000', '482845', '����Ư����', '������',
'���ּ�', '�౸', '1990-00-00', '��', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test144444@mail.com', '1234', '�մ���', 'AXD0123456789012', '000-0000-0000', '482845', '����Ư����', '������',
'���ּ�', '�౸', '1990-00-00', '��', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');

INSERT INTO member(mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
                              birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code)
VALUES ((SELECT NVL(MAX(mno),0)+1 as mno FROM member),
'test15555@mail.com', '1234', '�մ���', 'AXD0123456789012', '000-0000-0000', '482845', '����Ư����', '������',
'���ּ�', '�౸', '1990-00-00', '��', 'Y', 'N', 'N', 'file1', 0, sysdate, 0, 0, 'SEOJRG');



SELECT * FROM member;

4)���� ����
DELETE FROM member
WHERE mno=4;

5)���� ����
UPDATE member
SET tel='111-1111-1111'
WHERE mno=1;

6)�̸��� ����
UPDATE member
SET confirm = 'Y'
WHERE email='test1@mail.com' AND auth='AXD0123456789012';

7)ȸ�� ���
SELECT mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
            birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code
FROM member
ORDER BY mno DESC;

8)���� ����
UPDATE member 
SET  master='Y'
WHERE mno=1;

9)ȸ�� ���� ���� 
SELECT mno, email, passwd, name, auth, tel, zipcode, tAddress, mAddress, bAddress, hobby,
            birth, gen, push, confirm, master, file1, size1, mdate, sound, mi, local_code
FROM member
WHERE mno = 1;

10)�н����� ����

1.���� �н����� �˻�
SELECT count(*) as cnt
FROM member
WHERE mno = 1 AND passwd='1234';

2.�н����� ����
UPDATE member
SET passwd=''
WHERE mno=1;

11)���� Ȯ��
UPDATE member 
SET  confirm='Y'
WHERE email='gudgjs1245@naver.com' AND auth='YLZ1466922177827';

select confirm
from member
where email='gudgjs1245@naver.com';

12)�α��� �� ���ϸ��� ����
UPDATE member
SET mi=mi+1
WHERE email='gudgjs1245@naver.com'

13)ȸ�� ���� ����
UPDATE member
SET name='change', tel='000-0000-0000', zipcode='78945', 
      tAddress='5', mAddress='������', bAddress='���ּ�', hobby='����', birth='1111-11-11',
      gen='��', push='?', confirm='?', master='?', file1='����', size1='0', mi='123', local_code='test'
WHERE mno='6';

14)�н����� ����
UPDATE member
SET passwd='12345'
WHERE mno=1;

15)�н����� ��ġ �˻�
SELECT COUNT(mno) as cnt
FROM member
WHERE mno=1 AND passwd='12345';

16)���� �� ������ ó��
SELECT COUNT(email) as cnt
FROM member
WHERE email = 'test1@mail.com';

SELECT * FROM member;

17)�˻�(�����ڿ� ȸ�� ���)
SELECT mno, email, name, tel, mAddress, push, confirm, master, sound, mdate, r 
FROM(
        SELECT mno, email, name, tel, mAddress, push, confirm, master, sound, mdate, rownum as r
        FROM(
            SELECT mno, email, name, tel, mAddress, push, confirm, master, sound, mdate
            FROM member
            WHERE name LIKE '%ȫ�浿%'
            ORDER BY mno DESC
         )
 )
WHERE r >= 1 AND r <= 3;

18)�˻� ����
SELECT COUNT(*) as cnt
FROM member    
WHERE email LIKE '%test1@mail.com%'; 

    
    
    