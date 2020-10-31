package com.ljf.algorithm.Hot30;


import java.util.HashMap;
import java.util.HashSet;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/18 18:49
 * @description：https://leetcode.com/problems/longest-substring-without-repeating-characters
 * @modified By：
 * @version: 1.0
 */
public class LengthOfLongestSubstring {
    //TODO: array和string问题常用滑动窗口解决
    //暴力实现，hashset
    public int forceLengthOfLongestSubstring(String s) {
        /**
         * 思路：暴力遍历，放到一个hashset，下次访问是否存在，如果存在就遍历下一个节点
         * 选取长度最大的
         */
        int maxLen = 0;
        HashSet<Character> set = new HashSet<>();

        //判空和异常值
        if (s.length() == 0 || s == null) {
            return 0;
        }

        //遍历数组
        int tmpLen = 0;
        for (int i = 0; i < s.length(); i++) {
            //从i向字符串末尾查找
            for (int j = i; j < s.length(); j++) {
                char c = s.charAt(j);

                //如果set中存在当前字符，结束当前字符下的查找，清空set；
                //对比当前长度与最大长度
                if (set.contains(c)) {
                    break;
                } else {
                    tmpLen++;
                    set.add(c);
                }
            }
            set.clear();
            if (tmpLen > maxLen) {
                maxLen = tmpLen;
            }
            //临时长度置0
            tmpLen = 0;
        }


        return maxLen;
    }

    /**
     * 滑动串口：
     * [i,j]表示字符串从i到j的子串，先让j向右移动，同时判断s[j]是否在hashset中
     * 如果在则移动索引i，一直移动到当前字符不在set中
     * 如果不在则添加到set中，同时将当前size+1
     * 最大时间复杂度，i和j都遍历了一次数组，O(2n)=>O(n)
     */
    public int slideWindow1(String s) {
        int resSize = 0;

        //左右索引初始值为0
        int i = 0, j = 0;
        int n = s.length();

        //唯一子串
        HashSet<Character> set = new HashSet<>();

        while (i < n && j < n) {
            //不存在set中
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j++));
                resSize = Math.max(resSize, j - i);
            } else {
                //存在set中
                set.remove(s.charAt(i++));
            }
        }

        return resSize;
    }

    /**
     * 进阶2：hashSet的实质还是调用了HashMap，value放的值是final object
     * 相比滑动窗口1，存在的问题是：
     * 如果此时hashSet中已经存在当前字符，如果直接找到当前字符在子串中的位置(HashMap)而不是采用遍历的方式
     * 那么运行速度会更快,注：这里的下标索引是从1开始
     */
    public int slideWindow2(String s) {
        int resSize = 0;

        //唯一子串,字符：字符下标
        HashMap<Character, Integer> map = new HashMap<>();


        //HashMap的put方法，如果已经存在key会直接更新value
        for (int j = 0, i = 0; j < s.length(); j++) {
            //如果存在直接更新i值，取出最大的。直接跳过遍历子串的过程
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }

            //更新长度
            resSize = Math.max(resSize, j - i + 1);
            //更新当前字符在map中的下标索引
            map.put(s.charAt(j), j + 1);
        }

        return resSize;
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring code = new LengthOfLongestSubstring();
        String s = "pwwkew";
        int len = code.forceLengthOfLongestSubstring(s);
        System.out.println(len);
        System.out.println(code.slideWindow1(s));
        System.out.println(code.slideWindow2(s));
    }
}
