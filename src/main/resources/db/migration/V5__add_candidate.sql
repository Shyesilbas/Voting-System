CREATE TABLE IF NOT EXISTS candidate (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    birth_date DATE NOT NULL,
    gender VARCHAR(10),
    party VARCHAR(100),
    votes_received BIGINT DEFAULT 0
);
