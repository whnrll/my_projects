package org.example.回溯算法;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

public class 可重复使用的全排列 {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3};
        getResult1(arr);
        getResult2(arr);
        getResult3(arr);
        getResult4(arr);


    }

    public static void print(List<LinkedList<Integer>> result) {
        result.forEach(res -> {
            StringJoiner joiner = new StringJoiner(",");
            res.forEach(o -> joiner.add(o+ ""));
            System.out.println(joiner);
        });
    }

    public static void getResult1(int[] arr) {
        List<LinkedList<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        int k = 2;
        // 求k个元素的所有排列（元素可以重复使用）
        System.out.println(k +"个元素的所有排列（元素可以重复使用）");
        backtracking(res, arr, path, k);

        print(res);
    }

    public static void getResult2(int[] arr) {
        List<LinkedList<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        boolean[] used = new boolean[arr.length];

        int k = 2;
        // 求k个元素的所有排列（元素不可以重复使用）
        System.out.println(k +"个元素的所有排列（元素不可以重复使用）");
        backtracking3(res, arr, path, k, used);

        print(res);
    }

    public static void getResult3(int[] arr) {
        List<LinkedList<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        int k = 2;

        // 求k个元素的所有组合（元素不可以重复使用）
        System.out.println(k +"个元素的所有组合（元素不可以重复使用）");
        backtracking2(res, arr, path, k, 0);

        print(res);
    }

    public static void getResult4(int[] arr) {
        List<LinkedList<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();

        // 求数组的所有子集
        System.out.println("数组的所有子集");
        backtracking4(res, arr, path, 0);

        print(res);
    }

    private static void backtracking4(List<LinkedList<Integer>> res, int[] arr, LinkedList<Integer> path, int index) {
        // 收集结果，即每一个路径
        res.add(new LinkedList<>(path));

        // 第i次决策从index开始选择
        for (int i = index; i < arr.length; i++) {
            path.add(arr[i]);

            backtracking4(res, arr, path, i + 1);

            path.removeLast();
        }

    }

    /**
     * 描述：可重复使用元素的K个元素全排列
     *
     * @param res
     * @param arr
     * @param path
     * @param k
     */
    public static void backtracking(List<LinkedList<Integer>> res, int[] arr, LinkedList<Integer> path, int k) {
        // 决策结束条件：选择的数的个数等于给定的个数K
        if (path.size() == k) {
            res.add(new LinkedList<>(path));
            return;
        }

        // 第一次决策
        for (int i = 0; i < arr.length; i++) {
            path.add(arr[i]);

            // 第i次决策。由于可以重复使用元素
            backtracking(res, arr, path, k);

            path.removeLast();
        }
    }

    /**
     * 描述：不可重复使用元素的K个元素的所有排列
     *
     * @param res
     * @param arr
     * @param path
     * @param k
     */
    public static void backtracking3(List<LinkedList<Integer>> res, int[] arr, LinkedList<Integer> path, int k, boolean[] used) {
        // 决策结束条件：选择的数的个数等于给定的个数K
        if (path.size() == k) {
            res.add(new LinkedList<>(path));
            return;
        }

        // 第一次决策
        for (int i = 0; i < arr.length; i++) {
            // 防止重复使用元素
            if (used[i]) {
                continue;
            }

            path.add(arr[i]);
            used[i] = true;

            // 第i次决策。
            backtracking3(res, arr, path, k, used);

            path.removeLast();
            used[i] = false;
        }
    }

    /**
     * 描述：不可重复使用元素的K个元素的所有组合
     *
     * @param res
     * @param arr
     * @param path
     * @param k
     */
    public static void backtracking2(List<LinkedList<Integer>> res, int[] arr, LinkedList<Integer> path, int k, int index) {
        // 决策结束条件：选择的数的个数等于给定的个数K
        if (path.size() == k) {
            res.add(new LinkedList<>(path));
            return;
        }

        // 第一次决策
        for (int i = index; i < arr.length; i++) {
            path.add(arr[i]);

            // 第i次决策。由于可以重复使用元素
            backtracking2(res, arr, path, k, i + 1);

            path.removeLast();
        }
    }


}
