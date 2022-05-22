CREATE SCHEMA sample;
CREATE TABLE sample.todo
(
    id       SERIAL  NOT NULL,
    title    TEXT    NOT NULL,
    details  TEXT,
    finished BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);
comment on table sample.todo is 'やること';
comment on column sample.todo.id is 'ID';
comment on column sample.todo.title is 'タイトル';
comment on column sample.todo.details is '詳細';
comment on column sample.todo.finished is '完了';
