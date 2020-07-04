package com.ljf.algorithm.dynamicPrograming;

import com.sun.org.apache.regexp.internal.RE;
import javax.naming.ldap.PagedResultsControl;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/3/16 11:07
 * @modified By：
 * @version: 1.0
 * 动态转移方程：
 * dp[i][k][0]：表示第i天，进行第k次交易，0表示当前状态不持有，1表示持有
 * 买入-卖出为一次交易，这里只记录买入交易数量+1,上一次交易的非持有状态进行该次的买入
 *     dp[i][k][0] = max(dp[i-1][0],dp[i-1][k][1]+prices[i]) //第i天不持有：i-1天也不持有或者是i-1天持有，今天卖出
 *     dp[i][k][1] = max(dp[i-1][1],dp[i-1][k-1][0]-prices[i]) //第i天持有：i-1天也持有或者是i-2天卖出，今天持有
 */
public class MaxProfitLJF {

  int maxProfit;

  /*
  限制交易次数为一次，k=1，此时k为定值，这个维度对转移方程没有影响
  转移方程：
    注：因为只交易一次，所以dp[i-1][0][0] = 0，买入股票之前的利润为0
    dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i]) //第i天不持有：i-1天也不持有或者是i-1天持有，今天卖出
    dp[i][1] = max(dp[i-1][1],dp[i-1][0]-prices[i])
             = max(dp[i-1][1],0-prices[i])//第i天持有：i-1天也持有或者是i-2天卖出，今天持有
   */
  public int maxProfit_k_1(int[] prices) {
    //设置初始值
    int dp_i_0 = 0;
    int dp_i_1 = Integer.MIN_VALUE;

    for (int i = 0; i < prices.length; i++) {
      dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);//持有或卖出股票
      dp_i_1 = Math.max(dp_i_1, -prices[i]);//持有或买入股票
    }
    return dp_i_0;
  }


  /*
限制交易次数为两次，k=2，此时k为定值，这个维度对转移方程没有影响
转移方程：
  还记得前面总结的「穷举框架」吗？就是说我们必须穷举所有状态。其实我们之前的解法，都在穷举所有状态，只是之前的题目中 k 都被化简掉了。这道题由于没有消掉 k 的影响，所以必须要对 k 进行穷举：
  int max_k = 2;
  int[][][] dp = new int[n][max_k + 1][2];
  for (int i = 0; i < n; i++) {
      for (int k = max_k; k >= 1; k--) {
          if (i - 1 == -1) { /*处理 base case  }
          dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i]);
              dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i]);
              }
              }
  // 穷举了 n × max_k × 2 个状态，正确。
              return dp[n - 1][max_k][0];
*/
  public int maxProfit_k_2(int[] prices) {
//    //设置初始值
//    int max_k = 2;
//    int n = prices.length;
//    //状态数组
//    int dp[][][] = new int[n][max_k + 1][2];
//
//    for (int i = 0; i < n; i++) {
//      for (int k = max_k; k >= 1; k--) {
//        if (i == 0) {
//          //base case处理，第一天非持有利润为0，持有的利润为-prices[0]
//          dp[i][k][0] = 0;
//          dp[i][k][1] = -prices[i];
//        } else {
//          //非持有状态
//          dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
//          //持有状态
//          dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
//        }
//      }
//    }
//
//    return dp[n - 1][max_k][0];

    int dp_i10 = 0, dp_i11 = Integer.MIN_VALUE;
    int dp_i20 = 0, dp_i21 = Integer.MIN_VALUE;
    for (int price : prices) {
      dp_i20 = Math.max(dp_i20, dp_i21 + price);
      dp_i21 = Math.max(dp_i21, dp_i10 - price);
      dp_i10 = Math.max(dp_i10, dp_i11 + price);
      dp_i11 = Math.max(dp_i11, -price);
    }
    return dp_i20;

  }

  public int maxProfit_k_anyInteger(int max_k, int[] prices) {
    //设置初始值
    int n = prices.length;
    //状态数组
    int dp[][][] = new int[n][max_k + 1][2];

    for (int i = 0; i < n; i++) {
      for (int k = max_k; k >= 1; k--) {
        if (i == 0) {
          //base case处理，第一天非持有利润为0，持有的利润为-prices[0]
          dp[i][k][0] = 0;
          dp[i][k][1] = -prices[i];
        } else {
          //非持有状态
          dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
          //持有状态
          dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
        }
      }
    }

    return dp[n - 1][max_k][0];

  }

  /*
  回溯法实现最大利润
  status 0/1 表示股票持有(1)和非持有(0)
   */
  public int maxProfit_backtrack_k_inf(int[] prices) {
    //初始化状态值为0
    int status = 0;
    backtrack(status, 0, prices, 0);

    return maxProfit;
  }

  private void backtrack(int status, int profit, int[] prices, int day) {
    /**
     * status=0
     *    可以不做动作
     *    可以买入
     * status=1
     *    可以持有
     *    可以卖出
     */

    //可行解
    if (day == prices.length) {
      maxProfit = maxProfit > profit ? maxProfit : profit;
      return;
    }
    if (status == 0) {
      //不做动作,进入下一天
      backtrack(status, profit, prices, day + 1);

      //买入,修改状态
      status = 1;
      backtrack(status, profit - prices[day], prices, day + 1);
//        status = 0;//回溯
    } else {
      //继续持有
      backtrack(status, profit, prices, day + 1);

      //卖出
      status = 0;
      backtrack(status, profit + prices[day], prices, day + 1);
    }

  }

  /*
  不限制交易次数，k=无穷大，可以认为k和k-1相等，这个维度对转移方程没有影响
  转移方程：
    注：
    dp[i][k][0] = max(dp[i-1][k][0],dp[i-1][k][1]+prices[i]) //第i天不持有：i-1天也不持有或者是i-1天持有，今天卖出
    dp[i][k][1] = max(dp[i-1][k][1],dp[i-1][k-1][0]-prices[i]) //第i天持有：i-1天也持有或者是i-2天卖出，今天持有
 */
  public int maxProfit_k_inf(int[] prices) {
    //设置初始值
    int dp_i_0 = 0;
    int dp_i_1 = Integer.MIN_VALUE;
    int temp;//这里用来记录上一次交易的非持有状态

    for (int i = 0; i < prices.length; i++) {
      temp = dp_i_0;
      dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);//持有或卖出股票
      dp_i_1 = Math.max(dp_i_1, temp - prices[i]);//持有或买入股票
    }
    return dp_i_0;
  }

  /*
  交易包含冻结期，即卖出后需要经历冻结期时间后才可以再次买入，
  冻结期=1,不限制交易次数
  转移方程：
    dp[i][0] = max(dp[i-1][0],dp[i-1][1]+prices[i]) //第i天不持有：i-1天也不持有或者是i-1天持有，今天卖出
    dp[i][1] = max(dp[i-1][1],dp[i-2][0]-prices[i]) //第i天持有：i-1天也持有或者是i-2天卖出，今天持有
   */
  public int maxProfit_with_cool(int[] prices) {
    //设置初始值，第0天不持有利润为0，持有为无穷小(表示不可能事件)
    int dp_i_0 = 0;
    int dp_i_1 = Integer.MIN_VALUE;
    int dp_pre_0 = 0;//表示dp[i-2][0]

    int temp;//存储中间变量
    for (int i = 0; i < prices.length; i++) {
      temp = dp_i_0;
      dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);//卖股票
      dp_i_1 = Math.max(dp_i_1, dp_pre_0 - prices[i]);//买股票
      dp_pre_0 = temp;
    }
    return dp_i_0;//最后一天应该是卖出状态
  }

  public static void main(String[] args) {
    int[] prices = {1, 2, 3, 0, 2};
    MaxProfitLJF ljf = new MaxProfitLJF();
    System.out.println(ljf.maxProfit_with_cool(prices));
    System.out.println(ljf.maxProfit_k_1(prices));
    System.out.println(ljf.maxProfit_k_inf(prices));
    System.out.println(ljf.maxProfit_backtrack_k_inf(prices));

    System.out.println("交易两次获取的最大利润：" + ljf.maxProfit_k_2(prices));
  }
}
