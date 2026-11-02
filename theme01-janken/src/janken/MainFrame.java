package janken;

import javax.swing.*;
import java.awt.*;

/**
 * じゃんけんゲームの画面を作るクラス。
 * 画面の部品（ボタン・ラベルなど）の配置と、操作されたときの処理の呼び出しを担当する。
 * 計算や判定などの処理そのものは JankenGame クラスに書く（共通要件 C-03）。
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        // C-01：タイトルバーにアプリ名を表示する
        setTitle("じゃんけんゲーム");
        // C-02：ウィンドウの×ボタンでアプリを終了する
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ウィンドウの大きさ（幅, 高さ）。作りながら自由に変えてよい
        setSize(650, 500);
        // ウィンドウを画面の中央に表示する
        setLocationRelativeTo(null);


        // じゃんけんの処理を行うクラスを用意する
        JankenGame game = new JankenGame();

        // 画面を上・中央・下の3つに分けて配置する
        setLayout(new BorderLayout());

        // 上部：じゃんけんの結果を表示するラベルを作る
        JLabel resultLabel = new JLabel(
                "じゃんけんを始めよう！",
                SwingConstants.CENTER
        );

        // 結果の文字を大きくする
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 24));

        // 結果を画面の上部に配置する
        add(resultLabel, BorderLayout.NORTH);

        // 中央：グー・チョキ・パーのボタンを横に並べる
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 20, 0));

        // 3つのボタンを作る
        JButton guu = new JButton("✊ グー");
        JButton choki = new JButton("✌️ チョキ");
        JButton paa = new JButton("✋ パー");

        // ボタンに表示する文字の大きさや太さを設定する
        Font font = new Font("SansSerif", Font.BOLD, 28);

        guu.setFont(font);
        choki.setFont(font);
        paa.setFont(font);

        // ボタンの大きさを設定する
        guu.setPreferredSize(new Dimension(130, 130));
        choki.setPreferredSize(new Dimension(130, 130));
        paa.setPreferredSize(new Dimension(130, 130));

        // 3つのボタンを中央のパネルに追加する
        buttonPanel.add(guu);
        buttonPanel.add(choki);
        buttonPanel.add(paa);

        // ボタンの上下と左右に余白をつける
        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(50, 30, 50, 30)
        );

        // ボタンを画面の中央に配置する
        add(buttonPanel, BorderLayout.CENTER);

        // 下部：通算成績を表示するラベルを作る
        JLabel scoreLabel = new JLabel("", SwingConstants.CENTER);

        // 成績の文字を大きくする
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        // 成績をリセットするボタンを作る
        JButton resetButton = new JButton("リセット");

        // 成績とリセットボタンを並べるためのパネルを作る
        JPanel bottomPanel = new JPanel();

        // 成績とリセットボタンをパネルに追加する
        bottomPanel.add(scoreLabel);
        bottomPanel.add(resetButton);

        // 下部のパネルを画面の下に配置する
        add(bottomPanel, BorderLayout.SOUTH);

        // 勝ち・負け・あいこの回数を画面に表示する処理
        Runnable updateScore = () -> scoreLabel.setText(
                "あなたの勝ち：" + game.getWins()
                        + "　あなたの負け：" + game.getLosses()
                        + "　あいこ：" + game.getDraws()
        );

        // じゃんけんを行う処理
        // playerには自分が選んだ手が入る
        java.util.function.Consumer<JankenGame.Hand> play = player -> {

            // コンピュータの手をランダムに決める
            JankenGame.Hand computer = game.getComputerHand();

            // 自分とコンピュータの手を比べて勝敗を判定する
            JankenGame.Result result = game.judge(player, computer);

            // 判定結果に合わせて表示するメッセージを決める
            String message = switch (result) {
                case WIN -> "あなたの勝ち！";
                case LOSE -> "あなたの負け！";
                case DRAW -> "あいこ！";
            };

            // グー・チョキ・パーに対応する絵文字を用意する
            String[] marks = {"✊", "✌️", "✋"};

            // グー・チョキ・パーの日本語の名前を用意する
            String[] names = {"グー", "チョキ", "パー"};

            // 自分の手とコンピュータの手、勝敗を画面に表示する
            // ordinal()を使うと、enumの順番に対応した絵文字と名前を取り出せる
            resultLabel.setText(
                    "<html><center>"
                            + "あなた：" + marks[player.ordinal()]
                            + " " + names[player.ordinal()]
                            + "<br>"
                            + "コンピュータ：" + marks[computer.ordinal()]
                            + " " + names[computer.ordinal()]
                            + "<br><br>"
                            + message
                            + "</center></html>"
            );

            // 新しい成績を画面に表示する
            updateScore.run();
        };

        // グーのボタンが押されたら、グーを選んでじゃんけんをする
        guu.addActionListener(e -> play.accept(JankenGame.Hand.GUU));

        // チョキのボタンが押されたら、チョキを選んでじゃんけんをする
        choki.addActionListener(e -> play.accept(JankenGame.Hand.CHOKI));

        // パーのボタンが押されたら、パーを選んでじゃんけんをする
        paa.addActionListener(e -> play.accept(JankenGame.Hand.PAA));

        // リセットボタンが押されたときの処理
        resetButton.addActionListener(e -> {

            // 今までの勝ち・負け・あいこの回数を0に戻す
            game.reset();

            // 結果表示を最初の状態に戻す
            resultLabel.setText("じゃんけんを始めよう！");

            // 画面に表示されている成績も更新する
            updateScore.run();
        });

        // アプリを起動したときに、最初の成績を表示する
        updateScore.run();
    }
}