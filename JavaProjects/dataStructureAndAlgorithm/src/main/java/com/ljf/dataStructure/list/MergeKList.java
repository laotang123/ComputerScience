package com.ljf.dataStructure.list;


/**
 * @author ：ljf
 * @date ：Created in 2020/5/3 7:41
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class MergeKList {

    //合并k个链表

    /**
     * 原地合并，返回lists[0]
     *
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        //判空
        if (lists.length == 0 || lists == null) {
            return null;
        }

        int interval = 1;//间隔
        int length = lists.length;

        while (interval < length) {
            for (int i = 0; i < length - interval; i += interval * 2) {
                lists[i] = merge2Lists(lists[i], lists[i + interval]);
            }
            interval *= 2;
        }

        return lists[0];
    }

    public ListNode merge2Lists(ListNode l1, ListNode l2) {
        //合并两个有序链表，哨兵机制
        ListNode head = new ListNode(-1);
        ListNode tmp = head;

        while (l1 != null && l2 != null) {
            //判断l1，l2当前节点的大小
            if (l1.val < l2.val) {
                tmp.next = l1;
                l1 = l1.next;
            } else {
                tmp.next = l2;
                l2 = l2.next;
            }

            tmp = tmp.next;
        }

        tmp.next = l1 == null ? l2 : l1;

        return head.next;
    }

    public static ListNode[] transfer(int[][] nums) {
        int length = nums.length;
        ListNode[] lists = new ListNode[length];

        //TODO: 完善int数组转listNode数组
        return lists;
    }

    public static void printerNode(ListNode node) {
        ListNode tmp = node;
        while (tmp != null) {
            System.out.print(tmp.val + "\t");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeKList kList = new MergeKList();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(6);

        ListNode l2 = new ListNode(2);
        l2.next = new ListNode(4);
        l2.next.next = new ListNode(5);
        l2.next.next.next = new ListNode(7);

        printerNode(l1);
        printerNode(l2);
//        printerNode(kList.merge2Lists(l1, l2));
        ListNode[] lists = {l1, l2};
        printerNode(kList.mergeKLists(lists));

    }
}
