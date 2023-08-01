package org.example.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：考古学家
 *
 * @author xutao
 * @date 2023-06-10 16:33:54
 * @since 1.0.0
 */
public class B200_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] words = scanner.nextLine().split(" ");

        getResult(words, n);
    }

    private static void getResult(String[] words, int n) {
        boolean[] used = new boolean[n];
        LinkedList<String> path = new LinkedList<>();
        Set<String> ans = new HashSet<>();

        Arrays.sort(words);

        dfs(words, n, path, used, ans);

        ans.forEach(System.out::println);
    }


    private static void dfs(String[] words, int n, LinkedList<String> path, boolean[] used, Set<String> ans) {
        if (path.size() == n) {
            StringBuilder sb = new StringBuilder();
            for (String s : path) {
                sb.append(s);
            }

            ans.add(sb.toString());
            return;
        }

        for (int i = 0; i < n; i++) {
            // 如果上一次碎片未被使用，且与上一次选择的碎片相同，则剪枝
            if (i > 0 && words[i].equals(words[i - 1]) && !used[i - 1]) {
                continue;
            }

            if (!used[i]) {
                path.addLast(words[i]);
                used[i] = true;

                dfs(words, n, path, used, ans);

                path.removeLast();
                used[i] = false;
            }
        }
    }
}
