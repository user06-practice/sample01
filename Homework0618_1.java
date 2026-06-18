public class Homework0618_1 {
    /*
「プロになるJava」練習問題
第13章 「処理の難しさの段階」
*/

    /**
     * 奇数番目の文字と、その次の偶数番目の文字を
     * 入れ替えて出力します。
     */
    public class SwapCharacters {

        public static void main(String[] args) {
            var data = "abcde";

            var builder = new StringBuilder();

            /*
             * 2文字ずつ処理するため、
             * iを2ずつ増やします。
             */
            for (int i = 0; i < data.length(); i += 2) {

                /*
                 * 次の文字が存在する場合は、
                 * 順番を入れ替えて追加します。
                 */
                if (i + 1 < data.length()) {
                    builder.append(data.charAt(i + 1));
                    builder.append(data.charAt(i));
                } else {
                    /*
                     * 最後の文字に組み合わせる文字がない場合は、
                     * そのまま追加します。
                     */
                    builder.append(data.charAt(i));
                }
            }

            var result = builder.toString();

            System.out.println(data);
            System.out.println(result);
        }
    }
}
