CREATE SCHEMA IF NOT EXISTS securecapita;

SET client_encoding = 'UTF8';
SET timezone = 'UTC+4';
SET search_path = securecapita;

-- Drop tables  
DROP TABLE IF EXISTS two_factor_verifications;
DROP TABLE IF EXISTS reset_password_verifications;
DROP TABLE IF EXISTS account_verifications;
DROP TABLE IF EXISTS user_events;
DROP TABLE IF EXISTS events;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;


CREATE TABLE users (
    id              BIGSERIAL           PRIMARY KEY,
    first_name      VARCHAR(50)         NOT NULL,
    last_name       VARCHAR(50)         NOT NULL,
    email           VARCHAR(100)        NOT NULL,
    password        VARCHAR(255)        DEFAULT NULL,
    address         VARCHAR(255)        DEFAULT NULL,
    phone           VARCHAR(30)         DEFAULT NULL,
    title           VARCHAR(50)         DEFAULT NULL,
    bio             VARCHAR(500)        DEFAULT NULL,
    is_active       BOOLEAN             DEFAULT FALSE,
    is_locked       BOOLEAN             DEFAULT FALSE,
    is_mfa_enabled  BOOLEAN             DEFAULT FALSE,
    created_at      TIMESTAMP           DEFAULT CURRENT_TIMESTAMP,
    image_url       VARCHAR(255)        DEFAULT 'https://cdn-icons-png.flaticon.com/512/149/149071.png',
    CONSTRAINT uq_users_email UNIQUE (email)
);

CREATE TABLE roles (
    id         BIGSERIAL       PRIMARY KEY,
    name       VARCHAR(50)     NOT NULL,
    permission VARCHAR(255)    NOT NULL,
    CONSTRAINT uq_roles_name UNIQUE (name)
);

CREATE TABLE user_roles (
    id      BIGSERIAL       PRIMARY KEY,
    user_id BIGINT          NOT NULL,
    role_id BIGINT          NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE RESTRICT ON UPDATE CASCADE,
    CONSTRAINT uq_user_roles_user_id UNIQUE (user_id)
);

CREATE TABLE events (
    id          BIGSERIAL       PRIMARY KEY,
    type        VARCHAR(50)     NOT NULL,
    description VARCHAR(255)    NOT NULL,
    CONSTRAINT uq_events_type UNIQUE (type)
);

ALTER TABLE events ADD CONSTRAINT check_type CHECK (type IN ('LOGIN_ATTEMPT', 'LOGIN_ATTEMPT_FAILURE', 'LOGIN_ATTEMPT_SUCCESS', 'PROFILE_UPDATE', 'PROFILE_PICTURE_UPDATE', 'ROLE_UPDATE', 'ACCOUNT_SETTINGS_UPDATE', 'PASSWORD_UPDATE', 'MFA_UPDATE'));

CREATE TABLE user_events (
    id         BIGSERIAL       PRIMARY KEY,
    user_id    BIGINT          NOT NULL,
    event_id   BIGINT          NOT NULL,
    device     VARCHAR(100)    DEFAULT NULL,
    ip_address VARCHAR(100)    DEFAULT NULL,
    created_at TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (event_id) REFERENCES events (id) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE account_verifications (
    id      BIGSERIAL       PRIMARY KEY,
    user_id BIGINT          NOT NULL,
    url     VARCHAR(255)    NOT NULL,
    -- expiration_date TIMESTAMP       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT uq_account_verifications_user_id UNIQUE (user_id),
    CONSTRAINT uq_account_verifications_url UNIQUE (url)
);

CREATE TABLE reset_password_verifications (
    id              BIGSERIAL       PRIMARY KEY,
    user_id         BIGINT          NOT NULL,
    url             VARCHAR(255)    NOT NULL,
    expiration_date TIMESTAMP       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT uq_reset_password_verifications_user_id UNIQUE (user_id),
    CONSTRAINT uq_reset_password_verifications_url UNIQUE (url)
);

CREATE TABLE two_factor_verifications (
    id              BIGSERIAL       PRIMARY KEY,
    user_id         BIGINT          NOT NULL,
    code            VARCHAR(10)     NOT NULL,
    expiration_date TIMESTAMP       NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT uq_two_factor_verifications_user_id UNIQUE (user_id),
    CONSTRAINT uq_two_factor_verifications_code UNIQUE (code)
);
