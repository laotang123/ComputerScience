package com.ljf.algorithm.sort;

import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/6 11:09
 * @description：从小到大选择排序
 * @modified By：
 * @version: $
 */
public class SelectSort {
    public static void selectSort(int[] arr) {
        /**
         *选择排序：
         * 将数组分为有序和无序两部分，默认第一个元素为有序部分。
         * 1.从无序数组部分找出最小/最大元素和下标
         * 2.将找到的最小元素和下标和有序数组部分的最后一个元素对比
         * 2.1 如果更小，则交换
         * 2.2 否则不交换
         *   最终将有序数组后移一位
         */
        //对比n-1次
        for (int i = 0; i < arr.length - 1; i++) { //查找次数
            //无序数组最小一位的下标和值
            int minIndex = i;
            int minValue = arr[i];
            for (int j = i+1; j < arr.length ; j++) {//找到有序数组中最小值
                if (arr[j] < minValue){
                    //如果找到更小的值，则重新赋值
                    minIndex = j;
                    minValue = arr[j];
                }
            }
            //如果找到，则进行无序数组中最小值和有序数组中最后一个值交换
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
        }
    }

    public static void main(String[] args) {
        //算法：复杂-》拆解简单，逐步解决
//        int[] arr = {101, 34, 119, 1};
//        System.out.println("排序前：" + Arrays.toString(arr));
//        selectSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        int[] arr = new int[80000];

        //数组赋值
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        //时间测试
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);


//        System.out.println("排序前：" + Arrays.toString(arr));
        selectSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime);

        System.out.println("时间花费：" + (endTime-startTime)/1000.0 + "秒");
    }
}
