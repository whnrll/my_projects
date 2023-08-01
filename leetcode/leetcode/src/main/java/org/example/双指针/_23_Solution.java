package org.example.双指针;

import org.example.datastruct.ListNode;

/**
 * 描述：23. 合并K个升序链表 https://leetcode.cn/problems/merge-k-sorted-lists/
 *
 * @author xutao
 * @date 2023-05-26 19:56:19
 * @since 1.0.0
 */
public class _23_Solution {

    /**
     * 描述：使用迭代合并多个列表
     *
     * @param lists
     * @return {@link ListNode }
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode first = lists[0];
        for (int i = 1; i < lists.length; i++) {
            first = mergeTwoLists(first, lists[i]);
        }

        return first;
    }

    /**
     * 描述：使用分治合并多个列表
     *
     * @param lists
     * @return {@link ListNode }
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return divide(lists, 0, lists.length -1);
    }

    private ListNode divide(ListNode[] lists, int left, int right) {
        // 只剩一个节点无需再分
        if (left == right) {
            return lists[left];
        }

        int mid = left + (right - left) / 2;
        // 分成左右两个部分
        ListNode l = divide(lists, left, mid);
        ListNode r = divide(lists, mid + 1, right);

        // 合并链表
        return mergeTwoLists(l, r);
    }


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 新链表的虚拟头节点
        ListNode dummy = new ListNode();
        ListNode p = dummy;

        ListNode p1 = list1;
        ListNode p2 = list2;

        // 两个链表都不为 null 时，一直比较大小
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                // 值较小的接到 p 指针
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }

            p = p.next;
        }

        // p1 已经遍历完成，则将 p2 接到 p 的后面
        if (p1 == null) {
            p.next = p2;
        }

        // p2 已经遍历完成，则将 p1 接到 p 的后面
        if (p2 == null) {
            p.next = p1;
        }

        return dummy.next;
    }

}
