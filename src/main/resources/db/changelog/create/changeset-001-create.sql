CREATE TABLE IF NOT EXISTS artist
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);
--rollback DROP TABLE artist;

CREATE TABLE IF NOT EXISTS album
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    artist_id INT NOT NULL
);
--rollback DROP TABLE album;

CREATE TABLE IF NOT EXISTS song
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    duration  INT          NOT NULL CHECK (duration > 0),
    album_id  INT,
    artist_id INT
);
--rollback DROP TABLE song;

CREATE TABLE IF NOT EXISTS app_user
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email    VARCHAR(255) NOT NULL UNIQUE
);
--rollback DROP TABLE app_user;

CREATE TABLE IF NOT EXISTS purchase
(
    id            SERIAL PRIMARY KEY,
    user_id       INT       NOT NULL,
    song_id       INT       NOT NULL,
    price         INT       CHECK (price >= 0),
    purchase_date TIMESTAMP NOT NULL DEFAULT now()
);
--rollback DROP TABLE purchase;