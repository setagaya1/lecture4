
/*世田谷（JAVA.23.05）
第４回講義　課題　Streamを試す
2023/07/02　作成*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        //Listの作成
        List<Integer> integerList = List.of(1,2,3,4,5);
        List<String> nameList = List.of("tanaka", "suzuki", "itou", "gotou", "aoki");

        //中間処理 filter
        integerList.stream() //Listをstreamに変換
                .filter( i -> i % 2 == 0) //中間処理（filterの処理にはtrue or falseに置き換わるものをおく。trueのみ抜き出される。）
                .forEach(System.out :: println); //終端処理（メソッド参照）
        nameList.stream()
                .filter( name -> name.contains("a"))
                .forEach(name -> System.out.println(name)); //メソッド参照ではないVer
        nameList.stream()
                .filter( name -> name.equals("suzuki"))
                .forEach(System.out::println);

        //中間処理 map
        integerList.stream()
                .map( i -> i * 3) //mapは要素を変換する中間処理
                .forEach(System.out::println);
        integerList.stream()
                .filter( i -> i % 2 == 1) //中間処理は複数でもいけるのかーー！
                .map( i -> i *2)
                .forEach(System.out::println);
        nameList.stream()
                .map( name -> name.toUpperCase())
                .forEach(System.out::println);

        //中間処理 sorted
        List<String> newNameList = nameList.stream().sorted().toList();
        System.out.println(newNameList);

        integerList.stream()
                .sorted(Comparator.reverseOrder()) //sortedはそのまま並べ替え。Comparatorインターフェースを使うのが最もシンプルと検索したら出てきたので試してみた。
                .forEach(System.out::println);

        //ミニゲーム　数字並べ替えクイズ
        List<Integer> randomList = new ArrayList<>();
        for (int i=0; i<5; i++){
            randomList.add(new java.util.Random().nextInt(100));
        }
        List<Integer> answerList = randomList.stream()
                .sorted(Comparator.naturalOrder())
                .toList();

        System.out.println("次の数字を昇順で並べ替えた時\"3番目\"の数字を答えてください！");
        long startTime = System.currentTimeMillis();
        System.out.println(randomList);

        try {
            int answer = new java.util.Scanner(System.in).nextInt();
            long finishTime = System.currentTimeMillis();
            long clearTime = TimeUnit.MILLISECONDS.toSeconds(finishTime - startTime);

            if (answer == answerList.get(2)) {
                System.out.println("正解です！");
                System.out.println("クリア時間は" + clearTime + "秒でした！");
            } else {
                System.out.println("残念不正解です…！またチャレンジしてくださいね");
            }
        } catch (InputMismatchException e) {
            System.out.println("回答は半角数字にて入力してください！");
        }
    }
}
