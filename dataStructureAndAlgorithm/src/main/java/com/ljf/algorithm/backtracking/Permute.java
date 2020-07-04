package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/4 14:25
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/permutations/
 */
public class Permute {

  public void backtrack(int n,
      ArrayList<Integer> nums,
      List<List<Integer>> output,
      int first) {
    // if all integers are used up
    if (first == n) {

      output.add(new ArrayList<Integer>(nums));
      return;
    }
    for (int i = first; i < n; i++) {
      // place i-th integer first
      // in the current permutation
      Collections.swap(nums, first, i);
      // use next integers to complete the permutations
      backtrack(n, nums, output, first + 1);
      // backtrack
      Collections.swap(nums, first, i);
    }
  }

  public List<List<Integer>> permute(int[] nums) {
    // init output list
    List<List<Integer>> output = new LinkedList();

    // convert nums into list since the output is a list of lists
    ArrayList<Integer> nums_lst = new ArrayList<Integer>();
    for (int num : nums) {
      nums_lst.add(num);
    }

    int n = nums.length;
    backtrack(n, nums_lst, output, 0);
    return output;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3};
    Permute permute = new Permute();
    System.out.println(permute.permute(nums));
  }
}
