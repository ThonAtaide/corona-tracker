CREATE TABLE USER_TABLE (
    ID       BIGINT PRIMARY KEY,
    USERNAME VARCHAR(32) UNIQUE,
    PASSWORD VARCHAR(100),
    ROLE     VARCHAR(100)
);

CREATE TABLE PERSON (
    ID      BIGINT PRIMARY KEY,
    NAME    VARCHAR(100),
    PHONE   VARCHAR(11),
    USER_ID BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USER_TABLE (ID)
);

CREATE TABLE STORE (
    ID      BIGINT PRIMARY KEY,
    NAME    VARCHAR(200),
    PHONE   VARCHAR(11),
    CNPJ    VARCHAR(14),
    CEP     VARCHAR(8),
    USER_ID BIGINT,
    FOREIGN KEY (USER_ID) REFERENCES USER_TABLE (ID)
);

CREATE TABLE HEALTH_UNITY
(
    ID      BIGINT PRIMARY KEY,
    NAME    VARCHAR(100),
    USER_ID BIGINT,
    CEP     VARCHAR(8),
    ZONE    VARCHAR(200),
    FOREIGN KEY (USER_ID) REFERENCES USER_TABLE (ID)
);

CREATE TABLE DIAGNOSED (
    ID                 BIGINT PRIMARY KEY,
    NAME               VARCHAR(100),
    PHONE              VARCHAR(11),
    ESTIMATED_DAY_ZERO TIMESTAMP,
    CREATED_AT         TIMESTAMP,
    HEALTH_UNITY_ID    BIGINT,
    FOREIGN KEY (HEALTH_UNITY_ID) REFERENCES HEALTH_UNITY (ID)
);

CREATE TABLE VISITED_STORE (
    ID         BIGINT PRIMARY KEY,
    STORE_ID   BIGINT NOT NULL,
    NAME       VARCHAR(100),
    PHONE      VARCHAR(11),
    CREATED_AT TIMESTAMP,
    FOREIGN KEY (STORE_ID) REFERENCES STORE (ID)
);