CREATE TABLE IF NOT EXISTS album
(
    id        SERIAL PRIMARY KEY,
    title     VARCHAR(255) NOT NULL,
    description VARCHAR(255),
    artist_id INT NOT NULL
);
--rollback DROP TABLE album;