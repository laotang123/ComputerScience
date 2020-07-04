package com.ljf.algorithm.str;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/15 9:45
 * @modified By：
 * @version: $
 */
public class DPRegexpString {
    //动态规划匹配字符串

    /**
     * 关键是在：问题的分解！！！！！！！，子问题
     * 状态定义：dp[i][j] 表示text[1:i+1]与pattern[1:j+1]匹配(左闭右开) dp[0][0]表示空串匹配
     * 转移方程：
     * 1.pattern和text当前字符相等 or pattern[j-1]='.' 则dp[i][j] = dp[i-1][j-1]
     * 2.如果pattern的当前字符为*，即pattern[j-1]='*',分为匹配0次和多次
     * 2.1匹配0次
     * dp[i][j]=dp[i][j-2]
     * 2.2 匹配一次
     * dp[i][j]=dp[i-1][j]
     * <p>
     * 实现：循环 or 递归？
     * 自顶向下动态规划匹配字符串
     *
     * @param text：匹配串
     * @param pattern：模式串
     * @return
     */
    private static boolean isMatchUp2Down(String text, String pattern) {
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];

        //空串dp[0][0]默认为匹配
        dp[0][0] = true;
        char[] tChars = text.toCharArray();
        char[] pChars = pattern.toCharArray();

        //text为空的情况，如text = '',pattern='a*'
        for (int i = 1; i <= pChars.length; i++) {
            dp[0][i] = pChars[i-1] == '*' ? dp[0][i - 2] : false;
        }

        for (int i = 1; i <= tChars.length; i++) {
            for (int j = 1; j <= pChars.length; j++) {
                //模式串当前字符为字母和.
                if (tChars[i - 1] == pChars[j - 1] || pChars[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                }

                //模式串当前字符为*
                if (pChars[j - 1] == '*') {
                    dp[i][j] |= dp[i][j - 2];

                    //模式串的上一个字符为.或者与匹配串相匹配
                    if(tChars[i-1] == pChars[j-2] || pChars[j-2] == '.'){
                        dp[i][j] |= dp[i-1][j];
                    }
                }
            }
        }

        return dp[tChars.length][pChars.length];
    }
    public static void main(String[] args) {
        System.out.println(isMatchUp2Down("", "ab*"));
    }


}
