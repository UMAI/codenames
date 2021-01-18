CREATE TABLE TEAMS(
	ID NUMERIC(10) NOT NULL,
	NAME VARCHAR(30) NOT NULL,
	COLOR_HEX CHAR(7) NOT NULL,
	LOBBY_ID NUMERIC(10) NOT NULL,
	PRIMARY KEY (ID),
	CONSTRAINT FK_LOBBY_ID FOREIGN KEY (LOBBY_ID) REFERENCES LOBBIES(ID)
);

CREATE SEQUENCE IF NOT EXISTS SEQ_TEAMS_ID INCREMENT BY 1 START WITH 1 CYCLE OWNED BY TEAMS.ID;

CREATE UNIQUE INDEX IDX_NAME_LOBBY_ID ON TEAMS (NAME, LOBBY_ID);

COMMENT ON COLUMN TEAMS.ID IS 'Unique identifier';
COMMENT ON COLUMN TEAMS.NAME IS 'Team name';
COMMENT ON COLUMN TEAMS.COLOR_HEX IS 'Hex color code (ex. #000000)';
COMMENT ON COLUMN TEAMS.LOBBY_ID IS 'Identifier of a lobby the team is a part of';