package org.example.区间问题;

import java.util.Arrays;
import java.util.Scanner;

public class 无法照明的区间 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int[] arr = new int[m];
        for (int i = 0; i < m; i++) {
            arr[i] = scanner.nextInt();
        }

        // 初始化照明区间
        int[][] lights = new int[m][2];
        for (int i = 0; i < arr.length; i++) {
            lights[i][0] = (i + 1) * 100 - arr[i];
            lights[i][1] = (i + 1) * 100 + arr[i];
        }


        System.out.println(getResult(lights));
    }

    private static int getResult(int[][] lights) {
        // 将区间按照左边界升序，右边界降序
        Arrays.stream(lights).sorted((a, b) -> {
            if (a[0] != b[0]) {
                return a[0] - b[0];
            }

            return b[1] - a[1];
        });

        // 区间的个数
        int n = lights.length;

        // 第一个区间的右边界
        int firstRight = lights[0][1];

        int sum = 0;
        for (int i = 1; i < n; i++) {
            int[] next = lights[i];
            // 如果第二个区间和第一个区间有交集，则合并区间。交集有两种情况，部分交集和完全交集，所以这里取最右边的值
            if (next[0] <= firstRight) {
                firstRight = Math.max(firstRight, next[1]);
            } else {
                // 没有交集，则计算未覆盖区间的长度
                sum += next[0] - firstRight;
                firstRight = next[1];
            }
        }

        return sum;
    }
}
