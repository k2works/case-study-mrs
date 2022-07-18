DELETE
FROM reservation.予約;
DELETE
FROM reservation.予約可能会議室;
DELETE
FROM facility.会議室;
DELETE
FROM usr.利用者;

-- 会議室
INSERT INTO facility.会議室 (会議室番号, 会議室名)
VALUES (1, '新木場');
INSERT INTO facility.会議室 (会議室番号, 会議室名)
VALUES (2, '辰巳');
INSERT INTO facility.会議室 (会議室番号, 会議室名)
VALUES (3, '豊洲');
INSERT INTO facility.会議室 (会議室番号, 会議室名)
VALUES (4, '月島');
INSERT INTO facility.会議室 (会議室番号, 会議室名)
VALUES (5, '新富町');
INSERT INTO facility.会議室 (会議室番号, 会議室名)
VALUES (6, '銀座一丁目');
INSERT INTO facility.会議室 (会議室番号, 会議室名)
VALUES (7, '有楽町');

-- 会議室の予約可能日(room_idが2〜6用のSQLは省略
-- room_id=1(新木場)の予約可能日
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE, 1);
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE + 1, 1);
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE - 1, 1);

INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U000001', 'Aaa', 'Aaa', '$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu', '一般');
INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U000002', 'Bbb', 'Bbb', '$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu', '一般');
INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U000003', 'Ccc', 'Ccc', '$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu', '管理者');
INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U999999', '', '', '', 'ゲスト');

-- 予約
INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
VALUES ('9:00', '10:00', CURRENT_DATE, 1, 'U000001');
INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
VALUES ('10:00', '11:00', CURRENT_DATE, 1, 'U000001');
INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
VALUES ('11:00', '12:00', CURRENT_DATE, 1, 'U000001');
INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
VALUES ('13:00', '14:00', CURRENT_DATE, 1, 'U000001');
INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
VALUES ('14:00', '15:00', CURRENT_DATE, 1, 'U000001');

-- 問い合わせ
INSERT INTO contact.問い合わせ (問い合わせ番号, 問い合わせ内容, 利用者番号)
VALUES ('C000001', '問い合わせ1', 'U000001');
INSERT INTO contact.問い合わせ (問い合わせ番号, 問い合わせ内容, 利用者番号)
VALUES ('C000002', '問い合わせ2', 'U000001');
INSERT INTO contact.問い合わせ (問い合わせ番号, 問い合わせ内容, 利用者番号)
VALUES ('C000003', '問い合わせ3', 'U000001');
INSERT INTO contact.問い合わせ (問い合わせ番号, 問い合わせ内容, 利用者番号)
VALUES ('C000004', '問い合わせ4', 'U000001');
INSERT INTO contact.問い合わせ (問い合わせ番号, 問い合わせ内容, 利用者番号)
VALUES ('C000005', '問い合わせ5', 'U000001');
