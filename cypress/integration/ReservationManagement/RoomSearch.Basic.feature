Feature: 予約管理:会議室の検索(基本)

  会員として
  会議室を検索したい
  なぜなら会議室を利用したいから

  Scenario: 予約可能な会議室を検索する
    Given "会員" としてログインしている
    Given "会議室予約一覧画面" ページにアクセスする
    Then 会議室予約一覧に "新木場" が表示される