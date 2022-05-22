CREATE SCHEMA sample;
CREATE TABLE sample.todo
(
    id       INT4    NOT NULL AUTO_INCREMENT,
    title    TEXT    NOT NULL,
    details  TEXT,
    finished BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);
