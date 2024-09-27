CREATE TABLE TB_JAVA_GA_USERS (
                                  id NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                  name VARCHAR2(255) NOT NULL,
                                  password VARCHAR2(255) NOT NULL,
                                  email VARCHAR2(255) NOT NULL UNIQUE,
                                  avatar VARCHAR2(255)
);


