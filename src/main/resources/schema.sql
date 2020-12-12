CREATE TABLE users (
    id IDENTITY,
    name CHAR NOT NULL
);
CREATE TABLE yubisuma_room (
    no IDENTITY,
    id INT NOT NULL
);
CREATE TABLE data (
    id INT,
    hp INT,
    hand INT,
    hit INT
);
CREATE TABLE talk (
    id INT,
    name CHAR,
    talk CHAR
);
