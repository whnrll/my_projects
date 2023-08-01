package org.example.二分查找;

public class 包含重复元素的指定K最右侧插入位置 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 5, 5, 7, 8};
        int k = 10;

        int l = 0, r = arr.length - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (arr[mid] < k) {
                l =  mid + 1;
            } else if (arr[mid] > k) {
                r = mid -1;
            } else {
                // 相同时，要查找右边界，则收缩左边界
                l = mid + 1;
            }
        }

        System.out.println("k的插入索引是：" + l);
    }
}
