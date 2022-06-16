UPDATE auth.利用者
SET 役割= '管理者'
WHERE 役割 = 'ADMIN';
UPDATE auth.利用者
SET 役割= '会員'
WHERE 役割 = 'MEMBER';
