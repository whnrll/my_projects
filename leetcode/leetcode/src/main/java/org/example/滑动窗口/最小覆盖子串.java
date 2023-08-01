package org.example.滑动窗口;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 描述：给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" ADOBECODEBANC ABC
 * 
 * @author xutao
 * @date 2023-07-20 19:42:20
 * @since 1.0.0
 */
public class 最小覆盖子串 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();

        System.out.println(getMinLength(s1, s2));
    }

    private static String getMinLength(String s1, String s2) {
        // 初始化128长度的数组，索引代表字符，元素值代表字符所需数量。当元素值大于0时，代表是需要的元素
        int[] charArr = new int[128];
        // 存储需要的元素数量
        int count = s2.length();

        // 初始化每种字符所需的数量
        for (int i = 0; i < s2.length(); i++) {
            charArr[s2.charAt(i)]++;
        }

        // 存储满足条件的子串
        List<String> res = new ArrayList<>();

        // 初始化滑动窗口，初始时，窗口大小等于0
        int l = 0, r = 0;
        while (r < s1.length()) {
            // 新增窗口元素
            char add = s1.charAt(r);

            // 新增的元素是所需要的元素，则将需要的对应元素数量-1
            if (charArr[add] > 0) {
                count--;
            }

            // 将对应元素数量-1
            charArr[add]--;

            // 开始缩小窗口，即左边界增大
            // 当所需的元素等于0时，说明当前窗口已经包含了所有需要的元素
            while (count == 0) {
                // 移除左边的元素
                char remove = s1.charAt(l);

                // 移除一个元素，则当前窗口需要的该元素数量+1
                charArr[remove]++;

                // 检查移除的元素是否是需要的元素。即
                if (charArr[remove] > 0) {
                    // 此时需要记录该子串到结果集中
                    if (r + 1 <= s1.length()) {
                        res.add(s1.substring(l, r + 1));
                    }

                    // 同时，更新所需的字符数量
                    count++;
                }

                // 窗口左边界向右滑动
                l++;
            }

            // 窗口右边界不断扩大
            r++;
        }

        if (res.size() == 0) {
            return "";
        }

        List<String> collect =
            res.stream().sorted(Comparator.comparingInt(String::length)).collect(Collectors.toList());
        return collect.get(0);

    }
}
