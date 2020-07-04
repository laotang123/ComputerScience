package com.ljf.algorithm.str;

/**
 * @author ：ljf
 * @date ：Created in 2020/1/13 9:34
 * @modified By：
 * @version: $
 */

//class Solution {
//    public boolean isMatch(String text, String pattern) {
//        if (pattern.isEmpty()) return text.isEmpty();
//        boolean first_match = (!text.isEmpty() &&
//                (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
//
//        if (pattern.length() >= 2 && pattern.charAt(1) == '*'){
//            return (isMatch(text, pattern.substring(2)) ||
//                    (first_match && isMatch(text.substring(1), pattern)));
//        } else {
//            return first_match && isMatch(text.substring(1), pattern.substring(1));
//        }
//    }
//}

//class Solution {
//    public boolean isMatch(String text, String pattern) {
//        boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
//        dp[text.length()][pattern.length()] = true;
//
//        for (int i = text.length(); i >= 0; i--){
//            for (int j = pattern.length() - 1; j >= 0; j--){
//                boolean first_match = (i < text.length() &&
//                        (pattern.charAt(j) == text.charAt(i) ||
//                                pattern.charAt(j) == '.'));
//                if (j + 1 < pattern.length() && pattern.charAt(j+1) == '*'){
//                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
//                } else {
//                    dp[i][j] = first_match && dp[i+1][j+1];
//                }
//            }
//        }
//        return dp[0][0];
//    }
//}

public class RegexpMatch {
    /**
     * 正则表达式匹配
     */
    public static boolean regexpMatch(String text,String pattern){
        //如果模式串为空
        if (pattern.isEmpty()) return text.isEmpty();

        //模式串和匹配串的当前字符是否匹配
        boolean firstMatch = (!text.isEmpty() &&
                text.charAt(0) == pattern.charAt(0) || pattern.charAt(0) == '.');

        //如果出现*，两种匹配模式
        if(pattern.length() >=2 && pattern.charAt(1) == '*'){
            //*符号能够匹配前一个字符0次或者多次，
            return (regexpMatch(text,pattern.substring(2)) || //匹配0次
                    firstMatch && regexpMatch(text.substring(1),pattern)); //匹配一次和多次，要保证当前字符是相等的
        }else {
            //模式串和匹配串同时后移
            return firstMatch && regexpMatch(text.substring(1),pattern.substring(1));
        }
    }


    public static void main(String[] args) {
//        Solution solution = new Solution();
//        System.out.println(solution.isMatch("aab","c*ab"));
        System.out.println(regexpMatch("abcd","a.*"));
    }
}
