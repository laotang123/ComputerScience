package com.ljf.dataStructure.heap;

import java.util.PriorityQueue;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/14 8:54
 * @modified By：
 * @version: 1.0
 */
public class KthLargest {

  private PriorityQueue<Integer> queue;
  private int Kth;

  public KthLargest(int k, int[] nums) {
    queue = new PriorityQueue<>(k);
    for (int i = 0; i < nums.length; i++) {
      //构建容量为k的小顶堆
      if (queue.size() < k) {
        //直接添加
        queue.add(nums[i]);
      }else{ //判断插入元素和小顶堆堆顶元素,如果当前值比堆顶元素小则不考虑
        if(nums[i] > queue.peek()){
          //保证小顶堆中始终有k个元素，先删除，再添加
          queue.poll();
          queue.add(nums[i]);
        }
      }
    }
    this.Kth = k;
  }

  public int add(int val) {
    if(queue.size()<Kth){
      queue.add(val);
    }else {
      //判断插入元素和小顶堆堆顶元素大小
      if(val > queue.peek()){
        queue.poll();
        queue.add(val);
      }
    }
    return queue.peek();
  }

  public static void main(String[] args) {

    int k = 3;
    int[] arr = {};
    KthLargest kthLargest = new KthLargest(1, arr);
    System.out.println(kthLargest.add(-3));
    System.out.println(kthLargest.add(-2));// returns 5
    System.out.println(kthLargest.add(-4));// returns 5
    System.out.println(kthLargest.add(0));// returns 8
    System.out.println(kthLargest.add(4));// returns 8

  }
}
