DROP TABLE if EXISTS users, categories, events, requests, compilations, compilation_event;
CREATE TABLE if NOT EXISTS public.users
(
    id int8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar NOT NULL,
    email varchar NOT NULL UNIQUE
);
CREATE TABLE if NOT EXISTS public.categories
(
    id int8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name varchar NOT NULL UNIQUE
);
CREATE TABLE if NOT EXISTS public.events
(
    id int8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    annotation varchar NOT NULL,
    category_id int8 NOT NULL,
    created_on timestamp without time zone NOT NULL,
    description varchar,
    event_date timestamp without time zone NOT NULL,
    initiator_id int8 NOT NULL,
    lat float NOT NULL,
    lon float NOT NULL,
    paid boolean NOT NULL,
    participant_limit int8,
    published_on timestamp without time zone,
    request_moderation boolean,
    state varchar NOT NULL,
    title varchar NOT NULL
);
CREATE TABLE if NOT EXISTS public.requests
(
    id int8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    event_id int8 NOT NULL,
    created timestamp without time zone NOT NULL,
    requester_id int8 NOT NULL,
    status varchar NOT NULL,
    CONSTRAINT uq_requests UNIQUE(event_id, requester_id)
);
CREATE TABLE if NOT EXISTS public.compilations
(
    id int8 GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title varchar NOT NULL,
    pinned boolean NOT NULL
);
CREATE TABLE if NOT EXISTS public.compilation_event
(
    compilation_id int8 NOT NULL,
    event_id int8 NOT NULL,
    CONSTRAINT compilation_event_pk PRIMARY KEY (compilation_id, event_id)
);