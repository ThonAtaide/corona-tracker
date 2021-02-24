CREATE TABLE USER_TABLE (
    ID BIGSERIAL PRIMARY KEY,
    USERNAME VARCHAR(32),
    PASSWORD VARCHAR(32),
    ROLE     VARCHAR(100)
);

CREATE TABLE PERSON (
    ID BIGSERIAL PRIMARY KEY,
    NAME VARCHAR(100),
    PHONE VARCHAR(11),
    USER_ID BIGSERIAL,
    FOREIGN KEY (USER_ID) REFERENCES USER_TABLE(ID)
);

CREATE TABLE STORE(
  ID BIGSERIAL PRIMARY KEY,
  NAME VARCHAR(200),
  CNPJ VARCHAR(14),
  CEP  VARCHAR(8),
  USER_ID BIGSERIAL,
  FOREIGN KEY (USER_ID) REFERENCES USER_TABLE(ID)
);

CREATE TABLE HEALTH_UNITY (
  ID BIGSERIAL PRIMARY KEY,
  NAME VARCHAR(100),
  USER_ID BIGSERIAL,
  CEP  VARCHAR(8),
  ZONE VARCHAR(200),
  FOREIGN KEY (USER_ID) REFERENCES USER_TABLE(ID)
);

CREATE TABLE DIAGNOSED (
   ID BIGSERIAL PRIMARY KEY,
   NAME VARCHAR(100),
   PHONE VARCHAR(11),
   ESTIMATED_DAY_ZERO DATE,
   CREATED_AT DATE
);

CREATE TABLE VISITED_STORE (
    ID BIGSERIAL PRIMARY KEY,
    STORE BIGSERIAL NOT NULL,
    NAME VARCHAR(100),
    PHONE VARCHAR(11),
    CREATED_AT DATE,
    FOREIGN KEY (STORE) REFERENCES STORE(ID)
);