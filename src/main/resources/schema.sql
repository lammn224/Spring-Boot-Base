CREATE TABLE users(
    id    SERIAL PRIMARY KEY,
    email VARCHAR(40) NOT NULL UNIQUE
);

CREATE TABLE posts(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id),
    title   VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE comments(
    id      SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES users(id),
    post_id INTEGER NOT NULL REFERENCES posts(id),
    body    VARCHAR(500) NOT NULL
);