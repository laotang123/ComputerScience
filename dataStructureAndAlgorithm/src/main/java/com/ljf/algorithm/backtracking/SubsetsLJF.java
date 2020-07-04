package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/3 11:11
 * @modified By：
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 *
 * 说明：解集不能包含重复的子集。
 *
 * 示例:
 *
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 *   [3],
 *   [1],
 *   [2],
 *   [1,2,3],
 *   [1,3],
 *   [2,3],
 *   [1,2],
 *   []
 * ]
https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode/
 */
public class SubsetsLJF {

  int calNum = 0;

  /*
  思路：
    依次遍历nums数组，当前元素为一个list，同时将遍历到的当前元素合并到copy的list中
    例如：num = [1,2,3]
    resList = [[]]
    elem = 1:
      resList = [[],[1]]
    elem = 2:
      resList = [[],[1],[2],[1,2]]
      ...
   */
  public List<List<Integer>> subsets(int[] nums) {
    //定义数据结构
    List<List<Integer>> resList = new ArrayList<>();

    //判空
    if (nums == null || nums.length == 0) {
      return null;
    }

    //遍历nums
    for (int num : nums) {
      int n = resList.size();//动态变化
      ArrayList<Integer> temp = new ArrayList<>();
      temp.add(num);
      resList.add(temp);//添加只含当前元素的list
      for (int i = 0; i < n; i++) {
        ArrayList<Integer> list = new ArrayList<>();
        list.addAll(resList.get(i));//复制
        list.add(num);
        resList.add(list);

        //统计计算次数
        calNum++;
      }
    }

    //添加空集合
    resList.add(new ArrayList<>());
    System.out.println("数组长度：" + nums.length + "\t共计算：" + calNum);
    return resList;
  }

  public static void main(String[] args) {
    SubsetsLJF ljf = new SubsetsLJF();
    int[] nums = {1, 2, 3, 4};
    System.out.println(ljf.subsets(nums));
//    ArrayList<Integer> list = new ArrayList<>();
//    ArrayList<Integer> list1 = new ArrayList<>();
//    list.add(1);
//    list.add(2);
//    list.add(3);
//
//    list1.addAll(list);
//    list1.add(4);
//    System.out.println(list);
//    System.out.println(list1);
  }
}
