create tablespace safe_tablespace datafile 'safe_datafile.dbf'
size                                  400M
autoextend on maxsize                600M
extent management local uniform size  64K;


CREATE TABLE SAFE_USER.Users (
  user_id NUMBER,
  email VARCHAR(256) NOT NULL,
  display_name VARCHAR2(50) NOT NULL,
  password VARCHAR2(255) NOT NULL
);

ALTER TABLE SAFE_USER.users ADD (
  CONSTRAINT USER_pk PRIMARY KEY (user_id),
  CONSTRAINT USER_email_uk UNIQUE (email)
 );

CREATE SEQUENCE users_seq START WITH 1;


CREATE TABLE SAFE_USER.profiles (
  profile_id NUMBER,
  display_name VARCHAR(50) NOT NULL,
  natural_key VARCHAR2(50) NOT NULL
);

ALTER TABLE SAFE_USER.profiles ADD (
  CONSTRAINT profile_pk PRIMARY KEY (profile_id),
  CONSTRAINT profile_natural_key_pk UNIQUE (natural_key),
  CONSTRAINT profile_display_name_pk UNIQUE (display_name)
 );

CREATE SEQUENCE profile_seq START WITH 1;


CREATE TABLE SAFE_USER.users_profiles (
  user_profile_id NUMBER,
  users_fk NUMBER,
   profiles_fk NUMBER
);

ALTER TABLE SAFE_USER.profiles ADD (
  CONSTRAINT user_profile_id_pk PRIMARY KEY (user_profile_id)
);

ALTER TABLE SAFE_USER.users_profiles ADD (
  CONSTRAINT fk_users_profiles_users
  FOREIGN KEY (users_fk)
  REFERENCES users (user_id)
);


ALTER TABLE SAFE_USER.users_profiles ADD (
  CONSTRAINT fk_users_profiles_profiles
  FOREIGN KEY (profiles_fk)
  REFERENCES profiles (profile_id)
);

CREATE SEQUENCE user_profile_seq START WITH 1;
/************ PROCEDURE *****************/
create or replace PROCEDURE	users_insert(
	   p_EMAIL IN users.EMAIL%TYPE,
	   p_DISPLAY_NAME IN users.DISPLAY_NAME%TYPE,
	   p_PASSWORD IN users.PASSWORD%TYPE,
       o_USER_ID OUT users.user_id%TYPE)
as
seq_id number;
BEGIN

    seq_id := SAFE_USER.USERS_SEQ.NEXTVAL;
    INSERT INTO USERS ("USER_ID", "EMAIL", "DISPLAY_NAME", "PASSWORD") 
    VALUES (seq_id, p_email, p_display_name, p_PASSWORD);

    COMMIT;
    
    o_user_id := seq_id;
END;

CREATE OR REPLACE PROCEDURE users_by_id(
    p_id IN users.user_id%TYPE, 
    o_cursor OUT SYS_REFCURSOR)
AS

BEGIN

OPEN o_cursor FOR
      SELECT *   
      FROM users WHERE user_id = p_id; 
END;
/***************************************/

CALL SAFE_USER.USERS_INSERT('alba.rosales@gmail.com','Alba Rosales','1234qwer');
CALL SAFE_USER.USERS_INSERT('fito.paez@gmail.com','Fito Paez','1234qwer');
CALL SAFE_USER.USERS_INSERT('patricia.rodriguez@gmail.com','Patricia Rodriguez','1234qwer');
CALL SAFE_USER.USERS_INSERT('jorge.gonzales@gmail.com','Jorge Gonzales','1234qwer');
CALL SAFE_USER.USERS_INSERT('alfredo.sfeir@gmail.com','Alfredo Sfeir','1234qwer');