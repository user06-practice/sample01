package janken;

import java.util.Random;
/**
 * じゃんけんの手の決定・勝敗判定・通算成績の記録を担当するクラス。
 */
public class JankenGame {

    // TODO: README.md の「必須要件」「実装上の条件」を見ながら、必要なフィールドとメソッドを書く
    // じゃんけんの手
    public enum Hand {
        GUU, CHOKI, PAA
    }

    // じゃんけんの勝敗を定義する
    public enum Result {
        WIN, LOSE, DRAW
    }

    // 勝ち・負け・あいこの回数を記録する
    private int wins = 0;
    private int losses = 0;
    private int draws = 0;

    // コンピュータの手をランダムに決める
    public Hand getComputerHand() {

        // グー・チョキ・パーの中から1つを選ぶ
        Hand[] hands = Hand.values();

        Random random = new Random();

        return hands[random.nextInt(3)];
    }

    // 自分とコンピュータの手を比べて勝敗を判定する
    public Result judge(Hand player, Hand computer) {

        // 自分の手を基準にして勝敗を決める
        Result result = switch (player) {

            case GUU -> switch (computer) {
                case GUU -> Result.DRAW;
                case CHOKI -> Result.WIN;
                case PAA -> Result.LOSE;
            };

            case CHOKI -> switch (computer) {
                case GUU -> Result.LOSE;
                case CHOKI -> Result.DRAW;
                case PAA -> Result.WIN;
            };

            case PAA -> switch (computer) {
                case GUU -> Result.WIN;
                case CHOKI -> Result.LOSE;
                case PAA -> Result.DRAW;
            };
        };

        // 判定結果に合わせて成績を1回増やす
        switch (result) {
            case WIN -> wins++;
            case LOSE -> losses++;
            case DRAW -> draws++;
        }

        // 判定した勝敗を画面側に返す
        return result;
    }

    // 今までの勝ち数を返す
    public int getWins() {
        return wins;
    }

    // 今までの負け数を返す
    public int getLosses() {
        return losses;
    }

    // 今までのあいこ数を返す
    public int getDraws() {
        return draws;
    }

    // 勝ち・負け・あいこの回数をすべて0に戻す
    public void reset() {
        wins = 0;
        losses = 0;
        draws = 0;
    }
}