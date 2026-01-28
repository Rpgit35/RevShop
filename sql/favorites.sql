CREATE TABLE favorites (
    fav_id NUMBER PRIMARY KEY,
    buyer_email VARCHAR2(100),
    product_id NUMBER
);

CREATE SEQUENCE favorites_seq START WITH 1 INCREMENT BY 1;
