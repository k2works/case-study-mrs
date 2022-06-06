[![Node.js CI](https://github.com/k2works/application_programing_excercise_2022/actions/workflows/node.js.yml/badge.svg)](https://github.com/k2works/application_programing_excercise_2022/actions/workflows/node.js.yml)

# アプリケーション開発のための練習プログラミング

## 概要

Spring 徹底入門 Spring Framework による Java アプリケーション開発 チュートリアルの実装例

### 目的

- SpringBootの学習
- 戦略・戦術的DDD 及び [CCSR手法](https://masuda220.hatenablog.com/entry/2020/05/27/103750) の実践
- テスト駆動開発とリファクタリングの実践
- 継続的インテグレーションの実践

### 前提

| ソフトウェア | バージョン  | 備考 |
|:-------|:-------| :--- |
| nodejs | 16.3.0 |      |
| java   | 17.0.0 |      |

#### Quick Start

```bash
docker-compose up -d 

npm install

npm start
```

## Example Domain: WolfDesk

WolfDesk provides a help desk tickets management system as a service. If your start-up company needs to provide support
to your customers, with WolfDesk's solution you can get up and running in no time.

WolfDesk uses a different payment model than its competitors. Instead of charging a fee per user, it allows the tenants
to set up as many users as needed, and the tenants are charged for the number of support tickets opened per charging
period. There is no minimum fee, and there are automatic volume discounts for certain thresholds of monthly tickets: 10%
for opening more than 500 tickets, 20% for opening more than 750 tickets, and 30% for opening more than 1,000 tickets
per month.

To prevent tenants from abusing the business model, WolfDesk's ticket lifecycle algorithm ensures that inactive tickets
are closed automatically, encouraging customers to open new tickets when further support is needed. Moreover, WolfDesk
implements a fraud detection system that analyzes messages and detects cases of unrelated topics being discussed in then
same ticket.

To help its tenants streamline the support-related works, WolfDesk has implemented a "support autopilot" feature. Then
autopilot analyzes new tickets and tries to automatically find a matching solution from the tenant's ticket history. The
functionality allows for further reducing the tickets' lifespans, encouraging customers to open new tickets for further
questions.

WolfDesk incorporates all the security standards and measures to authenticate and authorize its tenants' users and also
allows tenants to configure a single sign-on (SSO) with their existing user management systems.

The administration interface allows tenants to configure the possible values for the tickets' categories, as well as a
list of the tenant's products that it supports.

To be able to route new tickets to the tenant's support agents only during their working hours, WolfDesk allows the
entry of each agent's shift schedule.

Since WolfDesk provides its service with no minimal fee, it has to optimize its infrastructure in a way that minimizes
the costs of onboarding a new tenant. To do that, WolfDesk leverages serverless computing, which allows it to
elastically scale its compute resources based on the operations on active tickets.

### 事例 ドメイン: S社

S社は、地方都市に本社を置くビル管理サービス事業会社である。市内に複数のビルを所有しており、主な事業は不動産賃貸業務及び設備総合管理業務である。

近年、所有物件の稼働率を向上するべく貸会議室運営業務に新たに取り組んでいるが状況は芳しくない。

現在、貸会議室運営業の大半は手作業で行われている。最近、サービスを紹介するWebページと会議室予約システムの初回リリースが行われたが担当者の急な退職により十分な引継ぎが行われなかったため仕様を把握する人間が不在の状況となっている。

利用可能な会議室の登録は手作業でシステムに登録しているため、登録漏れや登録間違いにより予約した会議室が使えない、利用したい会議室がいつも使えないなど利用者からのクレームが発生しておりサービス品質向上が求めらている。
また、プロジェクタやホワイトボードなど付随する設備の要望が上がっているが十分に対応できていない状況である。

S社としては、効率化によるサービス品質向上と会員管理の強化によるサブスクリプションサービスで収益を多様化することで貸会議室運営業務を新たな収益の柱に成長させたいと考えている。

- [参照1](https://www.saycogroup.co.jp/company/about/)
- [参照2](https://www.ekimaekaigi.com/)

## 実践

要件・開発に [CCSR手法](https://masuda220.hatenablog.com/entry/2020/05/27/103750) を適用

![](./docs/images/life_cycle.drawio.svg)

### [要件](./docs/index.adoc)

### [開発](./docs/index.adoc)

### [運用](./docs/index.adoc)
