package org.example.od;

import java.util.*;

/**
 * 描述：最小广播时延
 *
 * @author xutao
 * @date 2023-06-10 12:31:37
 * @since 1.0.0
 */
public class B200_1 {
    private static int nodeNum;
    private static int timeDelayRows;

    private static int k;

    public static void main(String[] args) {
//        5 7
//        2 1
//        2 4
//        2 3
//        1 4
//        3 4
//        3 5
//        4 5
//        2
        Scanner scanner = new Scanner(System.in);
        nodeNum = scanner.nextInt();
        timeDelayRows = scanner.nextInt();

        Map<Integer, List<Integer>> relationMap = new HashMap<>();
        for (int i = 0; i < timeDelayRows; i++) {
            int node1 = scanner.nextInt();
            int node2 = scanner.nextInt();

            relationMap.putIfAbsent(node1, new ArrayList<>());
            relationMap.get(node1).add(node2);

            relationMap.putIfAbsent(node2, new ArrayList<>());
            relationMap.get(node2).add(node1);
        }

        k = scanner.nextInt();

        System.out.println(getResult(relationMap));
    }

    private static int getResult(Map<Integer, List<Integer>> relationMap) {
        // dist[i] 表示节点 k 与节点 i 之间的时延
        int[] dist = new int[nodeNum + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = -1;
        dist[k] = 0;

        PriorityQueue<Integer[]> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // 从 k 节点开始遍历，找出到相邻节点的时延
        int src = k;
        while (true) {
            if (relationMap.containsKey(src)) {
                for (Integer node : relationMap.get(src)) {
                    int newDist = dist[src] + 1;
                    if (newDist < dist[node]) {
                        dist[node] = newDist;
                        queue.add(new Integer[]{node, newDist});
                    }
                }
            }

            // 结束条件
            if (queue.isEmpty()) {
                break;
            }

            src = queue.poll()[0];
        }

        // 获取结果
        int[] sorted = Arrays.stream(dist).sorted().toArray();
        return sorted[nodeNum] * 2;
    }
}
