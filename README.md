[![Node.js CI](https://github.com/k2works/case-study-mrs/actions/workflows/node.js.yml/badge.svg)](https://github.com/k2works/case-study-mrs/actions/workflows/node.js.yml)

# 会議室予約システムケーススタディ

## 概要

[Spring 徹底入門 Spring Framework による Java アプリケーション開発](https://www.amazon.co.jp/Spring%E5%BE%B9%E5%BA%95%E5%85%A5%E9%96%80-Spring-Framework%E3%81%AB%E3%82%88%E3%82%8BJava%E3%82%A2%E3%83%97%E3%83%AA%E3%82%B1%E3%83%BC%E3%82%B7%E3%83%A7%E3%83%B3%E9%96%8B%E7%99%BA-%E6%A0%AA%E5%BC%8F%E4%BC%9A%E7%A4%BENTT%E3%83%87%E3%83%BC%E3%82%BF-ebook/dp/B01IEWNLBU/ref=sr_1_1?adgrpid=145403684286&dib=eyJ2IjoiMSJ9.IkhrLXKTyEES5Gqfi-zt3hQHvS8afRI4nY-PF4M-jBO2c-HymP_gW8Mh0WnsygQFzMJCbUdDccTeBbKobtfPrJ7YV8hMqeLDrHYgrdJdbYyMDvqZQ5uxeQ7Nt2L5Vr_T7oAh74K99-HbI_wOKI6607MS_e8Ck2_0FbPYfrxsQ4CSePQzSKAvn3vCMD0Yi0yHkfALKzjtst4jS-SkzNS4k-Z0lQZi4E4TJ8_bGxhRk-U.D9OFE6_jsEmPK1IfzH2-3-wfsF1OMsd6UC3zbTcXWrg&dib_tag=se&hvadid=679093390007&hvdev=c&hvqmt=e&hvtargid=kwd-332386918940&hydadcr=1789_13657226&jp-ad-ap=0&keywords=spring%E5%BE%B9%E5%BA%95%E5%85%A5%E9%96%80&qid=1709521920&s=digital-text&sr=1-1) チュートリアルの実装例

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

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/k2works/case-study-mrs)

```bash
./gradlew bootRun
```

### [事例 ドメイン: S社](https://case-study-mrs.vercel.app/docs/index.html)

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
