package com.ljf.algorithm.dynamicPrograming;

import java.util.Arrays;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/16 10:17
 * @modified By：
 * @version: 1.0
 *
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 示例 1:
 *
 * 输入: [1,2,3,1]
 * 输出: 4
 * 解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2:
 *
 * 输入: [2,7,9,3,1]
 * 输出: 12
 * 解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * 链接：https://leetcode-cn.com/problems/house-robber
 */
public class RobLJF {

  /*
  动态规划：
    设置函数f(k)：表示从前k个房屋中抢到的最多金额，Ai表示第i个房屋的金钱
    转移方程：
    f(1) = A1
    f(2) = max(A1,A2)
    f(3) = max(A1+A3,A2)
    f(k) = max(f(k-2)+Ak,f(k-1))

    使用数组表示整个状态过程，由于只用到前两个状态，所以只需要两个变量存储
    初始状态f(-1) = f(0) = 0
   */
  public int rob(int[] nums) {
    int preMax = 0;
    int curMax = 0;

    int temp;//临时变量
    for (int num : nums) {
      temp = curMax;
      curMax = Math.max(preMax + num, curMax);
      preMax = temp;
    }
    return curMax;
  }

  /*
  环形住户，第一家住户和最后一家住户连接；
  分解为两个单排列：
    不包含第一家的最大盗窃金额
    不包含最后一家的盗窃金额
        public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                        myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
   */
  public int robLoop(int[] nums) {
    //判空和特殊值
    if (nums.length == 0) {
      return 0;
    }
    if (nums.length == 1) {
      return nums[0];
    }
    return Math.max(myRobLoop(Arrays.copyOfRange(nums, 0, nums.length - 1)),
        myRobLoop(Arrays.copyOfRange(nums, 1, nums.length)));
  }

  public int myRobLoop(int[] nums) {
    int cur = 0;
    int pre = 0;

    int temp;//临时保存cur
    for (int num : nums) {
      temp = cur;
      cur = Math.max(pre + num, cur);
      pre = temp;
    }

    return cur;
  }

  public static void main(String[] args) {
    int[] nums = {2, 3, 1};
    RobLJF ljf = new RobLJF();
    System.out.println(ljf.rob(nums));
    System.out.println(ljf.robLoop(nums));
  }

}
