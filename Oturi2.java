import java.util.Scanner;

public class Oturi2 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // お釣りを入力
        System.out.print("お釣りを入力してください：");
        int money = sc.nextInt();

        // 5000円札
        System.out.println("5000円札：" + money / 5000 + "枚");
        money = money % 5000;

        // 1000円札
        System.out.println("1000円札：" + money / 1000 + "枚");
        money = money % 1000;

        // 500円玉
        System.out.println("500円玉：" + money / 500 + "枚");
        money = money % 500;

        // 100円玉
        System.out.println("100円玉：" + money / 100 + "枚");
        money = money % 100;

        // 50円玉
        System.out.println("50円玉：" + money / 50 + "枚");
        money = money % 50;

        // 10円玉
        System.out.println("10円玉：" + money / 10 + "枚");
        money = money % 10;

        // 5円玉
        System.out.println("5円玉：" + money / 5 + "枚");
        money = money % 5;

        // 1円玉
        System.out.println("1円玉：" + money + "枚");

        sc.close();
    }
}