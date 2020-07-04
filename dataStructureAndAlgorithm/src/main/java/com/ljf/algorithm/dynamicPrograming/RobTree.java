package com.ljf.algorithm.dynamicPrograming;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/20 11:04
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/house-robber-iii/solution/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
 * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，
 * 每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
 * 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
 *
 * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
 *
 * 示例 1:
 *
 * 输入: [3,2,3,null,3,null,1]
 *
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 *
 * 输出: 7
 * 解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.
 * 示例 2:
 *
 * 输入: [3,4,5,1,3,null,1]
 *
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 *
 * 输出: 9
 * 解释: 小偷一晚能够盗取的最高金额 = 4 + 5 = 9.
 */

class TreeNode {

  int val;
  TreeNode left;
  TreeNode right;

  TreeNode(int x) {
    val = x;
  }
}

public class RobTree {

  /*
  详细查看LeetCode题解中思路演变；
  动态规划：
    状态方程-》状态转移-》定义状态
    数组两位表示状态：0 当前节点没有偷，1当前节点偷了金额
    如果当前节点没有偷：最大金额=左孩子的最大金额+右孩子偷的最大金额
    如果当前节点有偷：最大金额=右孩子没有偷的金额+左孩子没有偷的金额+当前节点的金额
   */
  public int rob(TreeNode root) {
    int[] result = robInternal(root);
    return Math.max(result[0], result[1]);
  }

  int[] robInternal(TreeNode root) {
    if (root == null) {
      return new int[2];
    }

    int[] result = new int[2];

    int[] left = robInternal(root.left);
    int[] right = robInternal(root.right);

    //不偷当前节点
    result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
    //偷当前节点
    result[1] = left[0] + right[0] + root.val;

    return result;
  }
}
