package com.ljf.algorithm.sort;

/**
 * @author ：ljf
 * @date ：Created in 2019/12/10 8:33
 * @description：插入排序
 * @modified By：
 * @version: $
 */
public class InsertSort {
    /**
     * 算法流程
     * 1.首先固定数组第一位，遍历后面的n-1位元素
     * 2.将当前遍历的元素和已排好序的数组依次比较，找到待排序元素的位置
     * 3.进行交换
     */
    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //待插入的值为当前遍历，待插入下标为上一个位置
            int insertIndex = i - 1;
            int insertValue = arr[i];

            //开始访问部分有序数组，访问条件为：insertValue<arr[insertIndex]
            //同时保证数组不能越界，数组后移
            while (insertIndex >= 0 && insertValue < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }

            //while循环结束后，找到待插入值得下标。insertIndex+1
            //需要判断，如果并没有后移，则不需要交换，节省时间
            if (insertIndex + 1 != i) {
                arr[insertIndex + 1] = insertValue;
            }

        }
    }

    public static void main(String[] args) {
//        int[] arr = {101, 34, 119, 1, -1, 89};
//        for (int i : arr) {
//            System.out.print(i + "\t");
//        }
//        System.out.println();
//        insertSort(arr);
//
//        for (int i : arr) {
//            System.out.print(i + "\t");
//        }
        int[] arr = new int[80000];

        //数组赋值
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random()*8000000);
        }
        //时间测试
        long startTime = System.currentTimeMillis();
        System.out.println(startTime);


//        System.out.println("排序前：" + Arrays.toString(arr));
        insertSort(arr);
//        System.out.println("排序后：" + Arrays.toString(arr));
        long endTime = System.currentTimeMillis();
        System.out.println(endTime);

        System.out.println("时间花费：" + (endTime-startTime)/1000.0 + "秒");
    }


}
