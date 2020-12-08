CREATE TABLE users (
    id IDENTITY,
    name CHAR NOT NULL
);
CREATE TABLE yubisuma_room (
    user_no IDENTITY,
    user_id INT NOT NULL
);
