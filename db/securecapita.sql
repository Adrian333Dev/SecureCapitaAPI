-- Active: 1708557140330@@127.0.0.1@5432@securecapita@securecapita

-- Drop all the user rows from users table
DELETE FROM users;
SELECT * FROM users;
SELECT * FROM user_roles;

SELECT * FROM roles WHERE name = 'ROLE_USER';

SELECT * FROM roles;