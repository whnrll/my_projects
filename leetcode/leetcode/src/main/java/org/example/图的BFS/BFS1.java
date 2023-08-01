package org.example.图的BFS;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 描述：宜居星球改造。初始化一个n*m的区域，每个格子可选值，yes,no,na。 其中yes代表宜居区域，no代表可改造区域，na代表无法改造区域。 每个自然日，宜居区域回向上下左右四个方向扩散，但是无法穿过na区域。
 * 求：最少需要几个自然日，可改造完成
 *
 * @author xutao
 * @date 2023-07-19 19:25:15
 * @since 1.0.0
 */
public class BFS1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String[]> matrix = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if ("".equals(nextLine)) {
                System.out.println(getResult(matrix));
                break;
            } else {
                matrix.add(nextLine.split(" "));
            }
        }


    }

    public static int getResult(List<String[]> matrix) {
        int row = matrix.size();
        int col = matrix.get(0).length;

        // 需要改造的区域
        List<int[]> points = new ArrayList<>();
        // 待改造的区域数量
        int need = 0;

        // 初始化
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                String val = matrix.get(i)[j];
                if (val.equals("yes")) {
                    points.add(new int[] {i, j});
                } else if (val.equals("no")) {
                    need++;
                }
            }
        }

        // 无需改造的场景
        if (points.size() == 0) {
            return -1;
        }
        if (points.size() == row * col) {
            return 0;
        }


        // 使用BFS统计改造区域
        int[][] offsets = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int day = 0;
        while (points.size() > 0 && need > 0) {
            List<int[]> temp = new ArrayList<>();


            for (int[] point : points) {
                // 遍历可改造区域的上下左右四个方向，如果可以改造，将该区域改为yes
                for (int[] offset : offsets) {
                    int newR = point[0] + offset[0];
                    int newC = point[1] + offset[1];

                    // 检查是否可以改造
                    if (newR >=0 && newR< row && newC >= 0 && newC < col && "no".equals(matrix.get(newR)[newC])) {
                        matrix.get(newR)[newC] = "yes";
                        temp.add(new int[] {newR, newC});
                        need--;
                    }
                }
            }

            day++;
            points = temp;
        }

        return need == 0 ? day : -1;
    }
}
