Feature: 会員管理:利用者の管理(選択)

  スタッフとして
  アプリケーション利用者の登録をしたい
  なぜなら登録された利用者しか認証できないから

  Background:
    Given "スタッフ" としてログインしている

  Scenario: 利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given 利用者番号を空白で新規登録する
    Then 利用者登録画面に "利用者番号が未入力です" がエラー表示される

  Scenario: 利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given 名前を空白で新規登録する
    Then 利用者登録画面に "名前が未入力です" がエラー表示される

  Scenario: 利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given パスワードを空白で新規登録する
    Then 利用者登録画面に "パスワードを入力してください" がエラー表示される

  Scenario: 利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given 利用者番号を "123" で新規登録する
    Then 利用者登録画面に "利用者番号の先頭はUから始まります" がエラー表示される

  Scenario: 利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given 利用者番号を "U123" で新規登録する
    Then 利用者登録画面に "利用者番号の長さは7文字です" がエラー表示される

  Scenario: 利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given パスワードを "123" で新規登録する
    Then 利用者登録画面に "パスワードは8文字以上である必要があります" がエラー表示される

  Scenario: 利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given パスワードを "password" で新規登録する
    Then 利用者登録画面に "パスワードは小文字、大文字、数字を含む必要があります" がエラー表示される

  Scenario: 登録済み利用者を新規登録する
    Given "利用者一覧画面" ページにアクセスする
    Given 存在する利用者を新規登録する
    Then 利用者登録画面に "利用者はすでに登録されています" がエラー表示される
