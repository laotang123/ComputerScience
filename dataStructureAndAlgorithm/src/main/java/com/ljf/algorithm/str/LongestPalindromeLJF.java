package com.ljf.algorithm.str;

/**
 * @author ：ljf
 * @date ：Created in 2020/5/3 16:19
 * @description：
 * @modified By：
 * @version: 1.0
 */
public class LongestPalindromeLJF {
    /**
     * 中心扩展法实现检测最长回文子串
     * 字符串长度为n，可能的中心点为2n-1，字符和字符之间的空格
     */
    public String longestPalindrome(String s) {
        String resStr = "";
        //判空
        if (s == null || s.length() == 0) {
            return resStr;
        }

        //子串的左右边界
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //基于当前字符扩展
            int len1 = expandAroundCenter(s, i, i);
            //基于空格扩展
            int len2 = expandAroundCenter(s, i, i + 1);

            int len = Math.max(len1, len2);
            //更新边界
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        resStr = s.substring(start, end + 1);
        return resStr;
    }

    /**
     * 中心扩展，left和right要么相等，要么相邻
     *
     * @param s
     * @param left
     * @param right
     * @return
     */
    private int expandAroundCenter(String s, int left, int right) {
        //向两边扩展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            //同时扩展
            left--;
            right++;
        }
        return right - left - 1;
    }

    public static void main(String[] args) {
        LongestPalindromeLJF ljf = new LongestPalindromeLJF();
        System.out.println(ljf.longestPalindrome("babad"));
    }
}
