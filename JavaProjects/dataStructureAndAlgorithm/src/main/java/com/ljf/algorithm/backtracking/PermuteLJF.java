package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/5 12:18
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/permutations/
 */
public class PermuteLJF {
  /*
  无放回的组合问题：
  1.从n个数中挑出一个放到第一个位置 遍历range(0,n)
    2.从n-1个数中挑出一个放到第二个位置 遍历range(0，n-1)
      3.从n-2个数中挑出一个放到第三个位置
        ...
   */


  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> resList = new ArrayList<>();

    //判空
    if (nums == null || nums.length == 0) {
      return null;
    }

    List<Integer> numsList = new ArrayList<>();
    for (int num : nums) {
      numsList.add(num);
    }

    backTrack(resList, numsList, nums.length, 0);

    return resList;
  }

  private void backTrack(List<List<Integer>> resList, List<Integer> nums, int length, int first) {
    //如果将第n位元素排列成功，完成一种可能的排列
    if (first == length) {
      resList.add(new ArrayList<Integer>(nums));
      return;
    }

    for (int i = first; i < length; i++) {
      //从start 到length之间的元素依次放入到数组中,交换是为了防止剩下的数减少
      Collections.swap(nums, first, i);

      backTrack(resList, nums, length, first + 1);//确定数组下一个位置的元素

      //回溯，回到进入递归方法前的状态
      Collections.swap(nums, first, i);
    }
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    PermuteLJF permute = new PermuteLJF();
    System.out.println(permute.permute(nums));
  }
}
