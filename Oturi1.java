public class Oturi1 {

    public static void main(String[] args) {

        // お釣り
        int money = 784;

        // 割り算で硬貨の枚数を求めて
        // 余りを次の計算に使っていく

        // 500円玉の枚数
        System.out.println("500円玉：" + money / 500 + "枚");

        // 余り(284円)をmoneyに代入
        money = money % 500;

        // 100円玉の枚数
        System.out.println("100円玉：" + money / 100 + "枚");

        // 余り(84円)をmoneyに代入
        money = money % 100;

        // 50円玉の枚数
        System.out.println("50円玉：" + money / 50 + "枚");

        // 余り(34円)をmoneyに代入
        money = money % 50;

        // 10円玉の枚数
        System.out.println("10円玉：" + money / 10 + "枚");

        // 余り(4円)をmoneyに代入
        money = money % 10;

        // 残った金額が1円玉の枚数
        System.out.println("1円玉：" + money + "枚");

    }
}