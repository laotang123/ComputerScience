package com.ljf.dataStructure.tree;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/4 9:41
 * @modified By：
 * @version: $
 */
public class JavaDeque {

  public static void main(String[] args) {
    Deque<Integer> deque = new LinkedList<>();
    deque.addLast(1);
    deque.offerLast(2);
    deque.offerLast(3);
    deque.offerLast(4);

    deque.offerFirst(5);
    deque.offerFirst(6);
    deque.offerFirst(7);
    deque.offerFirst(8);
    System.out.println(deque);

    while (!deque.isEmpty()){
      System.out.println(deque.pollFirst());
    }
  }
}
