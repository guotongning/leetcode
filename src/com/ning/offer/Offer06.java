package com.ning.offer;

import java.util.Arrays;
import java.util.Stack;

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
        ListNode node1 = new ListNode(11);
        ListNode node2 = new ListNode(12);
        ListNode node3 = new ListNode(13);
        node1.next = node2;
        node2.next = node3;
        for (int i : link2Array(node1)) {
            System.out.printf(i + " ");
        }
    }

    public static int[] link2Array(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        int size = stack.size();
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }
        return print;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}