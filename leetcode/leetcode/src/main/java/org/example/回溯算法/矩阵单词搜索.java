package org.example.回溯算法;

import java.util.Scanner;
import java.util.Stack;

/**
 * 描述：在一个矩阵中，搜索给定单词的首字母坐标。搜索的时候，不能走回头路，可以上下左右行走。
 *
 * @author xutao
 * @date 2023-07-21 19:38:56
 * @since 1.0.0
 */
public class 矩阵单词搜索 {
    private static String[] matrix;
    private static int m;
    private static int n;
    private static String word;

    private static boolean[][] used;
    private static int[][] offsets = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        matrix = new String[m];
        used = new boolean[m][n];

        word = scanner.next();
        for (int i = 0; i < m; i++) {
            matrix[i] = scanner.next();
        }

        getResult();
    }

    private static void getResult() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (backtracking(i, j, 0)) {
                    System.out.println((i + 1) + " " + (j + 1));
                    return;
                }
            }
        }

        System.out.println("NO");
    }

    private static boolean backtracking(int i, int j, int k) {
        // 搜索结束
        if (k == word.length()) {
            return true;
        }

        // 检查是否越界、是否匹配、是否被使用
        if (i < 0 || i >= m || j < 0 || j >= n || word.charAt(k) != matrix[i].charAt(j) || used[i][j]) {
            return false;
        }

        used[i][j] = true;
        int newK = k + 1;

        boolean res = backtracking(i - 1, j, newK)
                    || backtracking(i + 1, j, newK)
                    || backtracking(i, j - 1, newK)
                    || backtracking(i, j + 1, newK);
        used[i][j] = false;
        return res;
    }
}
