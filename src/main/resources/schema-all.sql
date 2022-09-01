DROP TABLE people IF EXISTS;

CREATE TABLE people (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    city VARCHAR(20),
    age VARCHAR(3)
);
