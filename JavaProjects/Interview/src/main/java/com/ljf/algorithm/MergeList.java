package com.ljf.algorithm;

import java.util.LinkedList;

/**
 * @author ：ljf
 * @date ：2020/7/12 7:41
 * @description：合并链表和合并k个链表
 * @modified By：
 * @version: $ 1.0
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        this.val = x;
    }
}

public class MergeList {

    /**
     * 合并两个有序链表，创建临时头结点进行连接；
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode merge2Lists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode tmp = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
            }
            tmp = tmp.next;
        }
        tmp.next = l1 != null ? l1 : l2;
        return head.next;

    }

    /**
     * 分治思想实现k个链表的合并
     *
     * @param lists
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        int interval = 1;
        int length = lists.length;

        while (interval < length) {
            for (int i = 0; i < length - interval; i += interval * 2) {
                lists[i] = merge2Lists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }
        return lists[0];
    }

    public static void printList(ListNode node) {
        ListNode tmp = node;

        while (tmp != null) {
            System.out.print(tmp.val + "\t");
            tmp = tmp.next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);
        System.out.print("链表l1打印：");
        printList(l1);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);
        System.out.print("\n链表l2打印：");
        printList(l2);

        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

//        ListNode l12 = merge2Lists(l1, l2);
//        System.out.print("\n合并12进行打印：");
//        printList(l12);

        ListNode l123 = mergeKLists(new ListNode[]{l1,l2,l3});
        System.out.print("\n 合并123三个链表：");
        printList(l123);
    }
}
