CREATE TABLE products_schema.products (
    id           SERIAL PRIMARY KEY,
    name         VARCHAR(100) NOT NULL,
    price        NUMERIC(10,2) NOT NULL,
    in_stock     BOOLEAN NOT NULL DEFAULT true,
    created_at   TIMESTAMP NOT NULL
);