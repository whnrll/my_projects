package org.example.回溯算法;

import java.util.*;

public class 包含重复数字的全排列 {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        List<List<Integer>> lists = permuteUnique(nums);
        print(lists);
    }

    public static void print(List<List<Integer>> result) {
        result.forEach(res -> {
            StringJoiner joiner = new StringJoiner(",");
            res.forEach(o -> joiner.add(o+ ""));
            System.out.println(joiner);
        });
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtracking5(res, nums, new LinkedList<>(), new boolean[nums.length]);
        return res;
    }

    // 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
    public static void backtracking5(List<List<Integer>> res, int[] arr, LinkedList<Integer> path, boolean[] used) {
        arr = Arrays.stream(arr).sorted().toArray();

        // 决策结束条件：选择的数的个数等于给定的个数K
        if (path.size() == arr.length) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 第一次决策
        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }

            // 当前选择的和上一次选择的数一样时，跳过。
            if (i >= 1 && !used[i-1] && arr[i] == arr[i -1]) {
                continue;
            }

            path.add(arr[i]);
            used[i] = true;

            // 第i次决策。由于可以重复使用元素
            backtracking5(res, arr, path, used);

            path.removeLast();
            used[i] = false;
        }
    }
}
