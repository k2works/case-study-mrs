Feature: 会員管理:問い合わせ管理

  スタッフとして
  会員からの問い合わせを管理したい
  なぜならサービス品質を向上させるため資料を提供するために必要であるから

  Background:
    Given "スタッフ" としてログインしている

  Scenario: 問い合わせを確認する
    Given "スタッフ" としてログインしている
    Given "問い合わせ一覧画面" ページにアクセスする
