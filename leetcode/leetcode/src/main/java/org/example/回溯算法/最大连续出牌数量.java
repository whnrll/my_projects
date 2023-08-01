package org.example.回溯算法;

import javax.smartcardio.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 描述：4种颜色的牌，每个牌有相应的数字。当颜色或者牌上的数字相同就可以连续出牌。
 * 求最大连续出牌数量
 *
 * @author xutao
 * @date 2023-07-21 19:00:53
 * @since 1.0.0
 */
public class 最大连续出牌数量 {
    private static List<Card> cards = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] cardNums = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        String[] cardColors = scanner.nextLine().split(" ");

        // 初始化扑克牌
        for (int i = 0; i < cardNums.length; i++) {
            cards.add(new Card(cardNums[i], cardColors[i]));
        }


        System.out.println(getResult());

    }

    private static int getResult() {
        // 用于保存结果
        int[] res = new int[1];
        backtracking(0, res, null, new boolean[cards.size()]);

        return res[0];
    }

    private static void backtracking(int count, int[] res, Card pre, boolean[] used) {
        // 更新连续出牌的数量
        res[0] = Math.max(res[0], count);

        // 选择出牌的顺序，第一次出第一张牌
        for (int i = 0; i < cards.size(); i++) {
            // 当前牌被使用
            if (used[i]) {
                continue;
            }

            // 用当前的牌和上一次出的牌做比较
            Card cur = cards.get(i);
            // 如果上一次出了牌，并且颜色和数字都和这次的不一样，那这次就不能出牌
            if (pre != null && !pre.color.equals(cur.color) && pre.num != cur.num) {
                continue;
            }

            // 表示第i张牌被占用
            used[i] = true;

            // 出下一张牌
            backtracking(count + 1, res, cur, used);
            // 撤销上一次选择的牌
            used[i] = false;
        }
    }


    private static class Card {
        private int num;
        private String color;

        public Card(int num, String color) {
            this.num = num;
            this.color = color;
        }

        public int getNum() {
            return num;
        }

        public String getColor() {
            return color;
        }
    }
}
