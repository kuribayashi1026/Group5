CREATE TABLE users (
    id IDENTITY,
    name CHAR NOT NULL
);
CREATE TABLE yubisuma_room (
    no INT NOT NULL,
    id INT NOT NULL PRIMARY KEY,
    name CHAR NOT NULL
);
CREATE TABLE data (
    id INT NOT NULL PRIMARY KEY,
    hp INT NOT NULL,
    hand INT,
    hit INT,
    isActed BOOLEAN,
    isNext BOOLEAN
);
