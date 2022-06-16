-- 会議室のスキーマを移動
DROP SCHEMA IF EXISTS facility;
CREATE SCHEMA facility;
CREATE TABLE facility.会議室
(
    会議室番号 SERIAL       NOT NULL,
    会議室名  VARCHAR(255) NOT NULL,
    登録日時  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (会議室番号)
);

INSERT INTO facility.会議室(会議室番号, 会議室名)
SELECT 会議室番号, 会議室名
FROM reservation.会議室;

ALTER TABLE reservation.予約可能会議室
    ADD FOREIGN KEY (会議室番号) REFERENCES facility.会議室;

ALTER TABLE reservation.予約可能会議室 DROP CONSTRAINT 予約可能会議室_会議室番号_fkey;

DROP TABLE IF EXISTS reservation.会議室;
