-- 会議室
INSERT INTO facility.会議室 (会議室名)
VALUES ('新木場');
INSERT INTO facility.会議室 (会議室名)
VALUES ('辰巳');
INSERT INTO facility.会議室 (会議室名)
VALUES ('豊洲');
INSERT INTO facility.会議室 (会議室名)
VALUES ('月島');
INSERT INTO facility.会議室 (会議室名)
VALUES ('新富町');
INSERT INTO facility.会議室 (会議室名)
VALUES ('銀座一丁目');
INSERT INTO facility.会議室 (会議室名)
VALUES ('有楽町');

-- 会議室の予約可能日(room_idが2〜6用のSQLは省略
-- room_id=1(新木場)の予約可能日
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE, 1);
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE + 1, 1);
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE - 1, 1);
-- room_id=7(有楽町)の予約可能日
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE, 7);
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE + 1, 7);
INSERT INTO reservation.予約可能会議室(予約日, 会議室番号)
VALUES (CURRENT_DATE - 1, 7);

-- 認証確認用のテストユーザー(password = demo)
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000001', 'Aaa', 'Aaa', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'MEMBER');
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000002', 'Bbb', 'Bbb', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'MEMBER');
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000003', 'Ccc', 'Ccc', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'ADMIN');
-- ダミーユーザー(password = demo)
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000004', '山田', '太郎', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'MEMBER');

-- 予約
INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
VALUES ('9:00', '10:00', CURRENT_DATE, 1, 'U000001');
