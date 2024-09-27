CREATE TABLE TB_JAVA_GA_GAMES (
                                  id_game NUMBER(10) GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                  name VARCHAR2(255) NOT NULL,
                                  genre VARCHAR2(100),
                                  region VARCHAR2(100),
                                  plataform VARCHAR2(100),
                                  user_id NUMBER(10),
                                  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES TB_JAVA_GA_USERS(id)
);

