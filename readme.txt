パスワードのハッシュ化
$ htpasswd -n -B pass
New password: #引数で渡したパスワードを入力
Re-type new password: #引数で渡したパスワードを入力
pass:$2y$05$7IWY4KIY8l52R5XbTfU24uRw679eZeHHiSwtY2sfT5R7eAVGdJ1IS # この値を使用する

-nオプションで出力先を標準出力にし、-Bオプションでbcryptのアルゴリズムを使用するように指定しています。
これで最後の引数(上記の例ではpass)をbcryptでハッシュ化した値が標準出力に表示されます。
その後2回パスワードの入力を求められるので、引数と同様の値を入力し、出力された値をデータベースに登録するパスワードとして使用します。