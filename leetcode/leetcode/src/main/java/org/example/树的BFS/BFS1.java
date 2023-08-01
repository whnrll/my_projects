package org.example.树的BFS;

import java.util.*;

/**
 * 描述：
 *
 * @author xutao
 * @date 2023-07-19 21:40:06
 * @since 1.0.0
 */
public class BFS1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, HashSet<String>> tree = new HashMap<>();
        int row = scanner.nextInt();
        for (int i = 0; i < row; i++) {
            String child = scanner.next();
            String father = scanner.next();
            tree.putIfAbsent(father, new HashSet<>());
            tree.get(father).add(child);
        }

        String start = scanner.next();

//        bfs(tree, start);

        List<String> ans = new ArrayList<>();
        dfs(tree, start, ans);
        ans = ans.subList(1, ans.size());
        ans.sort(String::compareTo);
        ans.forEach(System.out::println);
    }

    public static void bfs(Map<String, HashSet<String>> tree, String start) {
        if (!tree.containsKey(start)) {
            System.out.println("");
            return;
        }

        Queue<String> queue = new LinkedList<>(tree.get(start));
        List<String> ans = new ArrayList<>();

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String node = queue.poll();
                ans.add(node);

                HashSet<String> children = tree.get(node);
                if (children!= null) {
                    queue.addAll(children);
                }
            }
        }

        ans.sort(String::compareTo);
        ans.forEach(System.out::println);
    }

    public static void dfs(Map<String, HashSet<String>> tree, String start, List<String> ans) {
        ans.add(start);
        HashSet<String> nodes = tree.get(start);
        if (nodes == null) {
            return;
        }

        for (String node : nodes) {
            dfs(tree, node, ans);
        }
    }
}
