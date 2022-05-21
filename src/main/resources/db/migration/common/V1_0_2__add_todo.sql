DROP TABLE IF EXISTS todo CASCADE;
CREATE TABLE public.todo
(
    id       SERIAL  NOT NULL,
    title    TEXT    NOT NULL,
    details  TEXT,
    finished BOOLEAN NOT NULL
);
comment on table public.todo is 'やること';
comment on column public.todo.id is 'ID';
comment on column public.todo.title is 'タイトル';
comment on column public.todo.details is '詳細';
comment on column public.todo.finished is '完了';
