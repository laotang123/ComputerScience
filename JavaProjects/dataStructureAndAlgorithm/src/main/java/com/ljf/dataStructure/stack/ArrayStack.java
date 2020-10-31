package com.ljf.dataStructure.stack;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/2 9:41
 * @description：数组模拟栈的入栈和出栈
 * @modified By：
 * @version: $
 */
class Stack {
    private int maxSize;
    private int top = -1;
    private int[] stack;

    public Stack(int maxSize) {
        stack = new int[maxSize];
        this.maxSize = maxSize;
    }

    /**
     * 判断栈是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 压栈
     *
     * @param value
     */
    public void push(int value) {
        //栈不满则入栈
        if (!isFull()) {
            stack[++top] = value;
        } else {
            System.out.println("栈满，入栈失败");
        }

    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        //栈非空则出栈
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        return stack[top--];
    }

    /**
     * 显示栈中元素，从顶到下
     */
    public void showStack(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d] = %d \n",i,stack[i]);
        }
    }
}

public class ArrayStack {
    public static void main(String[] args) {
        Stack stack = new Stack(5);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.showStack();

        stack.pop();
        stack.showStack();
    }
}
