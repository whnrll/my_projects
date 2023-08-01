package org.example.od;

import java.util.Scanner;

/**
 * 描述：字符串加密
 *
 * @author xutao
 * @date 2023-06-08 21:38:59
 * @since 1.0.0
 */
public class B7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        String[] lines = new String[n];
        for (int i = 0; i < n; i++) {
            lines[i] = scanner.next();
        }

        for (int i = 0; i < lines.length; i++) {
            System.out.println(getResult(lines[i]));
        }
    }

    private static String getResult(String str) {
        int length = str.length();
        long[] arr = new long[length];
        if (length > 0) {
            arr[0] = 1;
        }
        if (length > 1) {
            arr[1] = 2;
        }
        if (length > 2) {
            arr[2] = 4;
        }

        for (int i = 3; i < length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }

        // 字符串加密规则 str[i] = (str[i] + arr[i] - 97) % 26 + 97
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) ((chars[i] + arr[i] - 97) % 26 + 97);
        }

        return new String(chars);
    }
}
