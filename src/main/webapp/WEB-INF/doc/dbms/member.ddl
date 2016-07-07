DROP TABLE member CASCADE CONSTRAINTS;

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
		mi                            		NUMBER(6)		 DEFAULT 0		 NOT NULL
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


