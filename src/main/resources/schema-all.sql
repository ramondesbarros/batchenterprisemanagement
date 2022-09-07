DROP TABLE people IF EXISTS;

CREATE TABLE people (
    id SMALLINT PRIMARY KEY AUTO_INCREMENT,	
    firstname VARCHAR(20),
    lastname VARCHAR(20),
    city VARCHAR(20),
    age VARCHAR(3)
);


