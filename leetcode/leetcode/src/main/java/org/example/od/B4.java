package org.example.od;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * 描述：黄金宝箱（下一个更大的数，环形数组）
 *
 * @author xutao
 * @date 2023-05-29 21:27:59
 * @since 1.0.0
 */
public class B4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] nums = Arrays.stream(scanner.nextLine().split(",")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(nextGreaterElement(nums)));
    }

    private static int[] nextGreaterElement(int[] nums) {
        // 利用单调栈 + 取余（环形数组）
        int n = nums.length;
        int[] res = new int[n];
        LinkedList<Integer> temp = new LinkedList<>();

        // 倒着往栈里放（这样可以保证和原先元素顺序一致）
        for (int i = 2 * n - 1; i >= 0; i--) {
            // 栈不为空，栈顶元素比当前元素小，则弹出
            while (!temp.isEmpty() && temp.peekLast() <= nums[i % n]) {
                temp.removeLast();
            }

            // nums[i % n] 身后更大的元素
            res[i % n] = temp.isEmpty() ? -1 : temp.peekLast();
            temp.addLast(nums[i % n]);
        }

        return res;
    }
}
