package org.example.二叉树;

import java.util.*;

/**
 * 描述：目录删除
 *
 * @author xutao
 * @date 2023-07-20 07:42:17
 * @since 1.0.0
 */
public class Tree2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        HashMap<Integer, HashSet<Integer>> relations = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int child = scanner.nextInt();
            int father = scanner.nextInt();

            relations.putIfAbsent(father, new HashSet<>());
            relations.get(father).add(child);
        }

        int del = scanner.nextInt();

        StringJoiner joiner = new StringJoiner(" ");
        List<Integer> res = new ArrayList<>();
        dfs(relations, 0, del, res);

        res.forEach(o -> joiner.add("" + o));
        System.out.println(joiner);
    }

    public static void dfs(HashMap<Integer, HashSet<Integer>> relations, int root, int del, List<Integer> res) {
        if (del == 0) {
            return;
        }

        if (relations.containsKey(root)) {
            HashSet<Integer> integers = relations.get(root);
            for (Integer integer : integers) {
                if (!integer.equals(del)) {
                    res.add(integer);
                    dfs(relations, integer, del, res);
                }
            }
        }
    }
}
