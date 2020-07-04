package com.ljf.dataStructure.list;

/**
 * @author ：ljf
 * @date ：Created in 2020/5/3 12:51
 * @description： 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * <p>
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * <p>
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * <p>
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * <p>
 * 你能尝试使用一趟扫描实现吗？
 * 链接：https://leetcode-cn.com/problems/remove-nth-node-from-end-of-lis
 * @modified By：
 * @version: 1.0
 */
public class RemoveNthFromEnd {
    /**
     * 快慢指针
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //伪造一个节点，展示情况为节点为1的链表
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        ListNode slow = dummy;
        ListNode fast = dummy;

        //将快指针移动n个节点
        for (int i = 0; i < n + 1; i++) {
            fast = fast.next;
        }

        //快慢指针同时移动，快指针移动到尾节点，
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        //移除掉到倒数第n个节点
        slow.next = slow.next.next;

        return dummy.next;
    }

    static void printNode(ListNode node) {
        ListNode tmp = node;
        while (tmp != null) {
            System.out.print(tmp.val + "\t");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RemoveNthFromEnd nthFromEnd = new RemoveNthFromEnd();
        ListNode head = new ListNode(1);
//        head.next = new ListNode(2);
//        head.next.next = new ListNode(3);
//        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = new ListNode(5);

        printNode(head);
        ListNode node = nthFromEnd.removeNthFromEnd(head, 1);
        printNode(node);
    }
}
