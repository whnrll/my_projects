package org.example.od;

import java.util.*;

/**
 * 描述：最长的顺子
 * 3-3-3-3-4-4-5-5-6-7-8-9-10-J-Q-K-A
 * 4-5-6-7-8-8-8
 *
 * @author xutao
 * @date 2023-05-31 20:28:34
 * @since 1.0.0
 */
public class B6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] myCurCards = scanner.nextLine().split("-");
        String[] myCardsOut = scanner.nextLine().split("-");

        // 初始化牌组
        //                      3, 4, 5, 6, 7, 8, 9, 10, J,  Q,  K,  A,      2,  B(小王), C(大王)
        //             0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,     18
        int[] cards = {0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4,  4,  4,  4,  4,  0,  4,  1,      1};

        // 扑克牌 与 cards索引 映射关系
        Map<String, Integer> mapToV = new HashMap<>();
        mapToV.put("3", 3);
        mapToV.put("4", 4);
        mapToV.put("5", 5);
        mapToV.put("6", 6);
        mapToV.put("7", 7);
        mapToV.put("8", 8);
        mapToV.put("9", 9);
        mapToV.put("10", 10);
        mapToV.put("J", 11);
        mapToV.put("Q", 12);
        mapToV.put("K", 13);
        mapToV.put("A", 14);
        mapToV.put("2", 16);
        mapToV.put("B", 17);
        mapToV.put("C", 18);

        // cards索引 和 扑克牌 映射关系
        Map<Integer, String> mapToK = new HashMap<>();
        mapToK.put(3, "3");
        mapToK.put(4, "4");
        mapToK.put(5, "5");
        mapToK.put(6, "6");
        mapToK.put(7, "7");
        mapToK.put(8, "8");
        mapToK.put(9, "9");
        mapToK.put(10, "10");
        mapToK.put(11, "J");
        mapToK.put(12, "Q");
        mapToK.put(13, "K");
        mapToK.put(14, "A");
        mapToK.put(16, "2");
        mapToK.put(17, "B");
        mapToK.put(18, "C");

        // 对手的牌 = 总的牌 - 我拥有的牌 - 我出的牌
        for (String own : myCurCards) {
            cards[mapToV.get(own)]--;
        }
        for (String out : myCardsOut) {
            cards[mapToV.get(out)]--;
        }

        // 求对手可以出的最长的顺子。顺子开始索引范围是[3,10]，最短顺子长度是5
        String ans = "NO_CHAIN";
        int maxLen = 0;
        int left = 3;
        while (left <= 10) {
            List<String> tmp = new ArrayList<>();
            StringJoiner joiner = new StringJoiner("-");

            for (int r = left; r < 16; r++) {
                if (cards[r] > 0) {
                    String card = mapToK.get(r);
                    joiner.add(card);
                    tmp.add(card);
                } else {
                    // 顺子中断
                    if (tmp.size() >= 5 && tmp.size() >= maxLen) {
                        maxLen = tmp.size();
                        ans = joiner.toString();
                    }

                    left = r;
                    break;
                }
            }

            left++;
        }

        System.out.println(ans);
    }
}
