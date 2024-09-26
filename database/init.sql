CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    address VARCHAR(255)
);

INSERT INTO users (name, age, address) VALUES
('John Doe', 30, '123 Main St'),
('Jane Smith', 25, '456 Elm St'),
('Alice Johnson', 35, '789 Oak St');
