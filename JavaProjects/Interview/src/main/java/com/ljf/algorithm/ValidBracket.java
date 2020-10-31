package com.ljf.algorithm;

import javax.print.DocFlavor;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author ：ljf
 * @date ：2020/7/12 7:16
 * @description：有效括号：https://leetcode-cn.com/problems/valid-parentheses/
 * @modified By：
 * @version: $ 1.0
 */
public class ValidBracket {
    /**
     * 使用栈顶元素与将要匹配的字符配对；
     * 无效字符串包括配对失败和无配对字符(栈非空)两种情况
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> map = new HashMap<>();

        map.put(']', '[');
        map.put(')', '(');
        map.put('}', '{');

        //栈中存储左括号，遇到右括号进行匹配
        for (int i = 0; i < s.length(); i++) {
            char curChar = s.charAt(i);

            //左括号
            if (!map.containsKey(curChar)) {
                stack.push(curChar);

            } else { //括号不对应或者栈为空均为匹配失败
                if (stack.size() != 0 && map.get(curChar) == stack.peek()) {
                    stack.pop();
                }else{
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public static void main(String[] args) {
        String s = "]}";
        System.out.println(isValid(s));
    }
}
