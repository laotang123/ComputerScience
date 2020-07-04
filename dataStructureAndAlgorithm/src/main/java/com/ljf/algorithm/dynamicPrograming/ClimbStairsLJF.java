package com.ljf.algorithm.dynamicPrograming;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/13 10:06
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class ClimbStairsLJF {

  /*
  多阶段决策模型：
    贪心，回溯，动态规划
   */
  int res = 0;

  //回溯解决，每上一次楼梯都是一个阶段，这个阶段是相同的。采用递归算法
  //尝试上一个阶梯，然后回溯到两个阶梯
  public int climbStairsBackTrack(int n) {
    //判空和非法数据
    if (n <= 0) {
      return res;
    }

    int currentStair = 0;//当前所在阶梯数
    backTrack(currentStair, n);
    return res;
  }

  private void backTrack(int currentStair, int n) {
    //找到可行解
    if (currentStair == n) {
      res++;
      return;
    } else if (currentStair < n) {
      for (int option = 1; option < 3; option++) {
        //进入下一阶段，上楼梯
        currentStair += option;

        backTrack(currentStair, n);

        //回溯
        currentStair -= option;
      }
    } else {//当前楼梯超过n
      return;
    }
  }

  /*
  动态规划解决问题：
    转态转移表法；
    转态转移方程；
    当前的问题求解只与前两个状态有关，所以状态转移表也具有时效性。转态转移方程更合适
    m[i] = m[i-1] + m[i-2] 当前阶梯数只能由上一个阶梯，或者上上一个阶梯达到。所以将这两个的方案相加就得到当前阶梯的方案总数。
   */
  public int climbStairsDP1(int n) {
    //判空
    if (n <= 0) {
      return 0;
    }
    //构建状态转移表，一维数组
    int[] stairs = new int[n];

    //特殊处理
    if (n == 1 || n == 2) {
      return n;
    }

    stairs[0] = 1;
    stairs[1] = 2;
    //递推stairs[i] = stairs[i-1] + stairs[i-2]
    for (int i = 2; i < n; i++) {
      stairs[i] = stairs[i - 1] + stairs[i - 2];
    }

    return stairs[n - 1];

  }

  /*
  版本2：替换状态转移表，降低空间复杂度
   */
  public int climbStairsDP2(int n) {
    //判空
    if (n <= 0) {
      return 0;
    }

    //特殊处理
    if (n == 1 || n == 2) {
      return n;
    }

    int cur = 0;
    int up = 1;
    int pre = 2;
    //状态转移方程
    for (int i = 2; i < n; i++) {
      cur = up + pre;
      up = pre;
      pre = cur;
    }

    return cur;

  }

  /*
  详看官方题解：斐波那契公式解法
   */

  public static void main(String[] args) {
    ClimbStairsLJF ljf = new ClimbStairsLJF();
    System.out.println(ljf.climbStairsBackTrack(44));
    System.out.println(ljf.climbStairsDP1(44));
    System.out.println(ljf.climbStairsDP2(44));
  }
}
