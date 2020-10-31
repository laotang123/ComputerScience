package com.ljf.algorithm.Hot30;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/28 13:30
 * @description： 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * <p>
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 * 链接：https://leetcode-cn.com/problems/sort-list
 * @modified By：
 * @version: 1.0
 */

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class SortList {
    //归并排序，合并两个有序列表
    public ListNode sortList(ListNode head) {
        return new ListNode(0);
    }
}
