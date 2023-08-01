package org.example.双指针;

import org.example.datastruct.ListNode;

/**
 * 描述：86. 分隔链表
 * https://leetcode.cn/problems/partition-list/
 *
 * @author xutao
 * @date 2023-05-26 20:44:58
 * @since 1.0.0
 */
public class _86_Solution {
    public ListNode partition(ListNode head, int x) {
        // 虚拟头节点1，保存值小于 x 的链表
        ListNode dummy1 = new ListNode();
        ListNode d1 = dummy1;

        // 虚拟头节点2，保存值大于等于 x 的链表
        ListNode dummy2 = new ListNode();
        ListNode d2 = dummy2;

        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                d1.next = node;
                d1 = d1.next;
            } else {
                d2.next = node;
                d2 = d2.next;
            }

            // 断开原链表的每个 next 指针，避免成环
            ListNode temp = node.next;
            node.next = null;
            node = temp;
        }

        // 合并两个链表
        d1.next = dummy2.next;

        return dummy1.next;
    }
}
