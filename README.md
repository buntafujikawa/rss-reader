# rss-reader
## 概要
AWSの情報収集に特化したRSSリーダーっぽいアプリ  
勉強していた本のサンプルアプリを参考にRSSリーダーを作って、そこにAWSの情報を収集できるタブを追加していった

[![https://gyazo.com/6356c86b7424d8484fc89a8b9d501d3c](https://i.gyazo.com/6356c86b7424d8484fc89a8b9d501d3c.gif)](https://gyazo.com/6356c86b7424d8484fc89a8b9d501d3c)

[RSS for AWS](https://play.google.com/store/apps/details?id=com.sugosumadesu.buntafujikawa.rssreader)という名前で公開してるよー


## 機能
- RSSフィードの追加、削除
- AWSの最新情報を表示
- AWSの障害情報を表示(tokyoリージョンのEC2のみ)
- AWS Japanの公式ツイッターアカウントのツイートを表示
- 通知(たぶん動いてない)

## コンセプト
一言で自分がandroid+kotlinを勉強をするためのアプリ  
そのためユーザビリティはあまり考慮していない(また今度)  
設計の部分でいろいろと辛いところが出てきたので、このアプリに手を加えていくよりも新しく他のアプリを作る予定

## 参考
- [基本からしっかり身につくAndroidアプリ開発入門 Android Studio 2.x対応 プロが本気で教えるアプリ作りの基本「技」 (ヤフー黒帯シリーズ)](https://www.amazon.co.jp/%E5%9F%BA%E6%9C%AC%E3%81%8B%E3%82%89%E3%81%97%E3%81%A3%E3%81%8B%E3%82%8A%E8%BA%AB%E3%81%AB%E3%81%A4%E3%81%8FAndroid%E3%82%A2%E3%83%97%E3%83%AA%E9%96%8B%E7%99%BA%E5%85%A5%E9%96%80-Android-Studio-%E3%83%97%E3%83%AD%E3%81%8C%E6%9C%AC%E6%B0%97%E3%81%A7%E6%95%99%E3%81%88%E3%82%8B%E3%82%A2%E3%83%97%E3%83%AA%E4%BD%9C%E3%82%8A%E3%81%AE%E5%9F%BA%E6%9C%AC%E3%80%8C%E6%8A%80%E3%80%8D-%E3%83%A4%E3%83%95%E3%83%BC%E9%BB%92%E5%B8%AF%E3%82%B7%E3%83%AA%E3%83%BC%E3%82%BA/dp/4797384506/ref=pd_sim_14_1?_encoding=UTF8&psc=1&refRID=P6W64G9AMD5CCXPTZ0JP)
- [ViewPager + TabLayout + NavigationView + CoordinatorLayout](https://qiita.com/Yuki_Yamada/items/50e45252cfdf76731811)
- [RxJava+RetrofitでAPI通信周りを実装するうえで最低限の知識を30分で詰め込む](https://qiita.com/FumihikoSHIROYAMA/items/201536d9b45ef21b6bc7)

## KPT
trelloでやると次始める頃には削除してるかもしれないので、ここでKPTやっちゃう

## Keep
- 週次で開発の進捗に対して目標を設定できていたので、公開までモチベーションを維持できた
- Retrofit,RxJavaに関しては、リポジトリを作成して最小単位での検証ができたので、勉強しやすい&後で振り返りやすかった

## Problem
- 一つのアプリをずっと触ってるとどんどん飽きて手抜きになってきた(issue書くだけ、todo書くだけ)
- ベースにしたサンプルアプリに古いところがあり、後から他のものを追加するときに結構辛かった
- そういえば実機で試していないので、実際に使ってみた感じがわからない

## Try
- 次アプリ作る時もいつまでにやるかをどこかで公言しておく(エラスティックリーダーシップのコミットメント言語的な)
- 引き続き新しいライブラリとか使うときは、いきなりアプリ内に組み込まないで最小単位での検証をしてみる
- 次作るとしたら自分が使いたいと思うものにする
- 検証用にAndroidの端末を買おう(iPhone寿命だしそっちメインにしようか悩む)

