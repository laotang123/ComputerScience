package com.ljf.algorithm.Hot30;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：ljf
 * @date ：Created in 2020/4/21 20:35
 * @description：https://leetcode-cn.com/problems/string-to-integer-atoi/
 * @modified By：
 * @version: 1.0
 */
public class String2Int {
    //1.定义状态转移map
    private HashMap<String, List<String>> map = new HashMap<>();

    //2.初始状态
    private String state = "start";

    //3.结果
    private int res = 0;

    private int sign = 1;

    public String2Int() {
        map.put("start", Arrays.asList("start", "signed", "in_number", "end"));
        map.put("signed", Arrays.asList("end", "end", "in_number", "end"));
        map.put("in_number", Arrays.asList("end", "end", "in_number", "end"));
        map.put("end", Arrays.asList("end", "end", "end", "end"));
    }

    private int getStateIndex(char c) {
        //状态对应数字
        if (c == ' ') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        } else if (Character.isDigit(c)) {
            return 2;
        } else {
            return 3;
        }
    }

    public int myAtoi(String str) {
        /**
         * 思路：
         * 自动机解决
         */
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            state = map.get(state).get(getStateIndex(c));

            //数字累加
            if (state == "in_number") {
                res = res * 10 + Character.getNumericValue(c);
                //res int类型中途类型溢出,当前下标小于长度-2
                //上溢出
                if (i < str.length() - 1 && map.get(state).get(getStateIndex(str.charAt(i + 1))) == "in_number") {
                    //上溢出
                    int pop = Character.getNumericValue(str.charAt(i + 1));
                    if (sign == 1 && (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && pop > 7)))
                        return Integer.MAX_VALUE;
                    //下溢出
                    if (res * sign < Integer.MIN_VALUE / 10 || (res * sign == Integer.MIN_VALUE / 10 && pop * sign < -8))
                        return Integer.MIN_VALUE;
                }
            } else if (state == "signed") {
                sign = (c == '-') ? -1 : 1;
            } else if (state == "end") {
                break;
            }
        }

        return res * sign;
    }

    public static void main(String[] args) {
        String2Int string2Int = new String2Int();
        System.out.println(string2Int.myAtoi("-123"));
    }
}