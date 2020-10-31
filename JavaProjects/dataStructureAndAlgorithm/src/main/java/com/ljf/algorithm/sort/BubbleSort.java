package com.ljf.algorithm.sort;

import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/6 10:30
 * @description：从小到大实现冒泡排序
 * @modified By：
 * @version: $
 */
public class BubbleSort {
    public static void bubbleSort(int[] arr) {
        //将i=0-（n-1）冒泡依次和 i+1 到n个元素进行对比
        boolean flag = true; //标志当前序列是否有序，默认为true
        int temp = 0; //交换时保存中间变量

        for (int i = 0; i < arr.length - 1; i++) { //控制趟数
            for (int j = 0; j < arr.length - i - 1; j++) {
                //如果遇到比当前值更小的值则进行交换
                if (arr[j] > arr[j + 1]) {
                    flag = false;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            if (flag) {
                //如果已经有序 则退出
                break;
            }else{
                flag = true;//重置flag
            }
        }
    }

    public static void main(String[] args) {
        //创建数组，数组为引用类型。
//        int[] arr = {3,9,-1,10,-2};
        int[] arr = new int[80000];

        //数组赋值
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        //时间测试
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);


//        System.out.println("排序前：" + Arrays.toString(arr));
        bubbleSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime);

        System.out.println("时间花费：" + (endTime-startTime)/1000.0 + "秒");

    }
}
