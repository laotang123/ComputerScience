package com.ljf.algorithm.sort;

import java.util.Arrays;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/10 9:06
 * @description：希尔排序
 * @modified By：
 * @version: $
 */
public class ShellSort {
    /**
     * 缩小增量排序，希尔排序：
     * 1.首次增量gap=arr.length/2，分为gap组，对每一组进行插入排序
     * 2.减少增量gap=gap/2，分为gap组，对每一组进行插入排序
     * 3.重复步骤2，直到gap=1
     */
    public static void shellSort(int[] arr) {
        int temp = 0;//用于交换的临时变量
        int count = 0;
        //算法分解，gap=5时
        /*for (int i = 5; i < arr.length; i++) {
            for (int j = i - 5; j >= 0; j -= 5) { //插入排序部分，
                if (arr[j] > arr[j + 5]) {//当前数字大于索引+gap，则进行交换
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));*/
        boolean flag = true;//设置对比之前的序列是否有序

        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {//当前元素大于加上步长的元素，进行交换。升序排列
                        flag = false;
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                    if (flag){
                        break;
                    }
                }
                //重置flag
                flag = true;
            }
        }
    }

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
//        shellSort(arr);
//        System.out.println(Arrays.toString(arr));
        int[] arr = new int[80000];

        //数组赋值
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 8000000);
        }
        //时间测试
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);


//        System.out.println("排序前：" + Arrays.toString(arr));
        shellSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime);

        System.out.println("时间花费：" + (endTime - startTime) / 1000.0 + "秒");
    }
}
