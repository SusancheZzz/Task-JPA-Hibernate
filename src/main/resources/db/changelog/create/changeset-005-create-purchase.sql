CREATE TABLE IF NOT EXISTS purchase
(
    id            SERIAL PRIMARY KEY,
    user_id       INT       NOT NULL,
    song_id       INT       NOT NULL,
    price         INT       CHECK (price >= 0),
    purchase_date TIMESTAMP NOT NULL DEFAULT now()
);
--rollback DROP TABLE purchase;