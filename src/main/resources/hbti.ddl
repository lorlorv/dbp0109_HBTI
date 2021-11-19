
CREATE SEQUENCE challenge_seq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE group_seq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE hbti_seq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE list_seq
	INCREMENT BY 1
	START WITH 1;

CREATE SEQUENCE todo_seq
	INCREMENT BY 1
	START WITH 1;

DROP TABLE ChallengePost CASCADE CONSTRAINTS PURGE;

DROP TABLE Todo CASCADE CONSTRAINTS PURGE;

DROP TABLE GroupInfo CASCADE CONSTRAINTS PURGE;

DROP TABLE UserInfo CASCADE CONSTRAINTS PURGE;

DROP TABLE DayOfChallenge CASCADE CONSTRAINTS PURGE;

DROP TABLE HBTI CASCADE CONSTRAINTS PURGE;

DROP TABLE Challenge CASCADE CONSTRAINTS PURGE;

CREATE TABLE Challenge
(
	challenge_id         NUMBER(4) NOT NULL ,
	content              VARCHAR2(1000) NULL 
);

ALTER TABLE Challenge
	ADD CONSTRAINT  XPKChallenge PRIMARY KEY (challenge_id);

CREATE TABLE HBTI
(
	name                 VARCHAR2(50) NULL ,
	hbti_id              NUMBER(4) NOT NULL ,
	bad_descr            VARCHAR2(1000) NOT NULL ,
	goodHBTI             NUMBER(4) NULL ,
	badHBTI              NUMBER(4) NULL ,
	icon                 VARCHAR2(50) NULL ,
	good_descr           VARCHAR2(1000) NOT NULL ,
	exercise             VARCHAR2(100) NULL 
);

ALTER TABLE HBTI
	ADD CONSTRAINT  XPKHBTI PRIMARY KEY (hbti_id);

CREATE TABLE DayOfChallenge
(
	challenge_id         NUMBER(4) NOT NULL ,
	hbti_id              NUMBER(4) NOT NULL 
);

ALTER TABLE DayOfChallenge
	ADD CONSTRAINT  XPKDayOfChallenge PRIMARY KEY (hbti_id);

CREATE TABLE GroupInfo
(
	group_id             NUMBER(4) NOT NULL ,
	name                 VARCHAR2(50) NULL ,
	create_date          DATE DEFAULT  SYSDATE  NULL ,
	icon                 VARCHAR2(50) NULL ,
	descr                VARCHAR2(100) NULL ,
	hbti_id              NUMBER(4) NOT NULL ,
	leader_id            VARCHAR2(15) NOT NULL ,
	limitation           NUMBER(2) NULL 
);

ALTER TABLE GroupInfo
	ADD CONSTRAINT  XPKGroupInfo PRIMARY KEY (group_id);

CREATE TABLE UserInfo
(
	user_id              VARCHAR2(15) NOT NULL ,
	password             VARCHAR2(20) NOT NULL ,
	name                 VARCHAR2(50) NOT NULL ,
	descr                VARCHAR2(100) NULL ,
	image                VARCHAR2(50) NULL ,
	group_id             NUMBER(4) NULL ,
	hbti_id              NUMBER(4) NULL ,
	login_date           DATE NULL 
);

ALTER TABLE UserInfo
	ADD CONSTRAINT  XPKUserInfo PRIMARY KEY (user_id);

CREATE TABLE ChallengePost
(
	post_id              NUMBER(4) NOT NULL ,
	write_date           DATE DEFAULT  SYSDATE  NOT NULL ,
	content              VARCHAR2(1000) NULL ,
	image                VARCHAR2(50) NULL ,
	like_btn             NUMBER(4) NULL ,
	group_id             NUMBER(4) NOT NULL ,
	writer_id            VARCHAR2(15) NOT NULL 
);

ALTER TABLE ChallengePost
	ADD CONSTRAINT  XPKChallengePost PRIMARY KEY (post_id);

CREATE TABLE Todo
(
	todo_id              NUMBER(4) NOT NULL ,
	content              VARCHAR2(50) NULL ,
	todo_date            DATE DEFAULT  SYSDATE  NULL ,
	user_id              VARCHAR2(15) NOT NULL ,
	is_done              NUMBER(1) NULL  CONSTRAINT  torf CHECK (is_done IN (0, 1))
);

ALTER TABLE Todo
	ADD CONSTRAINT  XPKTodo PRIMARY KEY (todo_id);

ALTER TABLE HBTI
	ADD (CONSTRAINT Recommended FOREIGN KEY (goodHBTI) REFERENCES HBTI (hbti_id));

ALTER TABLE HBTI
	ADD (CONSTRAINT Not_recommended FOREIGN KEY (badHBTI) REFERENCES HBTI (hbti_id));

ALTER TABLE DayOfChallenge
	ADD (CONSTRAINT Assignment FOREIGN KEY (challenge_id) REFERENCES Challenge (challenge_id) ON DELETE SET NULL);

ALTER TABLE DayOfChallenge
	ADD (CONSTRAINT Assignment FOREIGN KEY (hbti_id) REFERENCES HBTI (hbti_id));

ALTER TABLE GroupInfo
	ADD (CONSTRAINT belong_to_HBTI FOREIGN KEY (hbti_id) REFERENCES HBTI (hbti_id));

ALTER TABLE GroupInfo
	ADD (CONSTRAINT leading FOREIGN KEY (leader_id) REFERENCES UserInfo (user_id));

ALTER TABLE UserInfo
	ADD (CONSTRAINT Include FOREIGN KEY (group_id) REFERENCES GroupInfo (group_id) ON DELETE SET NULL);

ALTER TABLE UserInfo
	ADD (CONSTRAINT Assignment FOREIGN KEY (hbti_id) REFERENCES HBTI (hbti_id));

ALTER TABLE ChallengePost
	ADD (CONSTRAINT Have FOREIGN KEY (group_id) REFERENCES GroupInfo (group_id));

ALTER TABLE ChallengePost
	ADD (CONSTRAINT Complete FOREIGN KEY (writer_id) REFERENCES UserInfo (user_id));

ALTER TABLE Todo
	ADD (CONSTRAINT Write FOREIGN KEY (user_id) REFERENCES UserInfo (user_id));
