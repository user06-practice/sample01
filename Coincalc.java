//リスト使うために必要？→必要だから書いている。Listという型、機能を使うため
import java.util.List;

//クラスの定義？→たい焼きの焼き型
public class Coincalc {
    //クラスの中でメインのメソッドを書いている？→Javaのプログラムを実行したときの最初に呼ばれる入り口。
    //たい焼きの最初の調理手順。Pythonでいう、上から処理始まるのと同じ
    public static void main(String[] args) {
        //ユーザーからの入力を受け取る。受け取った値はstring？→IO.readln() で、入力された内容は 文字列 String型
        var input = IO.readln("お釣りの金額を入力してください：");
        //受け取った値をintに変換
        int change = Integer.parseInt(input);
        //変数coinsにリスト内の要素を代入。Javaでのリストの書き方はList.of()？→その通り
        var coins = List.of(500, 100, 50, 10, 5, 1);
        //for分でループさせる。変数iは初期値が０。ループ条件は、i < リストの要素数より小さい間。iは＋１されていく。
        for (int i = 0; i < coins.size(); i++) {
            //変数coinsに変数iをインデックスとして引数を渡してgetメソッドを実行し、
            // 変数coinへ順番に代入changeしている
            var coin = coins.get(i);
            //変数coinはリスト内の要素が順番に入る。500→１の順番。変数changeはユーザー入力で受け取った金額
            IO.println(coin + "円玉は：" +(change / coin) + "枚");
            //変数changeは、変数changeを変数coinで500→１の順番で割った余りが代入され
            // 変数changeは余りの金額に更新されていく。
            change = change % coin;
                    }
    }
}