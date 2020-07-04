package com.ljf.algorithm.str;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/14 9:53
 * @modified By：
 * @version: $
 */
public class DPRegexpStr {
    /**
     * 动态规划匹配字符串，以空间换时间
     * 将匹配过的子字符串结果保存在数组中
     */

    public static boolean isMatchDown2Up(String text, String pattern) {
        /**
         * 自底向上,字符串反向遍历；字符串映射到dp状态
         * 逐个遍历字符串字符，将当前问题分解为当前字符是否匹配和剩下所有字符是否匹配(子问题结构)
         * 遇到*字符，分为匹配0次和匹配1次或多次两种情况分析
         * 状态定义dp[i][j]表示i，j之前的字符都匹配
         * 子问题重复在哪里？
         */
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true;

        for (int i = text.length(); i >= 0; i--) {
            for (int j = pattern.length() - 1; j >= 0; j--) {
                boolean first_match = (i < text.length() &&
                        (pattern.charAt(j) == text.charAt(i) ||
                                pattern.charAt(j) == '.'));
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j]; //当前是否匹配的状态取决于 匹配0次(忽略模式串两个字符)，匹配一次或多次(忽略匹配串一个字符)
                } else {
                    dp[i][j] = first_match && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static boolean isMatch(String text, String pattern) {
        //dp[i][j]表示text[i:]和pattern[j:]匹配
        //dp[text.length][:pattern]表示text为空串，pattern不为空串的情况
        //dp[text.length][pattern.length]表示text和pattern都为空串

        //保存状态的二位数组
        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
        dp[text.length()][pattern.length()] = true; //两者都为空串匹配的情况

        for (int i = text.length(); i >= 0; i++) {
            for (int j = pattern.length() - 1; j >= 0; j++) {//text为空串，pattern不为空串也会出现匹配的情况。如text='',pattern='a*'
                boolean firstMatch = (i < text.length() && (text.charAt(i) == pattern.charAt(j) ||
                        pattern.charAt(j) == '.'));

                //判断当前字符的上一个是否为*
                if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
                    dp[i][j] = dp[i][j + 2] || firstMatch && dp[i + 1][j];//dp[i][j+2]表示0次匹配，dp[i+1][j]表示一次匹配
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];//dp[0][0]表示text和pattern字符串匹配
    }

    public static boolean isMatchUp2Down(String s, String p) {
        /**
         * 自顶向下
         */
        if (s.equals(p)) {
            return true;
        }

        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();

        // dp[i][j] => is s[0, i - 1] match p[0, j - 1] ?
        boolean[][] dp = new boolean[sArr.length + 1][pArr.length + 1];

        dp[0][0] = true;

        //匹配空串的情况dp[0]表示pArr为空
        for (int i = 1; i <= pArr.length; ++i) {
            dp[0][i] = pArr[i - 1] == '*' ? dp[0][i - 2] : false;
        }

        for (int i = 1; i <= sArr.length; ++i) {
            for (int j = 1; j <= pArr.length; ++j) {
                if (sArr[i - 1] == pArr[j - 1] || pArr[j - 1] == '.') {//当前字符相等
                    // 看 s[0,...i-1] 和 p[0,...j-1]
                    dp[i][j] = dp[i - 1][j - 1];//在当前字符相等的前提下，当前字符串是否相等取决于之前的字符串
                }

                if (pArr[j - 1] == '*') {
                    // 看 s[0,...i] 和 p[0,...j-2]，匹配0次
                    dp[i][j] |= dp[i][j - 2]; //a|=b  ==> a=a|b ,这里隐含重复使用

                    //匹配一次或多次，分为两种情况
                    //1.模式串为字母，需要和匹配串比较是否相等
                    //2.模式串为 '.'，不用比较
                    if (pArr[j - 2] == sArr[i - 1] || pArr[j - 2] == '.') {
                        // 看 s[0,...i-1] 和 p[0,...j]
                        dp[i][j] |= dp[i - 1][j];
                    }
                }
            }
        }

        return dp[sArr.length][pArr.length];
    }

    public static void main(String[] args) {
//        System.out.println(isMatch("abc", "a*a*d"));
        System.out.println(isMatchDown2Up("acccd", "ac*d"));
//        System.out.println(isMatchUp2Down("", "a*"));
    }

}
