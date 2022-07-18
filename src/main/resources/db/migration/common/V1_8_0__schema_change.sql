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

INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
SELECT 利用者番号, 姓, 名, パスワード, 役割
FROM auth.利用者;

ALTER TABLE reservation.予約
    ADD FOREIGN KEY (利用者番号) REFERENCES usr.利用者;
ALTER TABLE reservation.予約 DROP CONSTRAINT 予約_利用者番号_fkey;

ALTER TABLE contact.問い合わせ
    ADD FOREIGN KEY (利用者番号) REFERENCES usr.利用者;
ALTER TABLE contact.問い合わせ DROP CONSTRAINT 問い合わせ_利用者番号_fkey;

DROP TABLE IF EXISTS auth.利用者;
DROP SCHEMA IF EXISTS auth;
