CREATE TABLE WORDS(
	ID NUMERIC(10) NOT NULL,
	WORD VARCHAR(20),
	LANGUAGE VARCHAR(2),
	PRIMARY KEY (ID)
);

COMMENT ON COLUMN WORDS.ID IS 'Unique identifier';
COMMENT ON COLUMN WORDS.WORD IS 'Word (noun)';
COMMENT ON COLUMN WORDS.LANGUAGE IS 'Two-digit language code';