package org.example.滑动窗口;

import java.util.Scanner;

/**
 * 描述：给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * @author xutao
 * @date 2023-07-20 20:32:39
 * @since 1.0.0
 */
public class 无重复字符最长子串 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(maxLen(s));
    }

    public static int maxLen(String s) {
//        if(s.length() == 0) {
//            return 0;
//        }
//        if(s.length() == 1) {
//            return 1;
//        }

        // 用于记录窗口内的字符，索引代表字符，元素值代表字符数量
        int[] chars = new int[128];

        // 初始化窗口大小
        int l = 0, r = 0;
        int maxLen = 0;

        while (r < s.length()) {
            // 添加元素到窗口
            char add = s.charAt(r);
            // 更新窗口内元素数量
            chars[add]++;

            r++;

            // 当窗口中某个字符的数量大于1时，说明遇到了重复字符，此时需要将窗口缩小，同时记录此时窗口的长度
            while (chars[add] > 1) {
                char remove = s.charAt(l);
                chars[remove]--;
                l++;
            }

            maxLen = Math.max(maxLen, r - l);
        }

        return maxLen;
    }
}
