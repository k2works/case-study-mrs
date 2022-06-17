CREATE SCHEMA auth;
CREATE SCHEMA facility;
CREATE SCHEMA reservation;

CREATE TABLE auth.利用者
(
    利用者番号 VARCHAR(255) NOT NULL,
    姓     VARCHAR(255) NOT NULL,
    名     VARCHAR(255) NOT NULL,
    パスワード VARCHAR(255) NOT NULL,
    役割    VARCHAR(255) NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (利用者番号)
);

CREATE TABLE facility.会議室
(
    会議室番号 SERIAL       NOT NULL,
    会議室名  VARCHAR(255) NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (会議室番号)
);

CREATE TABLE reservation.予約可能会議室
(
    予約日   DATE NOT NULL,
    会議室番号 INT4 NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (予約日, 会議室番号),
    FOREIGN KEY (会議室番号) REFERENCES facility.会議室
);

CREATE TABLE reservation.予約
(
    予約番号   SERIAL       NOT NULL,
    予約終了時間 TIME         NOT NULL,
    予約開始時間 TIME         NOT NULL,
    予約日    DATE         NOT NULL,
    会議室番号  INT4         NOT NULL,
    利用者番号  VARCHAR(255) NOT NULL,
    登録日時   TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (予約番号),
    FOREIGN KEY (予約日, 会議室番号) REFERENCES reservation.予約可能会議室,
    FOREIGN KEY (利用者番号) REFERENCES auth.利用者
);

