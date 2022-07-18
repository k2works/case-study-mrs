-- 利用者から認証にスキーマを変更

CREATE SCHEMA usr;
CREATE TABLE usr.利用者
(
    利用者番号 VARCHAR(255) NOT NULL,
    姓     VARCHAR(255) NOT NULL,
    名     VARCHAR(255) NOT NULL,
    パスワード VARCHAR(255) NOT NULL,
    利用者区分 VARCHAR(255) NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (利用者番号)
);

DROP TABLE IF EXISTS reservation.予約;
CREATE TABLE reservation.予約
(
    予約番号   INT4         NOT NULL AUTO_INCREMENT,
    予約終了時間 TIME         NOT NULL,
    予約開始時間 TIME         NOT NULL,
    予約日    DATE         NOT NULL,
    会議室番号  INT4         NOT NULL,
    利用者番号  VARCHAR(255) NOT NULL,
    登録日時   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (予約番号),
    FOREIGN KEY (予約日, 会議室番号) REFERENCES reservation.予約可能会議室,
    FOREIGN KEY (利用者番号) REFERENCES usr.利用者
);

DROP TABLE IF EXISTS contact.問い合わせ;
CREATE TABLE contact.問い合わせ
(
    問い合わせ番号 VARCHAR(255) NOT NULL,
    問い合わせ内容 VARCHAR(255) NOT NULL,
    利用者番号   VARCHAR(255) NOT NULL,
    登録日時    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (問い合わせ番号),
    FOREIGN KEY (利用者番号) REFERENCES usr.利用者
);

DROP TABLE IF EXISTS auth.利用者;
DROP SCHEMA IF EXISTS auth;
