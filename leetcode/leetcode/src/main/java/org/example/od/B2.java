package org.example.od;

import java.util.*;

/**
 * 描述：告警抑制
 *
 * @author xutao
 * @date 2023-05-29 20:39:38
 * @since 1.0.0
 */
public class B2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        // k: 告警 v: 可以抑制 k 的告警列表
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            map.putIfAbsent(line[1], new ArrayList<>());
            map.get(line[1]).add(line[0]);
        }

        // 产生的告警
        List<String> alarms = Arrays.asList(sc.nextLine().split(" "));
        List<String> results = new ArrayList<>();
        for (String alarm : alarms) {
            // 1.不存在可以抑制该告警的告警
            // 2.存在可以抑制该告警的告警，但没有产生这种告警
            if (!map.containsKey(alarm) || Collections.disjoint(alarms, map.get(alarm))) {
                results.add(alarm);
            }
        }

        System.out.println(results);
    }
}
