DELETE
FROM usr.利用者;

INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U000001', 'Aaa', 'Aaa', '$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu', '一般');
INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U000002', 'Bbb', 'Bbb', '$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu', '一般');
INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U000003', 'Ccc', 'Ccc', '$2a$10$aXH5nDjY1F4Vbzvhx7Xm3uHKazYwbZID.N.L.wM8CN2Ovj7jwEvfu', '管理者');
INSERT INTO usr.利用者 (利用者番号, 姓, 名, パスワード, 利用者区分)
VALUES ('U999999', '', '', '', 'ゲスト');
