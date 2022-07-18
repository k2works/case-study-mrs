INSERT INTO facility.会議室 (会議室番号, 会議室名)
SELECT ROW_NUMBER() OVER () + 10, '会議室_' || ROW_NUMBER() OVER ()
FROM facility.会議室 AS f1,
     facility.会議室 AS f2,
     facility.会議室 AS f3,
     facility.会議室 AS f4,
     facility.会議室 AS f5,
     facility.会議室 AS f6;


INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
SELECT CURRENT_DATE,
       ROW_NUMBER() OVER () + 10
FROM reservation.予約可能会議室 AS f1,
     reservation.予約可能会議室 AS f2,
     reservation.予約可能会議室 AS f3,
     reservation.予約可能会議室 AS f4,
     reservation.予約可能会議室 AS f5,
     reservation.予約可能会議室 AS f6,
     reservation.予約可能会議室 AS f7,
     reservation.予約可能会議室 AS f8,
     reservation.予約可能会議室 AS f9,
     reservation.予約可能会議室 AS f10;

INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
SELECT CURRENT_TIME + INTERVAL '1' HOUR,
       CURRENT_TIME + INTERVAL '1' HOUR,
       CURRENT_DATE,
       ROW_NUMBER() OVER () + 10, 'U000001'
FROM reservation.予約 AS f1,
     reservation.予約 AS f2,
     reservation.予約 AS f3,
     reservation.予約 AS f4,
     reservation.予約 AS f5,
     reservation.予約 AS f6;

INSERT INTO contact.問い合わせ(問い合わせ番号, 問い合わせ内容, 利用者番号)
SELECT ROW_NUMBER() OVER () + 5, '問い合わせ_' || ROW_NUMBER() OVER (), 'U000001'
FROM contact.問い合わせ AS f1,
     contact.問い合わせ AS f2,
     contact.問い合わせ AS f3,
     contact.問い合わせ AS f4,
     contact.問い合わせ AS f5;
