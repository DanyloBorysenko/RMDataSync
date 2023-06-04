--liquibase formatted sql
--changeset <borysenko>:<add-external-id-column-movie-character-table>
ALTER TABLE public.movie_character ADD external_id BIGINT;

--rollback ALTER TABLE DROP COLUMN external_id;