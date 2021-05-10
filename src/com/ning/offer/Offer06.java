package com.ning.offer;

/**
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 示例 1：
 * <p>
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 * <p>
 * <p>
 * 限制：
 * <p>
 * 0 <= 链表长度 <= 10000
 */
public class Offer06 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        for (int i : link2Array(node1)) {
            System.out.printf(i + " ");
        }
    }

    public static int[] link2Array(ListNode head) {
//        ListNode node = reverse(listNode);
        StringBuilder sb = new StringBuilder();
        ListNode temp = head;
        while (temp != null) {
            sb.append(temp.val);
            temp = temp.next;
        }
        return sb.reverse().chars().map(c -> c = c - 48).toArray();
    }

    public static ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }
        ListNode pre = null;
        ListNode cur = node;
        ListNode nex;
        while (cur != null) {
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}