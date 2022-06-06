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
    登録日時  TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (会員番号)
);

INSERT INTO auth.会員 (会員番号, 姓, 名, パスワード, 役割)
SELECT 会員番号, 姓, 名, パスワード, 役割
FROM usr.会員;

DROP TABLE IF EXISTS reservation.予約;
CREATE TABLE reservation.予約
(
    予約番号   INT4         NOT NULL AUTO_INCREMENT,
    予約終了時間 TIME         NOT NULL,
    予約開始時間 TIME         NOT NULL,
    予約日    DATE         NOT NULL,
    会議室番号  INT4         NOT NULL,
    会員番号   VARCHAR(255) NOT NULL,
    登録日時   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (予約番号),
    FOREIGN KEY (予約日, 会議室番号) REFERENCES reservation.予約可能会議室,
    FOREIGN KEY (会員番号) REFERENCES auth.会員
);
DROP TABLE IF EXISTS usr.会員;
DROP SCHEMA IF EXISTS usr;
