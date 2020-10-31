package com.ljf.algorithm.dynamicPrograming;

/**
 * @author     ：ljf
 * @date       ：Created in 2020/2/29 10:46
 * @modified By：
 * @version: 1.0
 * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/solution/
 */
public class MaxProfit {

  public int maxProfit(int[] prices, int fee) {
    /**
     * 动态规划如果只是用到相邻的状态，使用变量的重新赋值替换数组，节省空间
     */
    //dp[i][k][0]交易次数k=无穷大
    int dp_i_0 = 0;//-1天不持有股票利润为0
    int dp_i_1 = Integer.MIN_VALUE;//-1天持有股票，无穷小表示不可能

    for (int i = 0; i < prices.length; i++) {
      int temp = dp_i_0;
      //今天不持有股票，可以由昨天不持有和昨天持有但是卖出转变来
      dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);

      //今天持有股票，可以由昨天持有股票和昨天没有持有但是买入转变来(不要忘记手续费)
      dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);//这里的temp是要记录昨天的数据，temp暂存
    }
    return dp_i_0;//最后应当没有持有股票
  }

  public static void main(String[] args) {
    int[] prices = {1, 3, 2, 8, 4, 9};
    int fee = 2;
    MaxProfit profit = new MaxProfit();
    System.out.println(profit.maxProfit(prices, fee));
  }
}
