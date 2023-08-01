package org.example.并查集;

import java.util.*;

public class 我们是一个团队 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] messages = new int[m][3];
        for (int i = 0; i < m; i++) {
            messages[i][0] = scanner.nextInt();
            messages[i][1] = scanner.nextInt();
            messages[i][2] = scanner.nextInt();
        }

        getResult(n, m, messages);
    }

    private static void getResult(int n , int m, int[][] messages) {
        if (n < 1 || n >= 100000 || m < 1 || m>= 100000) {
            System.out.println("NULL");
            return;
        }

        Arrays.sort(messages, Comparator.comparingInt(a -> a[2]));

        UF uf = new UF(n +1);
        for (int[] message : messages) {
            int p = message[0];
            int q = message[1];
            int cmd = message[2];

            if (p < 1 || p > n || q < 1 || q > n || cmd > 1 || cmd < 0) {
                System.out.println("da pian zi!");
                continue;
            }

            if (cmd == 0) {
                uf.union(p, q);
            } else {
                if (uf.connected(p, q)) {
                    System.out.println("we are a team!");
                } else {
                    System.out.println("we are not team!");
                }
            }

        }

    }

    private static class UF {
        private int[] parents;

        public UF(int n) {
            this.parents = new int[n];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public void union(int p, int q) {
            int rp = find(p);
            int rq = find(q);
            if (rp != rq) {
                parents[rp] = rq;
            }
        }

        public int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }

            return parents[x];
        }
    }
}
