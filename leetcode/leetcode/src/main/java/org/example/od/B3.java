package org.example.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 描述：黄金宝箱
 *
 * @author xutao
 * @date 2023-05-29 21:03:04
 * @since 1.0.0
 */
public class B3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(target(nums));
    }

    private static int target(int[] arr) {
        // 宝箱位置 j 满足条件：sum(0...j-1) == sum(j+1...n-1)
        int leftSum = 0;
        int rightSum = Arrays.stream(arr).sum() - arr[0];
        for (int i = 1; i < arr.length; i++) {
            leftSum += arr[i - 1];
            rightSum -= arr[i];

            if (leftSum == rightSum) {
                return i;
            }
        }

        return -1;
    }
}
