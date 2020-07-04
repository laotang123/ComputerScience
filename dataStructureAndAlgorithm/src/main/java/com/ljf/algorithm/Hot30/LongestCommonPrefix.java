package com.ljf.algorithm.Hot30;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/23 13:32
 * @description： 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * <p>
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * <p>
 * 所有输入只包含小写字母 a-z 。
 * <p>
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix* 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。 * @modified By：
 * @version: 1.0
 */
public class LongestCommonPrefix {
    /**
     * 思路：存一个字符创共有部分，字符数组
     * 依次取子字符数组中的元素和共有部分对比
     * TODO: 优化，使用最短的字符串对比
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefixLJF(String[] strs) {
        //判空
        if (strs.length == 0 || strs == null) {
            return "";
        }

        char[] common = strs[0].toCharArray();
        int validLen = common.length;//有效长度
        String str;
        //遍历字符串数组，
        for (int i = 1; i < strs.length; i++) {
            //与已有字符数组元素对比
            str = strs[i];
            validLen = Math.min(validLen, str.length());

            if (validLen == 0) break;
            for (int j = 0; j < validLen; j++) {
                if (common[j] != str.charAt(j)) {
                    validLen = j;
                }
            }
        }

        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < validLen; i++) {
            buffer.append(common[i]);
        }
        return buffer.toString();

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++)
            //如果当前前缀比字符串长，则从尾部一直删减到字符串长度
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = {"abc", "ab", "a"};

        LongestCommonPrefix prefix = new LongestCommonPrefix();
//        System.out.println(prefix.longestCommonPrefix(strs));
        String s = "ab";
        System.out.println(s.indexOf("a"));
    }
}
