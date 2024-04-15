CREATE TABLE todos (
    id SERIAL PRIMARY KEY,
    description VARCHAR,
    completed BOOLEAN,
    starred BOOLEAN
);