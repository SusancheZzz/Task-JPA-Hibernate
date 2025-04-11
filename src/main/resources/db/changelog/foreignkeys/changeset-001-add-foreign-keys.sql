ALTER TABLE album
    ADD CONSTRAINT fk_album_artist
        FOREIGN KEY (artist_id)
            REFERENCES artist (id);

ALTER TABLE song
    ADD CONSTRAINT fk_song_album
        FOREIGN KEY (album_id)
            REFERENCES album (id);

ALTER TABLE song
    ADD CONSTRAINT fk_song_artist
        FOREIGN KEY (artist_id)
            REFERENCES artist (id);

ALTER TABLE purchase
    ADD CONSTRAINT fk_purchase_user
        FOREIGN KEY (user_id)
            REFERENCES app_user (id);

ALTER TABLE purchase
    ADD CONSTRAINT fk_purchase_song
        FOREIGN KEY (song_id)
            REFERENCES song (id) ON DELETE SET NULL;
