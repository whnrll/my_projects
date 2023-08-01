package org.example.DFS;

import java.util.Arrays;
import java.util.Scanner;

public class 四子棋 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = array[0];
        int n = array[1];

        int[] colors = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 初始化棋盘空间
        int[][] qiZi = new int[m][n];

        // 代表每一列上下一个可以落子的行号
        int[] rows = new int[n];
        Arrays.fill(rows, m - 1);
        for (int i = 0; i < colors.length; i++) {
            // 落子的列
            int col = colors[i] - 1;
            // 不在棋盘列，或当前列已经落满子，还落子就报错
            if (col < 0 || col > n || rows[col] == 0) {
                System.out.println(i + ",error");
                return;
            }

            // 奇数是红方,偶数是蓝方
            int player = i % 2 == 0 ? 1 : 2;
            int row = rows[col];
            qiZi[row][col] = player;
            // 落子
            rows[col]--;

            // 当落下第7个棋子时，检查当前是否可以构成4个连续棋子（行，列，对角线）
            if (i >= 6) {
                if (siLian(qiZi, player, row, col)) {
                    System.out.println((i + 1) + "," + (qiZi[row][col] == 1 ? "red" : "green"));
                    return;
                }
            }
        }
    }

    public static boolean siLian(int[][] qiZis, int player, int x, int y) {
        int m = qiZis.length;
        int n = qiZis[0].length;
        int[][] offsets = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}};
        for (int[] offset : offsets) {
            int len = 1;

            // 朝一个方向搜索
            int x1 = x, y1 = y;
            while (true) {
                x1 += offset[0];
                y1 += offset[1];

                if (x1 >= 0 && x1 < m && y1 >= 0 && y1 < n && qiZis[x1][y1] == player) {
                    len++;
                } else {
                    break;
                }
            }

            // 朝一个方向的反方向搜索
            int x2 = x, y2 = y;
            while (true) {
                x2 -= offset[0];
                y2 -= offset[1];

                if (x2 >= 0 && x2 < m && y2 >= 0 && y2 < n && qiZis[x2][y2] == player) {
                    len++;
                } else {
                    break;
                }
            }

            return len == 4;
        }

        return false;
    }
}
