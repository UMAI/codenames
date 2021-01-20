CREATE TABLE LOBBY_WORDS(
    ID NUMERIC(10) NOT NULL,
    LOBBY_ID NUMERIC(10) NOT NULL,
    WORD VARCHAR(20) NOT NULL,
    COLOR_HEX VARCHAR(7) NOT NULL,
    OPEN BOOLEAN NOT NULL DEFAULT FALSE,
    PRIMARY KEY (ID),
    CONSTRAINT FK_LOBBY_ID FOREIGN KEY (LOBBY_ID) REFERENCES LOBBIES.ID
);

CREATE SEQUENCE IF NOT EXISTS SEQ_LOBBY_WORDS_ID INCREMENT BY 1 START WITH 1 CYCLE OWNED BY LOBBY_WORDS.ID;

COMMENT ON COLUMN LOBBY_WORDS.ID IS 'Unique identifier';
COMMENT ON COLUMN LOBBY_WORDS.LOBBY_ID IS 'Id of a lobby this word is played in';
COMMENT ON COLUMN LOBBY_WORDS.WORD IS 'Word that is being played';
COMMENT ON COLUMN LOBBY_WORDS.COLOR_HEX IS 'Hex color code (ex. #000000)';
COMMENT ON COLUMN LOBBY_WORDS.OPEN IS 'Was this word flipped';