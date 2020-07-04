package com.ljf.dataStructure.heap;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author ：ljf
 * @date ：Created in 2020/2/15 7:54
 * @modified By：
 * @version: 1.0
 */
public class MedianFinder {

  /**
   * 构造大小顶堆，其中大顶堆存储较大的一半数(n+1/n) 小顶堆存储较小的一半数(n) 中位数结果=MaxHeap.top() / (maxHeap.top +
   * minHeap.top)*0.5
   */
  Queue<Integer> minHeap;
  Queue<Integer> maxHeap;

  public MedianFinder() {
    minHeap = new PriorityQueue<>();
    maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o2 - o1;
      }
    });
  }

  public void addNum(int num) {
    //首先加入最小堆，然后在做平衡操作
    minHeap.offer(num);
    maxHeap.offer(minHeap.poll());


    //平衡条件minHeap.size <= maxHeap.size
    if(maxHeap.size() > minHeap.size()){
      minHeap.offer(maxHeap.poll());
    }

  }

  public double findMedian() {
    return minHeap.size() > maxHeap.size() ? (double) minHeap.peek()
        : (maxHeap.peek() + minHeap.peek()) * 0.5;
  }

  public static void main(String[] args) {
    MedianFinder finder = new MedianFinder();

    finder.addNum(2);
    finder.addNum(3);
    finder.addNum(5);
    finder.addNum(6);
    finder.addNum(1);
    finder.addNum(-1);

    System.out.println(finder.findMedian());
  }
}
