CREATE TABLE IF NOT EXISTS city(
    id SERIAL PRIMARY KEY,
    name varchar(100) NOT NULL,
    temperature INTEGER NOT NULL
);