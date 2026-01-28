CREATE TABLE reviews (
    review_id NUMBER PRIMARY KEY,
    product_id NUMBER,
    buyer_email VARCHAR2(100),
    rating NUMBER CHECK (rating BETWEEN 1 AND 5),
    comment VARCHAR2(300),
    created_at DATE DEFAULT SYSDATE
);

CREATE SEQUENCE reviews_seq START WITH 1 INCREMENT BY 1;
