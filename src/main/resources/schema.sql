CREATE TABLE users (
    id IDENTITY,
    name CHAR NOT NULL
);
CREATE TABLE yubisuma_room (
    no IDENTITY,
    id INT NOT NULL
);
CREATE TABLE data (
    id INT NOT NULL PRIMARY KEY,
    hp INT NOT NULL,
    hand INT,
    hit INT
);
CREATE TABLE talk (
    id INT PRIMARY KEY,
    name CHAR,
    talk CHAR
);
