CREATE TABLE IF NOT EXISTS song
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    duration  INT          NOT NULL CHECK (duration > 0),
    album_id  INT,
    artist_id INT
);
--rollback DROP TABLE song;