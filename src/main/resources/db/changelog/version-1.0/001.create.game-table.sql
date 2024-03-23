create TABLE IF NOT EXISTS games
(
    game_id   UUID        PRIMARY KEY,
    width INT NOT NULL,
    height INT NOT NULL,
    mines_count INT NOT NULL,
    completed BOOLEAN NOT NULL,
    field CHAR[][] NOT NULL
);