ALTER TABLE friends
    DROP COLUMN status;

ALTER TABLE friends
    ADD status LONGTEXT DEFAULT 'pending' NULL;

ALTER TABLE friends
    ALTER status SET DEFAULT 'pending';