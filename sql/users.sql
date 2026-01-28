CREATE TABLE users (
    user_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) UNIQUE NOT NULL,
    password VARCHAR2(100) NOT NULL,
    role VARCHAR2(20) CHECK (role IN ('BUYER', 'SELLER')),
    security_question VARCHAR2(200),
    security_answer VARCHAR2(200),
    created_at DATE DEFAULT SYSDATE
);

CREATE SEQUENCE users_seq START WITH 1 INCREMENT BY 1;
