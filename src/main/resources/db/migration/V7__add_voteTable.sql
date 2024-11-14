CREATE TABLE IF NOT EXISTS vote (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    candidate_id INT NOT NULL,
    vote_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES voter (id),
    FOREIGN KEY (candidate_id) REFERENCES candidate (id)
);
