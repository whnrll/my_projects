package org.example.动态规划;

import java.util.Scanner;
import java.util.StringJoiner;

public class 字符串匹配 {

    // 输入获取
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] arr = sc.nextLine().split(" ");
        String reg = sc.nextLine();

        System.out.println(getResult(arr, reg));
    }

    // 算法入口
    public static String getResult(String[] arr, String reg) {
        StringJoiner sj = new StringJoiner(",");

        for (int i = 0; i < arr.length; i++) {
            if (isMatch(arr[i], reg))
                sj.add(i + "");
        }

        if (sj.length() == 0)
            return "-1";
        else
            return sj.toString();
    }

    public static boolean isMatch(String s, String p) {
        s = " " + s;
        p = " " + p;

        int n = s.length();
        int m = p.length();

        boolean[][] dp = new boolean[n][m];
        dp[0][0] = true;

        for (int i = 0; i < n; i++) {
            // 内层循环遍历的是正则p的范围，如果j=0.那么代表正则为空，此时匹配结果必然为false，而dp数组初始化时所有元素都初始化为了false，因此这里j可以从1开始
            for (int j = 1; j < m; j++) {
                if (p.charAt(j) == '*') {
                    dp[i][j] = (j >= 2 && dp[i][j - 2]) || (eq(p.charAt(j - 1), s.charAt(i)) && i >= 1 && dp[i - 1][j]);
                } else {
                    dp[i][j] = eq(p.charAt(j), s.charAt(i)) && i >= 1 && dp[i - 1][j - 1];
                }
            }
        }

        return dp[n - 1][m - 1];
    }

    public static boolean eq(char p, char s) {
        return p == s || p == '.';
    }
}