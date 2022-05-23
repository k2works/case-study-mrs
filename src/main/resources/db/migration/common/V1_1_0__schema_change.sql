DROP SCHEMA IF EXISTS service_user;
CREATE SCHEMA service_user;
DROP SCHEMA IF EXISTS reserve;
CREATE SCHEMA reserve;

-- 利用者スキーマ
DROP TABLE IF EXISTS service_user.会員;
CREATE TABLE service_user.会員
(
    会員番号  VARCHAR(255) NOT NULL,
    姓     VARCHAR(255) NOT NULL,
    名     VARCHAR(255) NOT NULL,
    パスワード VARCHAR(255) NOT NULL,
    役割    VARCHAR(255) NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (会員番号)
);

-- 予約スキーマ
DROP TABLE IF EXISTS reserve.会議室;
CREATE TABLE reserve.会議室
(
    会議室番号 SERIAL       NOT NULL,
    会議室名  VARCHAR(255) NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (会議室番号)
);

DROP TABLE IF EXISTS reserve.予約可能会議室;
CREATE TABLE reserve.予約可能会議室
(
    予約日   DATE NOT NULL,
    会議室番号 INT4 NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (予約日, 会議室番号),
    FOREIGN KEY (会議室番号) REFERENCES reserve.会議室
);

DROP TABLE IF EXISTS reserve.予約;
CREATE TABLE reserve.予約
(
    予約番号   SERIAL       NOT NULL,
    予約終了時間 TIME         NOT NULL,
    予約開始時間 TIME         NOT NULL,
    予約日    DATE         NOT NULL,
    会議室番号  INT4         NOT NULL,
    会員番号   VARCHAR(255) NOT NULL,
    登録日時   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (予約番号),
    FOREIGN KEY (予約日, 会議室番号) REFERENCES reserve.予約可能会議室,
    FOREIGN KEY (会員番号) REFERENCES service_user.会員
);
