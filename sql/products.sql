CREATE TABLE products (
    product_id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    description VARCHAR2(300),
    price NUMBER(10,2) NOT NULL,
    mrp NUMBER(10,2),
    category VARCHAR2(50),
    stock NUMBER DEFAULT 0,
    seller_email VARCHAR2(100),
    created_at DATE DEFAULT SYSDATE
);

CREATE SEQUENCE products_seq START WITH 1 INCREMENT BY 1;
