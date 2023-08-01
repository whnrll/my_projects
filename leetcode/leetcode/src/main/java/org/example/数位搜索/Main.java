package org.example.数位搜索;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String s = sc.next();

        System.out.println(getResult(n, s));
    }

    /**
     * @param n 字符串的每个字符范围都是前N的英语字母（1<=N<=26）
     * @param s 合法的并且没有包含回文串的字符串（长度<=10000）
     */
    public static String getResult(int n, String s) {
        char[] tmp = s.toCharArray();
        int[] arr = new int[tmp.length];
        for (int i = 0; i < tmp.length; i++) arr[i] = tmp[i];
        int max = 97 + n - 1;

        String ans = dfs(arr, 0, true, max, new LinkedList<>());

        if (ans == null) {
            return "NO";
        } else {
            return ans;
        }
    }

    public static String dfs(int[] arr, int level, boolean limit, int max, LinkedList<Integer> path) {
        if (level == arr.length) {
            StringBuilder sb = new StringBuilder();
            for (Integer num : path) sb.append((char) ((int) num));
            return sb.toString();
        }

        int min = limit ? arr[level] : 97;

        for (int i = min; i <= max; i++) {
            // 此步跳过原始字符串
            if (limit && level == arr.length - 1 && i == min) continue;
            //  此步表示含有回文串，如abb这种情况
//            if (level >= 1 && i == path.get(level - 1)) continue;
//            // 此步表示含有回文串，如aba这种情况
//            if (level >= 2 && i == path.get(level - 2)) continue;
            path.add(i);
            String ans = dfs(arr, level + 1, limit && i == min, max, path);
            if (ans != null) return ans; // 找到了不含回文串的字典序下一个字符串，则直接返回
            path.removeLast();
        }

        return null;
    }
}
