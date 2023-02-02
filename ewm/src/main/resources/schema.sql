DROP TABLE if EXISTS users;
CREATE TABLE if NOT EXISTS public.users
(
    id int8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar NOT NULL,
    email varchar NOT NULL UNIQUE
);