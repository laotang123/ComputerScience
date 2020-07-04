package com.ljf.dataStructure.list;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/5 9:28
 * @modified By：
 * @version: $
 */
class LinkedNode {

  int val;
  LinkedNode next;

  public LinkedNode(int val) {
    this.val = val;
  }
}

class LinkedList {

  LinkedNode root, end;
  int length;

  /**
   * 尾插法插入数据
   */
  public void add(int val) {
    if (root == null) {
      root = new LinkedNode(val);
      end = root;
    } else {
      end.next = new LinkedNode(val);
      end = end.next;
    }

    length++;//长度增加1
  }

  /**
   * 展示链表
   */
  public void showList() {
    LinkedNode temp = root;
    if (temp == null) {
      System.out.println("链表为空~");
      return;
    }
    System.out.println("链表长度为：" + length);
    while (temp != null) {
      System.out.print(temp.val + "\t");
      temp = temp.next;
    }
    System.out.println();
  }
}

public class MergeOrderList {

  /**
   * 合并k个有序链表： 2个链表举例： 1.构建数据结构LinkedNode(val,next) 2.新建一个数组，长度=所有数组长度之和
   * 3.构建length=k的数组LinkedNode[]，找到val最大的下标位置，加入新数组 同时更新大val所在node=node.next
   * 4.最后只剩下一个数组时，直接将该数组连接到新创建的数组中
   */
  public static LinkedList mergeOrderList(LinkedList... list) {
    LinkedList resList = new LinkedList();
    LinkedNode[] linkedNodes = new LinkedNode[list.length];

    //初始化linkedNodes
    for (int i = 0; i < list.length; i++) {
      linkedNodes[i] = list[i].root;
    }
    //遍历k个列表中当前最大的node,同时将最大的node向后移node=node.next
    boolean isOver = false;
    int minIndex = 0;
    while (true) {
      if (isOver) {
        break;
      }
      //遍历k个列表，找到最小值
      int minVal = Integer.MAX_VALUE;
      int counter = 0;
      for (int i = 0; i < linkedNodes.length; i++) {
        //空节点，跳过
        if (linkedNodes[i] == null) {
          continue;
        }
        if (counter == 0) {
          minVal = linkedNodes[0].val;
        }
        counter++;
        if (linkedNodes[i].val < minVal) {
          minVal = linkedNodes[i].val;
          minIndex = i;
        }
      }

      //只剩下一个非空链表，直接连接然后返回结果
      if (counter == 1) {
        LinkedNode temp = linkedNodes[minIndex];
        while (temp != null) {
          resList.add(temp.val);
          temp = temp.next;
        }
        isOver = true;
      }
      //将minIndex对应的node后移
      resList.add(linkedNodes[minIndex].val);

      //检查maxIndex是否为链表的最后一位，如果是则重新再找一位
      if (linkedNodes[minIndex].next != null) {
        linkedNodes[minIndex] = linkedNodes[minIndex].next;
      } else {
        for (int i = 0; i < linkedNodes.length; i++) {
          if (i != minIndex && linkedNodes[i] != null) {
            minIndex = i;
          }
        }
      }
      minIndex = 0;

    }

    return resList;
  }

  public static void main(String[] args) {
    LinkedList linkedList1 = new LinkedList();
    linkedList1.add(3);
    linkedList1.add(4);
    linkedList1.add(5);
    linkedList1.add(6);
    LinkedList linkedList2 = new LinkedList();
    linkedList2.add(5);
    linkedList2.add(7);
    linkedList2.add(8);
    linkedList2.add(10);
//    linkedList.showList();
    LinkedList resList = mergeOrderList(linkedList1, linkedList2);
    resList.showList();

  }
}
