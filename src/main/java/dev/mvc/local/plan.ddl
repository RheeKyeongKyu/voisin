DROP TABLE plan CASCADE CONSTRAINTS;
DROP TABLE groupMember CASCADE CONSTRAINTS;
DROP TABLE member CASCADE CONSTRAINTS;
DROP TABLE local_code CASCADE CONSTRAINTS;

/**********************************/
/* Table Name: ���� �ڵ� */
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
VALUES ('1', '����Ư���� ������', 'SEODBG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� �����', 'SEONWG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���ϱ�', 'SEOGBG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� �߷���', 'SEOJYG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���ϱ�', 'SEOSBG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ����', 'SEOWPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���α�', 'SEOJRG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���빮��', 'SEODDMG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ������', 'SEOGJG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ������', 'SEOSDG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� �߱�', 'SEOJG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ��걸', 'SEOYSG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ������', 'SEOMPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���빮��', 'SEOSDMG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ������', 'SEOGSG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ��������', 'SEOYDPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���α�', 'SEOGRG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���۱�', 'SEODJG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ��õ��', 'SEOGCG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���Ǳ�', 'SEOGAG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���ʱ�', 'SEOSCG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ������', 'SEOGNG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ���ı�', 'SEOSPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ������', 'SEOGDG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('1', '����Ư���� ��õ��', 'SEOYCG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('2', '��⵵ ����', 'GGDGPG');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('2', '��⵵ ����', 'GGDGYS');

INSERT INTO local_code(tAddress, mAddress, local_code)
VALUES ('2', '��⵵ ��õ��', 'GGDGCS');

DELETE FROM local_code WHERE local_code = 'SEOKBG';

delete from local_code;

    SELECT tAddress, mAddress, local_code
    FROM local_code
    WHERE mAddress='����Ư���� ������';

COMMENT ON TABLE local_code is '���� �ڵ�';
COMMENT ON COLUMN local_code.tAddress is '������/��';
COMMENT ON COLUMN local_code.mAddress is '��/��/��';
COMMENT ON COLUMN local_code.local_code is '�����ڵ�';
COMMENT ON COLUMN local_code.grpcnt is '��ȣȸ��';


/**********************************/
/* Table Name: ȸ�� */
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


/**********************************/
/* Table Name: ��ȣȸ �����ο���� */
/**********************************/
CREATE TABLE groupMember(
		membershipno                  		NUMBER(7)		 NULL 		 PRIMARY KEY,
		mno                           		NUMBER(6)		 NULL ,
  FOREIGN KEY (mno       ) REFERENCES member (mno       )
);

COMMENT ON TABLE groupMember is '��ȣȸ �����ο����';
COMMENT ON COLUMN groupMember.membershipno is '���Թ�ȣ';
COMMENT ON COLUMN groupMember.mno        is 'ȸ����ȣ';


/**********************************/
/* Table Name: ����ǥ */
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

COMMENT ON TABLE plan is '����ǥ';
COMMENT ON COLUMN plan.planno is '������ȣ';
COMMENT ON COLUMN plan.title is '��������';
COMMENT ON COLUMN plan.content is '����';
COMMENT ON COLUMN plan.pass_p is '��й�ȣ';
COMMENT ON COLUMN plan.name_p is '�۾���';
COMMENT ON COLUMN plan.cnt is '��ȸ��';
COMMENT ON COLUMN plan.file1_p is '÷������';
COMMENT ON COLUMN plan.size1_p is '����ũ��';
COMMENT ON COLUMN plan.mdate_p is '�����';
COMMENT ON COLUMN plan.mno        is 'ȸ����ȣ';
COMMENT ON COLUMN plan.clubno is '��ȣȸ ��ȣ';
COMMENT ON COLUMN plan.membershipno is '���Թ�ȣ';


