-- Active: 1708557140330@@127.0.0.1@5432@securecapita@securecapita
SET search_path TO securecapita;

INSERT INTO
    roles (name, permission)
VALUES
    ('ROLE_USER', 'READ:USER,READ:CUSTOMER'),
    (
        'ROLE_MANAGER',
        'READ:USER,READ:CUSTOMER,UPTATE:USER,UPDATE:CUSTOMER'
    ),
    (
        'ROLE_ADMIN',
        'READ:USER,READ:CUSTOMER,CREATE:USER,CREATE:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER'
    ),
    (
        'ROLE_SUPER_ADMIN',
        'READ:USER,READ:CUSTOMER,CREATE:USER,CREATE:CUSTOMER,UPDATE:USER,UPDATE:CUSTOMER,DELETE:USER,DELETE:CUSTOMER'
    );

SELECT * FROM roles;