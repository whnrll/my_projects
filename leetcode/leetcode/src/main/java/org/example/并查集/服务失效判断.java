package org.example.并查集;

import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class 服务失效判断 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[][] services = Arrays.stream(scanner.nextLine().split(",")).map(obj -> obj.split("-")).toArray(String[][]::new);
        String[] down = scanner.nextLine().split(",");
        getResult(services, down);
    }

    public static void getResult(String[][] services, String[] down) {
        HashMap<String, List<String>> relationships = new HashMap<>(services.length);
        HashMap<String, Integer> serviceOrder = new HashMap<>();

        int order = 0;
        for (int i = 0; i < services.length; i++) {
            String child = services[i][0];
            String fa = services[i][1];
            relationships.putIfAbsent(fa, new ArrayList<>());
            relationships.get(fa).add(child);
            relationships.putIfAbsent(child, new ArrayList<>());

            serviceOrder.put(child, order++);
            serviceOrder.put(fa, order++);
        }

        for (String service : down) {
            removeService(relationships, service);
        }

        String[] ans = relationships.keySet().toArray(new String[0]);
        if (ans.length == 0) {
            System.out.println(",");
            return;
        }

        Arrays.sort(ans, (Comparator.comparingInt(serviceOrder::get)));

        StringJoiner joiner = new StringJoiner(",");
        for (String an : ans) {
            joiner.add(an);
        }
        System.out.println(joiner);
    }

    public static void removeService(HashMap<String, List<String>> relationships, String service) {
        if (relationships.containsKey(service)) {
            List<String> downServices = relationships.get(service);
            relationships.remove(service);
            for (String downService : downServices) {
                removeService(relationships, downService);
            }
        }
    }
}
