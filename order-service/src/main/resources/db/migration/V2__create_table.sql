CREATE TABLE orders_schema.orders (
    id         SERIAL PRIMARY KEY,
    user_id    INT NOT NULL,
    product_id INT NOT NULL,
    quantity   INT NOT NULL CHECK (quantity > 0),
    status     VARCHAR(20) NOT NULL,
    created_at TIMESTAMP NOT NULL
);