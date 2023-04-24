--liquibase formatted sql
--changeset <borysenko>:<create-movie-character-sequence-id>
CREATE TABLE IF NOT EXISTS public.movie_character (
    id BIGINT NOT NULL,
    name character varying(256) NOT NULL,
    status character varying(256) NOT NULL,
    gender character varying(256) NOT NULL,
    CONSTRAINT movie_character_pk PRIMARY KEY (id)
);

--rollback DROP TABLE movie-character;