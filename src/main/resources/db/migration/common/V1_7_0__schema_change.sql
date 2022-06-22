DROP SCHEMA IF EXISTS contact;
CREATE SCHEMA contact;

DROP TABLE IF EXISTS contact.問い合わせ;
CREATE TABLE contact.問い合わせ
(
    問い合わせ番号 VARCHAR(255) NOT NULL,
    問い合わせ内容 VARCHAR(255) NOT NULL,
    利用者番号   VARCHAR(255) NOT NULL,
    登録日時    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (問い合わせ番号),
    FOREIGN KEY (利用者番号) REFERENCES auth.利用者
);
