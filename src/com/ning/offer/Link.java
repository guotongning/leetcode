package com.ning.offer;

/**
 * 链表相关
 *
 * @author <a href="guotongning@58.com">Nicholas</a>
 * @since 1.0.0
 */
public class Link {
    public static void main(String[] args) {
//        Node head = Node.initLinkedNode();
//        System.out.println(reverse(head));

        Node head1 = Node.initLinkedNode();
        Node head2 = Node.initLinkedNode();
        System.out.println(merge(head1, head2));
    }

    //链表反转：假头
    public static Node reverse(Node head) {
        if (head == null) {
            return null;
        }
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            //拿出当前节点的下一个
            Node next = cur.next;
            //反转节点指针
            cur.next = pre;
            //移动指针
            pre = cur;
            cur = next;
        }
        return pre;
    }

    //合并有序链表：非递归
    public static Node merge(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return head1 != null ? head1 : head2;
        }
        //新的头节点
        Node head = head1.val < head2.val ? head1 : head2;
        Node cur1 = head == head1 ? head1 : head2;
        Node cur2 = head == head1 ? head2 : head1;
        Node pre = null;//c1的pre
        Node next = null;//c2的next
        while (cur1 != null && cur2 != null) {
            if (cur1.val <= cur2.val) {
                pre = cur1;
                cur1 = cur1.next;
            } else if (pre != null) {
                next = cur2.next;
                pre.next = cur2;
                cur2.next = cur1;
                pre = cur2;
                cur2 = next;
            }
        }
        pre.next = cur1 == null ? cur2 : cur1;
        return head;
    }
}

class Node {
    Node next;
    int val;

    private Node(int val, Node next) {
        this.val = val;
        this.next = next;
    }

    public static Node initLinkedNode() {
        Node node6 = new Node(6, null);
        Node node5 = new Node(5, node6);
        Node node4 = new Node(4, node5);
        Node node3 = new Node(3, node4);
        Node node2 = new Node(2, node3);
        return new Node(1, node2);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node temp = this;
        while (temp != null) {
            sb.append(temp.val).append("->");
            temp = temp.next;
        }
        return sb.toString();
    }
}
