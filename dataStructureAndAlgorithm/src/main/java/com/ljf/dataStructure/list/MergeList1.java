package com.ljf.dataStructure.list;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/6 7:08
 * @modified By：
 * @version: $
 */
public class MergeList1 {

  /**
   * 合并k个链表
   *
   * @return：合并后链表的首节点
   */
  public static ListNode mergeKLists(ListNode... list) {

    //判空
    if (list == null) {
      return null;
    }
    int interval = 1;//循环的步长
    int amount = list.length;

    while (interval < amount) {
      for (int i = 0; i < amount - interval; i += (interval * 2)) {
        list[i] = merge2Lists(list[i], list[i + interval]);
      }
      interval *= 2;
    }

    return list[0];
  }

  /**
   * 合并两个链表，创建一个节点重新连接l1和l2链表
   *
   * @return：ListNode节点
   */
  public static ListNode merge2Lists(ListNode l1, ListNode l2) {
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
    System.out.println(mergeKLists(null));
//    merge2Lists()
    ListNode l1 = new ListNode(1);
    l1.next = new ListNode(4);
    l1.next.next = new ListNode(5);
    l1.next.next.next = new ListNode(7);

    ListNode l2 = new ListNode(2);
    l2.next = new ListNode(3);
    l2.next.next = new ListNode(8);
    l2.next.next.next = new ListNode(10);

    ListNode l3 = merge2Lists(l1, l2);
    ListNode temp = l3;
    while (temp != null){
      System.out.print(temp.val + "\t");
      temp = temp.next;
    }

  }
}
