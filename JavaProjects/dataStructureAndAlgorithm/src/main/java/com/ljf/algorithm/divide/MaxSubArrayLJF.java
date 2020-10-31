package com.ljf.algorithm.divide;

import java.util.Random;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/27 8:16
 * @modified By：
 * @version: 1.0
 */
public class MaxSubArrayLJF {

  //暴力算法求解n2
  private int maxSubArrayforce(int[] nums) {
    if (nums.length == 1) {
      return nums[0];
    }
    int res = Integer.MIN_VALUE;
    for (int i = 0; i < nums.length; i++) {
      int cross = crossSum(nums, i);
      res = res > cross ? res : cross;
    }
    return res;
  }

  private int crossSum(int[] nums, int p) {
    //以p点向数组的左右两端试探性求和
    int leftSum = Integer.MIN_VALUE;
    int curSum = 0;
    for (int i = p; i >= 0; i--) {
      curSum += nums[i];
      leftSum = max(leftSum, curSum);
    }

    //p点为起点向数组的右端试探性访问
    int rightSum = Integer.MIN_VALUE;
    curSum = 0;
    for (int i = p + 1; i < nums.length; i++) {
      curSum += nums[i];
      rightSum = max(rightSum, curSum);
    }

    return leftSum + rightSum;
  }

  private int max(int a, int b) {
    return a > b ? a : b;
  }

  //分治算法求解nlogn
  private int maxSubArrayDivide(int[] nums) {
    return helper(nums, 0, nums.length - 1);
  }

  //贪心算法求解O(n)
  private int maxSubArrayGreed(int[] nums) {
    int curSum = nums[0];
    int maxSum = nums[0];

    for (int i = 0; i < nums.length; i++) {
      //如果当前值>当前值+之前值求和，证明之前值为负数
      curSum = max(nums[i], curSum + nums[i]);

      //从多个符合要求的子序列中选取最大值
      maxSum = max(maxSum, curSum);
    }
    return maxSum;
  }

  private int helper(int[] nums, int left, int right) {
    //判断一个值
    if (left == right) {
      return nums[left];
    }

    //中心点
    int p = (left + right) / 2;
    int leftSum = helper(nums, left, p);
    int rightSum = helper(nums, p + 1, right);
    int crossSum = crossSum(nums, left, right, p);

    return max(crossSum, max(leftSum, rightSum));
  }

  private int crossSum(int[] nums, int left, int right, int p) {
    //以p点向数组的左右两端试探性求和
    int leftSum = Integer.MIN_VALUE;
    int curSum = 0;
    for (int i = p; i >= left; i--) {
      curSum += nums[i];
      leftSum = max(leftSum, curSum);
    }

    //p点为起点向数组的右端试探性访问
    int rightSum = Integer.MIN_VALUE;
    curSum = 0;
    for (int i = p + 1; i <= right; i++) {
      curSum += nums[i];
      rightSum = max(rightSum, curSum);
    }

    return leftSum + rightSum;
  }

  public static void main(String[] args) {
    MaxSubArrayLJF maxSubArrayLJF = new MaxSubArrayLJF();
    Random random = new Random(18);
    int num = 100;
    int[] nums = new int[num];
    for (int i = 0; i < num; i++) {
      nums[i] = random.nextInt();
    }
    //TODO 三个计算答案竟然不一样！！！
    System.out.println(maxSubArrayLJF.maxSubArrayforce(nums));
    System.out.println(maxSubArrayLJF.maxSubArrayDivide(nums));
    System.out.println(maxSubArrayLJF.maxSubArrayGreed(nums));
  }
}
