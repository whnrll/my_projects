package org.example.并查集;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 有几个边界 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int m = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] area = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                area[i][j] = scanner.nextInt();
            }
        }

        System.out.println(getResult(area, m, n));
    }

    private static int getResult(int[][] area, int m, int n) {
        int[][] offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        List<int[]> fives = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (area[i][j] == 5) {
                    fives.add(new int[] {i, j});
                }
            }
        }

        // 边界检查
        if (fives.size() == 0 || fives.size() == m * n) {
            return 0;
        }

        // 将5和周围的1连通
        UF uf = new UF(fives.size());
        for (int i = 0; i < fives.size(); i++) {
            int[] a = fives.get(i);
            int x = a[0];
            int y = a[1];
            for (int j = i + 1; j < fives.size(); j++) {
                int[] b = fives.get(j);
                int newX = b[0];
                int newY = b[1];

                // 只要满足如下条件，说明两个点的边界是相连的
                if (Math.abs(x - newX) <= 3 && Math.abs(y - newY) <= 3) {
                    uf.union(i, j);
                }
            }
        }

        return uf.count;
    }

    private static class UF {
        private int count;
        private int[] fa;

        public UF(int count) {
            this.count = count;
            this.fa = new int[count];
            for (int i = 0; i < fa.length; i++) {
                fa[i] = i;
            }
        }

        public int getCount() {
            return count;
        }

        public void union(int p, int q) {
            int pRoot = find(p);
            int qRoot = find(q);
            if (pRoot != qRoot) {
                fa[pRoot] = qRoot;
                count--;
            }
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int find(int p) {
            if (fa[p] != p) {
                fa[p] = find(fa[p]);
            }

            return fa[p];
        }
    }
}
