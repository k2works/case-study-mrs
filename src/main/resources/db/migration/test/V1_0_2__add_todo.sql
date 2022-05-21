DROP TABLE IF EXISTS todo CASCADE;

CREATE TABLE todo
(
    id       INT4    NOT NULL AUTO_INCREMENT,
    title    TEXT    NOT NULL,
    details  TEXT,
    finished BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);
