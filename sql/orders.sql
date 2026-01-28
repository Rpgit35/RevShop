CREATE TABLE orders (
    order_id NUMBER PRIMARY KEY,
    buyer_email VARCHAR2(100),
    order_date DATE DEFAULT SYSDATE,
    total_amount NUMBER(10,2)
);

CREATE TABLE order_items (
    order_item_id NUMBER PRIMARY KEY,
    order_id NUMBER,
    product_id NUMBER,
    quantity NUMBER,
    price NUMBER(10,2),
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE SEQUENCE orders_seq START WITH 1 INCREMENT BY 1;
CREATE SEQUENCE order_items_seq START WITH 1 INCREMENT BY 1;
