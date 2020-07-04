package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;


/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/3 11:40
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/subsets/solution/zi-ji-by-leetcode/
 */
public class Subsets {

  /*
  依次查找子集长度符合[0,n]的子集
  先画图，思路再写代码！！！！！！！！
   */
  List<List<Integer>> output = new ArrayList();
  int n, k;
  int calNum = 0;

  public void backtrack(int first, ArrayList<Integer> curr, int[] nums) {
    // if the combination is done，当前list符合子集长度直接返回
    if (curr.size() == k) {
      output.add(new ArrayList(curr));//这里是复制curr
      return;
    }

    for (int i = first; i < n; ++i) {
      // add i into the current combination
      curr.add(nums[i]);
      // use next integers to complete the combination
      backtrack(i + 1, curr, nums);
      // backtrack
      curr.remove(curr.size() - 1);

      //统计计算次数
      calNum++;
    }
  }

  public List<List<Integer>> subsets(int[] nums) {
    n = nums.length;
    //k表示子集可能的长度，[0,n]
    for (k = 0; k < n + 1; ++k) {
      backtrack(0, new ArrayList<Integer>(), nums);
    }

    System.out.println("数组长度：" + n + "\t共计算：" + calNum);
    return output;
  }

  public static void main(String[] args) {
    int[] nums = {1, 2, 3, 4};
    Subsets subsets = new Subsets();
    System.out.println(subsets.subsets(nums));
  }
}
