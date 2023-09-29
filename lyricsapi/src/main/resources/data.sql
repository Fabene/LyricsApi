CREATE DATABASE lyricsapi;

\c lyricsapi;

CREATE TABLE artist (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255),
                        genre VARCHAR(255)
);

CREATE TABLE song (
                      id SERIAL PRIMARY KEY,
                      title VARCHAR(255),
                      year INT,
                      album VARCHAR(255),
                      lyrics TEXT,
                      artist_id INT,
                      FOREIGN KEY (artist_id) REFERENCES artist(id)
);

