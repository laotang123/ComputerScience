package com.ljf.algorithm;


/**
 * @author ：ljf
 * @date ：2020/7/11 13:09
 * @description：股票利润问题
 * @modified By：
 * @version: $ 1.0
 */
public class MaxProfitLJF {

    /**
     * 只允许交易一次，不含有手续费
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        //初始值，第0天的持有状态
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            //未持有状态可以从“保持未持有”和“持有卖出”两种状态而来
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);

            //持有状态从持有和未持有买入两种状态转换而来
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }

        return dp_i_0;
    }


    /**
     * 交易无限次，每次有手续费版本
     *
     * @param prices
     * @return
     */
    public static int maxProfitKFee(int[] prices, int fee) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0; //暂存，防止dp_i_0中间结果被修改

            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);

            dp_i_1 = Math.max(dp_i_1, temp - prices[i] - fee);
        }

        return dp_i_0;
    }

    /**
     * 无限次交易
     *
     * @param prices
     * @return
     */
    public static int maxProfitInf(int[] prices) {
        int dp_i_0 = 0;
        int dp_i_1 = Integer.MIN_VALUE;

        for (int i = 0; i < prices.length; i++) {
            int temp = dp_i_0;
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, temp - prices[i]);
        }

        return dp_i_0;
    }

    public static int maxProfitK(int[] prices, int max_k) {

        //异常情况，k超过天数的1/2。prices的长度异常
        if (prices.length <= 0) {
            return 0;
        }

        //无限次交易
        if (max_k > prices.length / 2) {
            return maxProfitInf(prices);
        }

        int n = prices.length;
        int[][][] dp = new int[n][max_k + 1][2];


        for (int i = 0; i < n; i++) {
            for (int k = max_k; k >= 1; k--) {
                //第1天特殊处理
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                } else {
                    dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                    dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
                }
            }
        }

        return dp[n - 1][max_k][0];
    }

    public static void main(String[] args) {
//        int[] prices = {7, 1, 5, 3, 6, 4};
//        int[] prices = {1, 3, 2, 8, 4, 9};
        int[] prices = {3, 2, 6, 5, 0, 3};
//        System.out.println(maxProfit(prices));
//        System.out.println(maxProfitKFee(prices, 2));
        System.out.println(maxProfitK(prices, 2));
    }
}
