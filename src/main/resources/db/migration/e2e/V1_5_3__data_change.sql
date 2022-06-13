DELETE
FROM reservation.予約;
DELETE
FROM auth.利用者
WHERE 利用者番号 = 'U000001';

UPDATE auth.利用者
SET 利用者番号= 'U000001'
WHERE 利用者番号 = 'aaaa';
UPDATE auth.利用者
SET 利用者番号= 'U000002'
WHERE 利用者番号 = 'bbbb';
UPDATE auth.利用者
SET 利用者番号= 'U000003'
WHERE 利用者番号 = 'cccc';
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000004', '太郎', '山田', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'MEMBER');

-- 予約
INSERT INTO reservation.予約 (予約終了時間, 予約開始時間, 予約日, 会議室番号, 利用者番号)
VALUES ('9:00', '10:00', CURRENT_DATE, 1, 'U000004');

