package org.example.双指针;

import org.example.datastruct.ListNode;

/**
 * 描述：21. 合并两个有序链表 https://leetcode.cn/problems/merge-two-sorted-lists
 *
 * @author xutao
 * @date 2023-05-26 19:56:19
 * @since 1.0.0
 */
public class _21_Solution {

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
