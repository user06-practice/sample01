import java.util.Arrays;

public class Homework0621 {

    // 硬貨と硬貨の枚数が順番になっている配列を2つ用意

    // 商品金額入力
    // 預かる硬貨枚数を受ける
    // 入力した硬貨の枚数は、枚数配列に今ある数と合計される

    // お釣り金額計算と、お釣りの最適解計算をする
    // 払い出されるお釣りの硬貨の枚数を表示する
    // 枚数配列から数が減っていく。

    // 現在のお釣りの枚数を表示する


    // 大きい金額の金種がない場合、小さい金種で払い出す。
    // 払える金種がない場合、【お釣りなし】表示して、処理を抜ける。

    static void main() {

        int[] coins = {500, 100, 50, 10, 5, 1};
        int[] num = {10, 10, 10, 10, 10, 10};

        //全体をwhile trueで無限ループさせて、釣銭のプールが0になるまでループし続ける。
        while (true) {

            String input_payment = IO.readln("商品の金額を入力：");
            int payment = Integer.parseInt(input_payment);

            int money_total = 0;
            //預かる硬貨の枚数を順番に更新していく。
            for (int i = 0; i <= coins.length - 1; i++) {
                String input_money = IO.readln("預かる" + coins[i] + "円玉の数を入力：");
                int money = Integer.parseInt(input_money);
                money_total = money_total + (coins[i] * money);
                num[i] = num[i] + money;
            }
            IO.println("預かり金額合計：" + money_total);

            //預かった金額-商品金額をする
            int change = money_total - payment;

            //お釣りの金額を表示
            IO.println("お釣り金額：" + change);

            //プールにお釣りを払い出せる枚数があるかどうかチェック
            //払い出し予定の枚数より、今ある枚数が多い
            //または、次の金種の枚数 * 金額がchangeを超える

            //金種のプール枚数を順番に見ていく
            for (int i = 0; i <= num.length - 1; i++) {
                //見ているプール枚数が今回の計算でなくならない場合、最適解の金種で計算する。
                if (num[i] > change / coins[i]) {
                    IO.println(coins[i] + "円玉は：" + (change / coins[i]) + "枚");
                    num[i] = num[i] - (change / coins[i]);
                    change %= coins[i];

                    //１円玉だけキャッチ。　iのインデックスが5かつ、1の位のお釣り払い出しがプールの1円玉より多いとき。
                } else if (i == 5 && change / coins[i] > num[i]) {
                    IO.println("お釣りがありません。");
                    return;

                    //最適解の金種がなく、次に大きい金種で計算する
                } else if ((change / coins[i + 1]) * (num[i + 1]) < coins[i + 1] * num[i + 1]) {
                    IO.println(coins[i + 1] + "円玉は：" + (change / coins[i + 1]) + "枚");
                    num[i + 1] = num[i + 1] - (change / coins[i + 1]);
                    change %= coins[i + 1];
                    //次の大きい金種を使用したため、インデックスを1個飛ばす。
                    i = i + 1;
                }
            }
            IO.println("現在のnumの中身は：" + Arrays.toString(num));
        }
    }
}


