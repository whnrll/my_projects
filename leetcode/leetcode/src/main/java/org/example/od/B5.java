package org.example.od;

import java.util.Scanner;

/**
 * 描述：单词搜索（在矩阵中搜索给定单词） 回溯算法
 * 5 5
 * HELLOWORLD
 * CPUCY
 * EKLQH
 * CHELL
 * LROWO
 * DGRBC
 *
 * @author xutao
 * @date 2023-05-31 19:02:44
 * @since 1.0.0
 */
public class B5 {
    private static int m;
    private static int n;
    private static String word;
    private static String[] matrix;

    // 记录坐标是否被访问
    private static boolean[][] used;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        word = scanner.next();
        matrix = new String[m];
        used = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            matrix[i] = scanner.next();
        }

        System.out.println(getResult());
    }

    private static String getResult() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                boolean backtrace = backtrace(i, j, 0);
                if (backtrace) {
                    return (i + 1) + " " + (j + 1);
                }
            }
        }

        return "NO";
    }

    private static boolean backtrace(int i, int j, int k) {
        // 搜索结束条件
        if (k == word.length()) {
            return true;
        }

        // 剪枝。越界、已使用或者字符不匹配的跳过
        if (i < 0 || i >= m || j < 0 || j >= n || used[i][j] || matrix[i].charAt(j) != word.charAt(k)) {
            return false;
        }

        // 选择
        used[i][j] = true;
        int newK = k + 1;

        boolean res = backtrace(i - 1, j, newK)
                    || backtrace(i + 1, j, newK)
                    || backtrace(i, j - 1, newK)
                    || backtrace(i, j + 1, newK);

        // 撤销选择
        used[i][j] = false;
        return res;
    }
}
