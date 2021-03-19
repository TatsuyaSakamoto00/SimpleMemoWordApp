# SimpleMemoWordApp(簡単な単語帳のようなものです)

ざっくり各Activityの内容を説明すると

[MainActivity.java]  スタートページのためのActivity

[TopActivity.java]   AddActivityに移動のための追加ボタンと、追加した単語リストがListViewによって表示される。またListViewに表示されている各Itemをタップするとアラートダイアログ表示で編集と削除ができる

[AddActivity.java]  editText２つで単語名、単語の意味をかけるようにし、SQliteを使ってベータベースに保存する。

*TopActivity.javaに関する注意*　起動時、初めはListViewは表示されず、追加ボタンからAddActivity.javaで保存してまた戻ってから表示される。
