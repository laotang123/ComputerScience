package com.ljf.dataStructure.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/3 10:21
 * @description：逆波兰表达式计算器
 * @modified By：
 * @version: $
 */
public class InversePolishCalculator {
    private static final int ADD = 1;
    private static final int SUB = 1;
    private static final int MUL = 2;
    private static final int DIV = 2;

    public static List<String> getStringList(String suffixExpression) {
        List<String> strings = new ArrayList<>();

        String[] split = suffixExpression.split(" ");
        for (String s : split) {
            strings.add(s);
        }
        return strings;

    }

    public static int calculator(List<String> stringList) {
        /**
         * 从左到右扫描，如果是数进行入栈
         * 如果是符号，从栈中pop出两个数与当前符号进行计算，将计算结果压入栈中
         * 最后栈中剩下的数字就是结果，返回
         */
        Stack<String> stringStack = new Stack<>();

        for (String s : stringList) {
            if (s.matches("\\d+")) {
                stringStack.push(s);
            } else {
                int num2 = Integer.parseInt(stringStack.pop());
                int num1 = Integer.parseInt(stringStack.pop());

                int res = 0;//接收计算结果
                switch (s) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        res = num1 / num2;
                        break;
                    default:
                        throw new RuntimeException(s + ":运算符号错误");
                }
                stringStack.push("" + res);
            }
        }

        //栈中最后一个元素就是结果
        return Integer.parseInt(stringStack.pop());
    }

    /**
     * 中缀表达式转后缀表达式
     *
     * @param infix
     * @return
     */
    public static List<String> infixToSuffix(String infix) {
        /**
         * 一.初始化两个栈，一个为符号栈s1，一个为中间结果栈s2
         * 二.从左到右扫描中缀表达式
         * 三.如果遇到操作数，将其压入s2
         * 四.如果遇到运算符
         *     1.如果s1栈为空或者s1栈顶运算符为‘(’，将其直接压入s1
         *     2.如果其优先级高于栈顶运算符，也直接压入s1
         *     3.如果其优先级小于等于栈顶运算符，将s1栈顶运算符弹栈并压入s2,
         *     然后转到4.1与s1中新栈顶运算符继续判断。
         * 五.如果遇到括号
         *     1.如果是‘(’，将其压入s1
         *     2.如果是‘)’，则依次弹出s1中运算符并压入s2中，直至遇到‘)’，此时将这一对括号丢弃
         * 六.重复二~~五，直到结束
         * 七.将s1中剩余运算符依次弹出并压入s2
         * 八.此时s2从栈底到栈顶的元素排列即为后缀表达式
         */

        //一 初始化两个栈
        Stack<String> s1 = new Stack<>();
        List<String> s2 = new ArrayList<>();

        String[] infixSplit = infix.split(" ");

        //二 扫描中缀表达式
        for (String item : infixSplit) {
            //三 如果遇到操作数
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop(); //丢弃这一对括号
            } else {
//                while (s1.size() != 0 && symbolToInt(s1.peek()) >= symbolToInt(item)){
//                    s2.add(s1.pop());
//                }
                //1.如果s1栈为空或者s1栈顶运算符为‘(’，将其直接压入s1
                //         *     2.如果其优先级高于栈顶运算符，也直接压入s1
                //         *     3.如果其优先级小于等于栈顶运算符，将s1栈顶运算符弹栈并压入s2,
                //         *     然后转到4.1与s1中新栈顶运算符继续判断。
                while (true){
                    if (s1.size() == 0 || s1.peek().equals("(")){
                        s1.push(item);
                        break;
                    }
                    else if (symbolToInt(item) > symbolToInt(s1.peek())){
                        s1.push(item);
                        break;
                    }
                    else if (symbolToInt(item) <= symbolToInt(s1.peek())){
                        s2.add(s1.pop());
                    }
                }

            }

        }

        //七.将s1中剩余运算符依次弹出并压入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }

        return s2;
    }

    /**
     * 比较符号的优先级，根据符号返回对应数值
     *
     * @param symbol
     * @return
     */
    public static int symbolToInt(String symbol) {
        int result = 0;

        switch (symbol) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println(symbol + ":运算符不存在");
                break;
        }
        return result;
    }

    public static void main(String[] args) {
        /**
         * 逆波兰表达式实现整数计算的计算器
         * 1.逆波兰表达式使用空格将数字和符号分割
         * 2.表达式-》list<String>
         * 3.编写calculator方法得出计算结果
         */
        // 中缀转后缀，(3+4)*5-6 ==> 3 4 + 5 * 6 -
//        String suffixExpression = "3 4 + 5 * 6 -";
        String suffixExpression = "1 2 3 + 4 * + 5 -";
        String infixExpression = "1 + ( ( 2 + 3 ) * 4 ) - 5";
//        List<String> stringList = getStringList(suffixExpression);

        //中缀表达式转后缀表达式
        List<String> stringList = infixToSuffix(infixExpression);
//        System.out.println(stringList);
        //后缀表达式进行计算
        System.out.println(calculator(stringList));

    }
}
