package org.example.滑动窗口;

import java.util.*;

public class 连续K个宝箱数字和最大值 {
    private static LinkedList<Object> linkedList = new LinkedList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        int k = scanner.nextInt();
        System.out.println(getResult(array, k));
    }

    private static int getResult(int[] array, int k) {
        if (k == array.length) {
            return Arrays.stream(array).sum();
        }

        int maxSum = Integer.MIN_VALUE;

        int l = 0, r = 0, sum = 0;
        while (r < array.length) {
            sum += array[r];
            r++;

            if (r - l == k) {
                maxSum = Math.max(maxSum, sum);
                sum -= array[l];
                l++;
            }
        }

        return maxSum;
    }

    private static void print(LinkedList<Object> linkedList) {
        StringJoiner joiner = new StringJoiner(" ");
        for (int i = 0; i < linkedList.size(); i++) {
            joiner.add(linkedList.get(i) + "");
        }
        System.out.println(joiner);
    }
}
