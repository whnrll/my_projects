package org.example.回溯算法;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

/**
 * 描述：数组中的元素可以被重复使用。求组合的和为target的所有组合。
 * 注意：任意2个组合，只有元素一样就是一个组合
 *
 * @author xutao
 * @date 2023-07-22 12:33:11
 * @since 1.0.0
 */
public class 组合总和等于K {
    public static void main(String[] args) {
        int[] nums = {2,3,5};
        int target = 8;

        List<List<Integer>> lists = combinationSum(nums, target);
        print(lists);
    }

    public static void print(List<List<Integer>> result) {
        result.forEach(res -> {
            StringJoiner joiner = new StringJoiner(",");
            res.forEach(o -> joiner.add(o+ ""));
            System.out.println(joiner);
        });
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        backtracking(res, path, candidates, target, 0, 0);
        return res;
    }

    private static void backtracking(List<List<Integer>> res, LinkedList<Integer> path, int[] candidates, int target, int sum, int begin) {
        if (sum > target) {
            return;
        }

        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            path.add(candidates[i]);

            // 因为元素可以重复使用，所以下一轮仍然从i开始
            backtracking(res, path, candidates, target, candidates[i] + sum, i);

            path.removeLast();
        }
    }


}
