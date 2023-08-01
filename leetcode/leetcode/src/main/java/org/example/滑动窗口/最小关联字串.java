package org.example.滑动窗口;

import java.util.Scanner;

/**
 * 描述：给定2个字符串s1,s2，如果s2中包含s1的所有字符，则返回该索引位置
 * abc efghicbaiii 5
 * abc efghiccaiii -1
 * @author xutao
 * @date 2023-07-20 19:28:52
 * @since 1.0.0
 */
public class 最小关联字串 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();

        System.out.println(getMinStart(s1, s2));
    }

    public static int getMinStart(String s1, String s2) {
        // 存储s1中所需的字符，字符对应索引，元素值代表每种字符需要的数量。即大于0的所有字符
        int[] needChars = new int[128];
        // 记录需要的字符数量
        int needCount = s1.length();

        // 初始化需要的每种字符数量
        for (int i = 0; i < needCount; i++) {
            needChars[s1.charAt(i)]++;
        }

        // 初始化滑动窗口左边界、右边界、满足条件的关联字串最小起始索引
        int l = 0, r = 0, start = -1;
        while (r < s2.length()) {
            char add = s2.charAt(r);

            // 右边新增的字符是所需要的字符，则更新所需要的字符数量
            if (needChars[add] > 0) {
                needCount--;
            }

            // 将新增的字符所需数量-1
            needChars[add]--;

            // 当需要的字符等于0时，说明当前滑窗已经包含所有需要的字符，这时候开始缩小滑窗
            while (needCount == 0) {
                char remove = s2.charAt(l);
                needChars[remove]++;
                // 当所要移除的字符数量大于0时，说明窗口不能再缩小了，此时检查窗口区间是否等于s1的长度
                if (needChars[remove] > 0) {
                    if (r - l + 1 == s1.length()) {
                        // 如果相等，说明是一个可行解
                        start = l;
                        return start;
                    }
                }

                l++;
            }


            // 不断扩大右边界
            r++;
        }


        return start;
    }
}
