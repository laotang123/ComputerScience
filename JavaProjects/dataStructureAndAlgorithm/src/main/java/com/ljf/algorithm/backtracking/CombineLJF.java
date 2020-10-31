package com.ljf.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/6 14:43
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/combinations/
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 *
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class CombineLJF {

  /*
  回溯算法实现注意事项：
    1.目标结果找到的条件：list.length = k
    2.如何实现回溯，回到进入该函数之前的状态。add  remove
    3.目标结果的保存，copy save
   */
  int num = 0;

  void backtrack(List<List<Integer>> resList, ArrayList<Integer> tempList, int n, int k,
      int start) {
    //查找到目标结果
    if (tempList.size() == k) {
      resList.add(new ArrayList<>(tempList));
      return;
    }

    for (int i = start; i <= n; i++) {
      //临时列表添加元素
      tempList.add(i);
      //进一步添加元素
      backtrack(resList, tempList, n, k, i + 1);

      //回溯
      tempList.remove(tempList.size() - 1);

    }
  }

  public List<List<Integer>> combine(int n, int k) {
    //非法及不合理数据
    if (k > n || k < 0 || n < 0) {
      return null;
    }

    List<List<Integer>> resList = new ArrayList<>();
    ArrayList<Integer> tempList = new ArrayList<>();

    //回溯算法
    backtrack(resList, tempList, n, k, 1);

    return resList;
  }

  public static void main(String[] args) {
    CombineLJF combineLJF = new CombineLJF();
    System.out.println(combineLJF.combine(5, 2));
    System.out.println("共计算：" + combineLJF.num);
  }
}
