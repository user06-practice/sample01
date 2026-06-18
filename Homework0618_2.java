import java.util.Arrays;

public class Homework0618_2 {
    /*
「プロになるJava」練習問題
第13章 「処理の難しさの段階」
*/


    /**
     * 各要素を1つ後ろの要素と比較し、
     * 大きいほうを新しい配列に格納します。
     */
    public class CompareNextValue {

        public static void main(String[] args) {
            var data = new int[]{3, 6, 9, 4, 2, 1, 5};

            var result = new int[data.length];

            /*
             * 最後の要素以外を、
             * 1つ後ろの要素と比較します。
             */
            for (int i = 0; i < data.length - 1; i++) {

                if (data[i] > data[i + 1]) {
                    result[i] = data[i];
                } else {
                    result[i] = data[i + 1];
                }
            }

            /*
             * 最後の要素には比較する次の要素がないため、
             * 元の値をそのまま格納します。
             */
            result[data.length - 1] = data[data.length - 1];

            System.out.println(Arrays.toString(data));
            System.out.println(Arrays.toString(result));
        }
    }
}
