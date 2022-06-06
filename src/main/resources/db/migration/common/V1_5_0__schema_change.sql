-- 利用者から認証にスキーマを変更

DROP TABLE IF EXISTS auth.利用者;
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

INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
SELECT 会員番号, 姓, 名, パスワード, 役割
FROM auth.会員;

ALTER TABLE reservation.予約 RENAME 会員番号 TO 利用者番号;

ALTER TABLE reservation.予約
    ADD FOREIGN KEY (利用者番号) REFERENCES auth.利用者;

ALTER TABLE reservation.予約 DROP CONSTRAINT 予約_会員番号_fkey1;

DROP TABLE IF EXISTS auth.会員;
