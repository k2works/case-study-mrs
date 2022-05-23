-- ダミーユーザー(password = demo)
INSERT INTO service_user.会員 (会員番号, 姓, 名, パスワード, 役割)
VALUES ('taro-yamada', '太郎', '山田', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'USER');
-- 認証確認用のテストユーザー(password = demo)
INSERT INTO service_user.会員 (会員番号, 姓, 名, パスワード, 役割)
VALUES ('aaaa', 'Aaa', 'Aaa', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'USER');
INSERT INTO service_user.会員 (会員番号, 姓, 名, パスワード, 役割)
VALUES ('bbbb', 'Bbb', 'Bbb', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'USER');
INSERT INTO service_user.会員 (会員番号, 姓, 名, パスワード, 役割)
VALUES ('cccc', 'Ccc', 'Ccc', '$2y$10$aYjdzuGAep5SXGVBxxGrMOnh1b1mL62sJLx5Sj.r4dM4BTICiemCG', 'ADMIN');
