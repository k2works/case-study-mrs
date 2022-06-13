DELETE
FROM reservation.予約;
DELETE
FROM auth.利用者;

INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000001', 'Aaa', 'Aaa', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'MEMBER');
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000002', 'Bbb', 'Bbb', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'MEMBER');
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000003', 'Ccc', 'Ccc', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'ADMIN');
INSERT INTO auth.利用者 (利用者番号, 姓, 名, パスワード, 役割)
VALUES ('U000004', '太郎', '山田', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'MEMBER');
