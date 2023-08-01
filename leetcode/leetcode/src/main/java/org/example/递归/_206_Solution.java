package org.example.递归;

import org.example.datastruct.ListNode;

/**
 * 描述：206.反转链表
 * https://leetcode.cn/problems/reverse-linked-list/
 *
 * @author xutao
 * @date 2023-05-27 16:44:59
 * @since 1.0.0
 */
public class _206_Solution {
    /**
     * 描述：迭代方式反转链表
     *
     * @param head
     * @return {@link ListNode }
     */
    public ListNode reverseList(ListNode head) {
        // 当前节点
        ListNode curr = head;
        // 当前节点的前一个节点
        ListNode prev = null;

        while (curr != null) {
            // 保存下一个节点
            ListNode temp = curr.next;
            // 当前节点指向上一个节点
            curr.next = prev;
            // 同时移动上一个节点和当前节点指针
            prev = curr;
            curr = temp;
        }

        return prev;
    }

    /**
     * 描述：递归方式反转链表
     *
     * @param head
     * @return {@link ListNode }
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode last = reverseList2(head.next);
        head.next.next = head;
        head.next = null;

        return last;
    }


    /**
     * 描述：反转链表的前 n 个节点
     *
     * @param node 链表
     * @param n n 大于等于1，小于等于链表长度
     * @return {@link ListNode }
     */
    public ListNode reverseN(ListNode node, int n) {
        ListNode post = null;
        // 终止条件
        if (n == 1) {
            post = node.next;
            return node;
        }

        ListNode head = reverseN(node.next, n - 1);
        node.next.next = node;
        node.next = post;

        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1,2,3,4,5);
        System.out.println(ListNode.print(head));
        System.out.println("---------------------------");

        ListNode node = new _206_Solution().reverseN(head, 3);
        System.out.println(ListNode.print(node));

    }
}
