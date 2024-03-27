-- This script was generated by the ERD tool in pgAdmin 4.
-- Please log an issue at https://redmine.postgresql.org/projects/pgadmin4/issues/new if you find any bugs, including reproduction steps.
BEGIN;


CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    first_name character varying(50) NOT NULL,
    last_name character varying(50) NOT NULL,
    email character varying(100) NOT NULL,
    phone character varying(20),
    PRIMARY KEY (id),
    UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 ),
    name character varying(50) NOT NULL,
    permissions character varying(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE (permissions)
);

CREATE TABLE IF NOT EXISTS public.user_roles
(
    user_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    role_id bigint NOT NULL GENERATED ALWAYS AS IDENTITY,
    UNIQUE (role_id)
);

ALTER TABLE IF EXISTS public.user_roles
    ADD CONSTRAINT user_id FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE CASCADE
    NOT VALID;


ALTER TABLE IF EXISTS public.user_roles
    ADD CONSTRAINT role_id FOREIGN KEY (role_id)
    REFERENCES public.roles (id) MATCH SIMPLE
    ON UPDATE CASCADE
    ON DELETE RESTRICT
    NOT VALID;

END;