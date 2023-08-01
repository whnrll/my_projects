package org.example.datastruct;

/**
 * 描述：单链表
 *
 * @author xutao
 * @date 2023-05-26 19:57:44
 * @since 1.0.0
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }

    /**
     * 描述：创建一个单链表
     *
     * @param args 给定可变数组
     * @return {@link ListNode } 返回链表头节点
     */
    public static ListNode of(int... args) {
        if (args.length == 0) {
            return null;
        }

        ListNode head = new ListNode(args[0]);
        ListNode p = head;
        for (int i = 1; i < args.length; i++) {
            p.next = new ListNode(args[i]);
            p = p.next;
        }

        return head;
    }

    /**
     * 描述：从指定节点开始打印单链表
     *
     * @param node 给定的节点
     * @return {@link String }
     */
    public static String print(ListNode node) {
        StringBuilder sb = new StringBuilder();

        while (node != null) {
            sb.append(node.val).append(" -> ");
            node = node.next;

        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ListNode head = ListNode.of(1, 2, 3, 4, 5);
        System.out.println(ListNode.print(head));
        System.out.println(head.val);
        System.out.println(ListNode.print(head));
    }
}
