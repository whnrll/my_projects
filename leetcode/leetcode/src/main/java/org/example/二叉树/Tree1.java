package org.example.二叉树;

import java.util.*;

/**
 * 描述：完全二叉树后续遍历，打印非叶子节点
 *
 * @author xutao
 * @date 2023-07-20 07:05:14
 * @since 1.0.0
 */
public class Tree1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        List<Integer> res = new ArrayList<>();
        dfs(array, 0, res);

        StringJoiner joiner = new StringJoiner(" ");
        res.forEach(obj -> joiner.add(obj + ""));
        System.out.println(joiner);
    }

    public static void dfs(int[] array, int root, List<Integer> res) {
        // 以数组形式表示的完全二叉树满足以下条件
        // 对根节点是arr[i]的元素，其左孩子是arr[2*i+1]，其右孩子是arr[2*i+2]
        int left = 2 * root + 1;
        int right = 2 * root + 2;

        if (left < array.length) {
            dfs(array, left, res);
            if (right < array.length) {
                dfs(array, right, res);
            }
            res.add(array[root]);
        }

    }
}
