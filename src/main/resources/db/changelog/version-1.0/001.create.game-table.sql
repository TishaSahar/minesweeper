create TABLE IF NOT EXISTS games
(
    id   UUID        PRIMARY KEY,
    width INT NOT NULL,
    height INT NOT NULL,
    mines_count INT NOT NULL,
    field VARCHAR(128) NOT NULL
);