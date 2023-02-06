DROP TABLE if EXISTS hits;
CREATE TABLE if NOT EXISTS public.hits
(
    id         int8    NOT NULL GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    app     varchar NOT NULL,
    uri     varchar NOT NULL,
    ip     varchar NOT NULL,
    timestamp timestamp without time zone NOT NULL

    );
