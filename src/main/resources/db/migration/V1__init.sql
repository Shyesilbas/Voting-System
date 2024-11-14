CREATE TABLE IF NOT EXISTS voter(
id Serial PRIMARY KEY,
name Varchar(55) NOT NULL,
surname Varchar(55) NOT NULL,
personalId BIGINT NOT NULL UNIQUE
);