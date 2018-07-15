CREATE TABLE E_USER
(
  USER_ID           NUMBER           NOT NULL,
  USERNAME         VARCHAR2(500)    NOT NULL,
  PASSWORD          VARCHAR2(500)    NOT NULL,
  CREATED_DATE      DATE            DEFAULT SYSDATE,
  CONSTRAINT E_USER_PK PRIMARY KEY (USER_ID)
);

CREATE SEQUENCE USER_SEQ START WITH 1 INCREMENT BY 1;


CREATE TABLE E_SEARCH_HISTORY
(
  SEARCH_ID         NUMBER          NOT NULL,
  USER_ID           NUMBER          NOT NULL,
  SEARCH_DATA       VARCHAR(500),
  RESULT            VARCHAR(2000),
  CREATED_DATE      DATE            DEFAULT SYSDATE,
  CONSTRAINT E_SEARCH_HISTORY_PK PRIMARY KEY (SEARCH_ID)
);

CREATE SEQUENCE SEARCH_HISTORY_SEQ START WITH 1 INCREMENT BY 1;


CREATE TABLE E_BOOKMARK
(
  BOOKMARK_ID       NUMBER          NOT NULL,
  USER_ID           NUMBER          NOT NULL,
  BOOK_ID           NUMBER          NOT NULL,
  URI               VARCHAR2(1000)  NOT NULL,
  CREATED_DATE      DATE            DEFAULT SYSDATE,
  DELETED_DATE      DATE,
  CONSTRAINT E_BOOKMARK_PK PRIMARY KEY (BOOKMARK_ID)
);

CREATE SEQUENCE E_BOOKMARK_SEQ START WITH 1 INCREMENT BY 1;

commit;