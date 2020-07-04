package com.ljf.algorithm.Hot100;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/11 8:26
 * @modified By：
 * @version: $
 */
class Solution {

  public int[] twoSum(int[] nums, int target) {
//    Arrays.sort(nums);
    int[] res = new int[2];

//    System.out.println("排序后的数组："+Arrays.toString(nums));
    boolean flag = false;
    for (int i = 0; i <nums.length-1 ; i++) {
      //i为滑动窗口起点，j为滑动窗口终点
      for (int j = i+1; j < nums.length; j++) {
        int num1 = nums[i];
        if(nums[j] == target-num1){
          flag = true;
          res[0] = i;
          res[1] = j;
          break;
        }
//        if(nums[j]>target-num1) break;
      }

      if(flag) break;
    }
    return res;
  }

  public int[] twoSum1(int[] nums,int target){
    HashMap<Integer, Integer> hashMap = new HashMap<>();

    //一边遍历数据，一边查找
    for (int i = 0; i <nums.length ; i++) {
      int num2 = target - nums[i];

      if(hashMap.containsKey(num2)){
        return new int[]{hashMap.get(num2),i};
      }
      hashMap.put(nums[i],i);
    }

    throw  new IllegalArgumentException("no two sum solution");
  }
}
public class Question1TwoSum {

  /**
   * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数， 并返回他们的数组下标。 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
   * 思路：排序，然后使用滑动窗口
   */

  public static void main(String[] args) {
    int[] nums = {2, 11, 15, 7};
    int target = 9;
    Solution solution = new Solution();
    int[] res = solution.twoSum1(nums, target);
    System.out.println(Arrays.toString(res));
  }
}
