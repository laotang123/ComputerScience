package com.ljf.algorithm.divide;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/26 10:10
 * @modified By：
 * @version: 1.0
 */

import java.util.HashMap;

/**
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 *
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 */
public class MajorityElement {

  /**
   * 维护一个hashMap，<elem,count>
   * maxCount对应res
   */
  public int majorityElement(int[] nums) {
    int res = 0;
    int maxCount = 0;

    HashMap<Integer, Integer> hashMap = new HashMap<>();

    for (int num : nums) {
      int count = hashMap.getOrDefault(num, 0);
      count++;
      hashMap.put(num, count);
      if (count > maxCount) {
        maxCount = count;
        res = num;
      }
    }
    return res;
  }

  //投票算法
  public int vote(int[] nums) {
    int count = 0;
    int candidate = 0;

    for (int num : nums) {
      if (count == 0) {
        candidate = num;
      }
      count += (candidate == num) ? 1 : -1;
    }

    return candidate;
  }

  public static void main(String[] args) {
    MajorityElement majorityElement = new MajorityElement();
    System.out.println(majorityElement.majorityElement(new int[]{2, 2, 1, 1, 1, 2, 2}));
    System.out.println(majorityElement.vote(new int[]{2, 2, 1, 1, 1, 2, 2}));
  }
}
