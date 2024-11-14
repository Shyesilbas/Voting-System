ALTER TABLE voter ADD COLUMN birthDate DATE;
ALTER TABLE voter ADD COLUMN ableToVote VARCHAR(3);

UPDATE voter
SET ableToVote =
    CASE
        WHEN birthDate <= CURRENT_DATE - INTERVAL '18' YEAR THEN 'YES'
        ELSE 'NO'

    END;