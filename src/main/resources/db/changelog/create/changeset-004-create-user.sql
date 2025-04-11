CREATE TABLE IF NOT EXISTS app_user
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email    VARCHAR(255) NOT NULL UNIQUE
);
--rollback DROP TABLE app_user;