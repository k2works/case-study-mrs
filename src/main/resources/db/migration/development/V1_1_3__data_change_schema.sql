DELETE
FROM service_user.会員
where 会員番号 = 'taro-yamada';
INSERT INTO service_user.会員 (会員番号, 姓, 名, パスワード, 役割)
VALUES ('U000001', '太郎', '山田', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'USER');

-- 会議室の予約可能日(room_idが2〜6用のSQLは省略
-- room_id=1(新木場)の予約可能日
INSERT INTO reserve.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE, 1);
INSERT INTO reserve.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE + 1, 1);
INSERT INTO reserve.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE - 1, 1);
-- 会議室番号=7(有楽町)の予約可能日
INSERT INTO reserve.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE, 7);
INSERT INTO reserve.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE + 1, 7);
INSERT INTO reserve.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE - 1, 7);

-- 予約
INSERT INTO reserve.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 会員番号)
VALUES ('9:00', '10:00', CURRENT_DATE, 1, 'U000001');
