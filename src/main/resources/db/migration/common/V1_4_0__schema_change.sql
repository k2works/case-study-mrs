-- 利用者から認証にスキーマを変更

CREATE SCHEMA auth;
DROP TABLE IF EXISTS auth.会員;
CREATE TABLE auth.会員
(
    会員番号  VARCHAR(255) NOT NULL,
    姓     VARCHAR(255) NOT NULL,
    名     VARCHAR(255) NOT NULL,
    パスワード VARCHAR(255) NOT NULL,
    役割    VARCHAR(255) NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (会員番号)
);

INSERT INTO auth.会員 (会員番号, 姓, 名, パスワード, 役割)
SELECT 会員番号, 姓, 名, パスワード, 役割
FROM usr.会員;
ALTER TABLE reservation.予約
    ADD FOREIGN KEY (会員番号) REFERENCES auth.会員;
ALTER TABLE reservation.予約 DROP CONSTRAINT 予約_会員番号_fkey;
DROP TABLE IF EXISTS usr.会員;
DROP SCHEMA IF EXISTS usr;
