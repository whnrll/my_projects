package org.example.图的BFS;

import java.util.*;

/**
 * 描述：爬山问题，在一个n*m的格子区域内，每个格子的值表示山的高度，小明从0,0出发，只能爬到相邻格子且格子值相差小于K的格子 求：小明可以爬到最高的山的高度和爬到最高的山的最小距离，如果没有则返回0
 *
 * @author xutao
 * @date 2023-07-19 20:34:55
 * @since 1.0.0
 */
public class BFS2 {
    private static int m;
    private static int n;
    private static int k;

    private static int[][] matrix;

    private static int[][] offsets = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        m = scanner.nextInt();
        n = scanner.nextInt();
        k = scanner.nextInt();
        matrix = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        Map<Integer, Integer> minStepToHeight = getResult();
        int max = minStepToHeight.keySet().stream().max((a, b) -> a - b).orElse(0);
        int minStep = minStepToHeight.get(max);
        System.out.println(max + " " + minStep);
    }

    private static Map<Integer, Integer> getResult() {
        // 存储到达山的高度的最小步数
        Map<Integer, Integer> minStepToHeight = new HashMap<>();
        minStepToHeight.put(matrix[0][0], 0);

        // 记录访问路径
        boolean[][] visited = new boolean[m][n];

        // 从起点(0, 0)开始BFS
        List<int[]> queues = new ArrayList<>();
        queues.add(new int[] {0, 0});
        visited[0][0] = true;

        int step = 0;
        while (queues.size() > 0) {
            List<int[]> temp = new ArrayList<>();
            step++;

            for (int[] queue : queues) {
                int x = queue[0];
                int y = queue[1];
                int preHeight = matrix[x][y];

                for (int[] offset : offsets) {
                    int newX = x + offset[0];
                    int newY = y + offset[1];

                    // 越界的情况跳过
                    if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                        continue;
                    }

                    if (visited[newX][newY]) {
                        continue;
                    }

                    // 检查是否是可以到达的山
                    int curHeight = matrix[newX][newY];
                    if (Math.abs(curHeight - preHeight) <= k) {
                        temp.add(new int[] {newX, newY});
                        visited[newX][newY] = true;

                        // 更新 minStepToHeight
                        if (!minStepToHeight.containsKey(curHeight) || minStepToHeight.get(curHeight) > step) {
                            minStepToHeight.put(curHeight, step);
                        }
                    }
                }
            }

            queues = temp;
        }

        return minStepToHeight;
    }
}
