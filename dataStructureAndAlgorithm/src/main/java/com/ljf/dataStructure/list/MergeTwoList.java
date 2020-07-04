package com.ljf.dataStructure.list;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/5 15:12
 * @modified By：
 * @version: $
 */
class ListNode {

  int val;
  ListNode next;

  public ListNode(int val) {
    this.val = val;
  }
}

public class MergeTwoList {

  //合并两个有序链表，将被连接到节点后移
  //node=node.next
  //最终连接非null的链表
  public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode preHead = new ListNode(-1);
    ListNode temp = preHead;

    while (l1 != null && l2 != null) {
      //如果l1小，连接l1
      if(l1.val < l2.val){
        temp.next = l1;
        l1 = l1.next;//后移
      }else {
        temp.next = l2;
        l2 = l2.next;
      }

      temp = temp.next;//临时节点后移
    }

    temp.next = l1 == null?l2:l1;//连接非空链表

    return preHead.next;
  }

  public static void main(String[] args) {
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(5);
    l1.next.next.next = new ListNode(7);

    ListNode l2 = new ListNode(2);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(8);
    l2.next.next.next = new ListNode(9);

    ListNode l4 = new ListNode(3);
    l4.next = new ListNode(12);
    l4.next.next = new ListNode(21);
    l4.next.next.next = new ListNode(90);

    ListNode l3 = mergeTwoLists(l1, l2);
    ListNode root = mergeTwoLists(l3,l4);
    ListNode temp = root;
    while (temp != null){
      System.out.print(temp.val + "\t");
      temp = temp.next;
    }



  }
}
