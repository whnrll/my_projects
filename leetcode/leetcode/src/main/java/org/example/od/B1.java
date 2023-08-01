package org.example.od;

import java.util.Scanner;

/**
 * 描述：需要打开多少监控器
 *
 * @author xutao
 * @date 2023-05-29 20:04:15
 * @since 1.0.0
 */
public class B1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();

        int[][] matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println(getResult(matrix, m, n));
    }

    private static int getResult(int[][] matrix, int m, int n) {
        // 监视器数量
        int count = 0;

        // 上下左右4个监视器偏移量
        int[][] offsets = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

        // 停车情况
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 当前遍历的位置已停车时，监视器 +1
                if (matrix[i][j] == 1) {
                    count++;
                    // 防止重复统计
                    continue;
                }

                // 当前遍历的位置上下左右方向有停车时，监视器 +1
                for (int[] offset : offsets) {
                    int x = i + offset[0];
                    int y = j + offset[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == 1) {
                        count++;
                        break;
                    }
                }
            }
        }

        return count;
    }
}
