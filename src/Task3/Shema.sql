CREATE TABLE Product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price NUMERIC(10, 2) NOT NULL CHECK (price >= 0)
)

INSERT INTO Product (name, price) VALUES
    ('Соль', 85),
    ('Хлеб', 45),
    ('Колбаса', 450),
    ('Сыр', 350);