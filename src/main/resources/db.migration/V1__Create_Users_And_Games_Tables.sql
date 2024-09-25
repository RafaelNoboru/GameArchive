CREATE TABLE TB_JAVA_GA_USERS (
                                  id NUMBER(10) PRIMARY KEY,
                                  name VARCHAR2(255) NOT NULL,
                                  password VARCHAR2(255) NOT NULL,
                                  email VARCHAR2(255) NOT NULL UNIQUE,
                                  avatar VARCHAR2(255)
);

CREATE TABLE TB_JAVA_GA_GAMES (
                                  id_game NUMBER(10) PRIMARY KEY,
                                  name VARCHAR2(255) NOT NULL,
                                  genre VARCHAR2(100),
                                  region VARCHAR2(100),
                                  plataform VARCHAR2(100),
                                  user_id NUMBER(10),
                                  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES TB_JAVA_GA_USERS(id)
);

INSERT INTO TB_JAVA_GA_USERS (id, name, password, email, avatar) VALUES (1, 'Alice Johnson', 'password123', 'alice.johnson@example.com', 'https://example.com/avatars/alice.jpg');
INSERT INTO TB_JAVA_GA_USERS (id, name, password, email, avatar) VALUES (2, 'Bob Smith', 'securepassword', 'bob.smith@example.com', 'https://example.com/avatars/bob.jpg');
INSERT INTO TB_JAVA_GA_USERS (id, name, password, email, avatar) VALUES (3, 'Charlie Brown', 'mypassword456', 'charlie.brown@example.com', 'https://example.com/avatars/charlie.jpg');
INSERT INTO TB_JAVA_GA_USERS (id, name, password, email, avatar) VALUES (4, 'Diana Prince', 'wonderwoman!', 'diana.prince@example.com', 'https://example.com/avatars/diana.jpg');
INSERT INTO TB_JAVA_GA_USERS (id, name, password, email, avatar) VALUES (5, 'Ethan Hunt', 'topsecret123', 'ethan.hunt@example.com', 'https://example.com/avatars/ethan.jpg');


